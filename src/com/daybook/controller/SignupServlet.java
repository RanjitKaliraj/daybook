package com.daybook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daybook.dto.ProfileDto;
import com.daybook.dto.SignUpDto;
import com.daybook.service.UserAccount;
import com.daybook.service.UserProfile;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/register")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccount userAccount;
	private UserProfile userProfile;
	

	public SignupServlet() {
		super();
		userAccount = new UserAccount();
		userProfile = new UserProfile();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet.Signup::Starting servlet for activation code check");
		SignUpDto signupDto = new SignUpDto();
		HttpSession session = request.getSession();
		
		System.out.println("Servlet.Signup::getting activation code from parameter");
		int code = 0;
		try{
			code = Integer.parseInt(request.getParameter("activation_code"));
		}
		catch (NumberFormatException e){
		}
		signupDto.setActivation_Code(code);
		
		String[] sessionArray = (String[]) request.getSession().getAttribute("signup");
		int user_Id = Integer.parseInt(sessionArray[0]);
		signupDto.setUser_id(user_Id);
		
		//if the activation code is correct then it will redirected to firsttime setup page.
		System.out.println("Servlet.Signup::Starting checking activation code");
		if (userAccount.checkActivationCode(signupDto)){
			System.out.println("Servlet.Signup::Activation code match.");
			System.out.println("Servlet.Signup::Activating the account.");
			userAccount.activateAccount(user_Id);
			String[] array = (String[])request.getSession().getAttribute("signup");
			
			//invalidating previous session
			request.getSession().setAttribute("signup", null);
			session.invalidate();
			System.out.println("Servlet.Signup::Previous session invalidated");
			
			//if the user bank info is already present user will redirect to home page.
			System.out.println("Servlet.Signup::Checking if bank info is already setup or not");
			if (userAccount.checkUserAdditionalInfo(user_Id)){
				System.out.println("Servlet.Signup::Bank info already setup");
				ProfileDto profile = new ProfileDto();
				profile.setUser_Id(user_Id);
				userProfile.getUser(profile);
				String userId =Integer.toString(user_Id);
				String fname = profile.getF_name();
				String lname = profile.getL_name();
				String [] user = {userId, fname, lname};
				System.out.println("Servlet.Signup::Setting new user session");
				request.getSession().setAttribute("user", user);
				System.out.println("Servlet.Signup::Dispatch to home.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
				dispatcher.forward(request, response);
			}
			else{
				System.out.println("Servlet.Signup::bank info is not setup");
				String userId = array[0];
				String page1 ="";
				String page2="setup";
				String [] signup = {userId, page1, page2};
				System.out.println("Servlet.Signup::Setting new signup session");
				request.getSession().setAttribute("signup", signup);
				System.out.println("Servlet.Signup::dispatch to first-time-setup.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("first-time-setup.jsp");
				dispatcher.forward(request, response);
			}
		}
		else{
			System.out.println("Servlet.Signup::Activation code do not matched");
			String msg ="Invalid activation code.";
			request.setAttribute("msg", msg);
			System.out.println("Servlet.Signup::Dispatch to activate.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("activate.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet.Signup::starting registering user");
		SignUpDto signupDto = new SignUpDto();
		HttpSession session = request.getSession();
		
		signupDto.setF_name(request.getParameter("fname"));
		signupDto.setL_name(request.getParameter("lname"));
		signupDto.setEmail(request.getParameter("email"));
		signupDto.setUsername(request.getParameter("username"));
		signupDto.setPassword(request.getParameter("password2"));
		
		System.out.println("Servlet.Signup::Calling signup method from service");
		userAccount.signUp(signupDto);
		System.out.println("Servlet.Signup::Checking if user input email is available or not");
		if (signupDto.isEmailAvailable()){
			System.out.println("Servlet.Signup::email is available");
			if (signupDto.isSignupSuccessful()){
				System.out.println("Servlet.Signup::signup is successful");
				String user_id = Integer.toString(signupDto.getUser_id());
				String page1 = "activate";
				String page2="";
				String[] signup = {user_id, page1, page2};
				System.out.println("Servlet.Signup::Setting signup session");
				session.setAttribute("signup", signup);
				System.out.println("Servlet.Signup::Dispatch to activate.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("activate.jsp");
				dispatcher.forward(request, response);
			}
			else{
				System.out.println("Servlet.Signup::Signup is not successful");
				String msg ="Something went wrong. Please, try again later.";
				request.setAttribute("msg", msg);
				System.out.println("Servlet.Signup::Dispatch to register.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
				dispatcher.forward(request, response);
			}			
		}
		else{
			System.out.println("Servlet.Signup::Email is not available");
			String msg ="An account has been already registered from this email id.";
			request.setAttribute("msg", msg);
			System.out.println("Servlet.Signup::Dispatch to register.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
		}		
	}
}
