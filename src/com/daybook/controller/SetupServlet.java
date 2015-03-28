package com.daybook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daybook.dto.ProfileDto;
import com.daybook.dto.SetupDto;
import com.daybook.service.UserAccount;
import com.daybook.service.UserProfile;

/**
 * Servlet implementation class SetupServlet
 */
@WebServlet("/setup")
public class SetupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccount userAccount;
	private UserProfile userProfile;
	
	public SetupServlet() {
		super();
		userAccount = new UserAccount();
		userProfile = new UserProfile();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet.Setup::Starting setup user account");
		SetupDto setupDto = new SetupDto();
		ProfileDto profileDto = new ProfileDto();
		
		System.out.println("Servlet.Setup::getting user id from session.");
		String[] array = (String[])request.getSession().getAttribute("signup");
		System.out.println("Servlet.Setup::Setting userid and bank info in setup DTO");
		setupDto.setUser_Id(Integer.parseInt(array[0]));
		setupDto.setBank_Name(request.getParameter("bank_name"));
		setupDto.setBank_No(request.getParameter("account_no"));
		
		System.out.println("Servlet.Setup::Calling method to setup account");
		userAccount.AccountSetup(setupDto);
		if(setupDto.isBankDetailAdded()|| setupDto.isBankDetailAlreadyAdded() ){
			System.out.println("Servlet.Setup::Account is setup or already setup");
			request.getSession().setAttribute("signup", null);
			request.getSession().invalidate();
			System.out.println("Servlet.Setup::Previous session invalidated");
			
			int id = setupDto.getUser_Id();
			profileDto.setUser_Id(id);
			
			System.out.println("Servlet.Setup::Calling method getUserProfile()");
			userProfile.getUserProfile(profileDto);
			String user_id = Integer.toString(id);
			String first_Name = profileDto.getF_name();
			String last_Name = profileDto.getL_name();
			
			String[] user = {user_id, first_Name, last_Name };
			System.out.println("Servlet.Setup::setting new session containing userid, first and last name");
			request.getSession().setAttribute("user", user);
			System.out.println("Servlet.Setup::Dispatch to home.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}
		else{
			System.out.println("Servlet.Setup::Error while setting user account");
			String msg = "Something went wrong. Please, try again.";
			request.setAttribute("msg", msg);
			System.out.println("Servlet.Setup::Dispatch to first-time-setup.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("first-time-setup.jsp");
			dispatcher.forward(request, response);
		}
	}

}
