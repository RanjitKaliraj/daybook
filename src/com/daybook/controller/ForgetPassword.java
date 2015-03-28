package com.daybook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daybook.dto.ProfileDto;
import com.daybook.service.UserAccount;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/recovery")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("forget-password.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet.forgetPassword: starting forgetpassword servlet");
		ProfileDto profileDto = new ProfileDto();
		UserAccount userAccount = new UserAccount();
		int userId = 0;
		profileDto.setEmail(request.getParameter("email"));
		
		if (userAccount.checkEmailAvailability(profileDto)){
			System.out.println("Servlet.forgetPassword:: email id matched");
			userId = profileDto.getUser_Id();
			System.out.println("Servlet.forgetPassword:: getting user id");
			
			if (userId!=0){
				userAccount.sendActivationCode(userId);
				String user_id = Integer.toString(userId);
				String page1 = "activate";
				String page2="";
				String[] signup = {user_id, page1, page2};
				System.out.println("Servlet.forgetpassword::Setting signup session");
				request.getSession().setAttribute("signup", signup);
				System.out.println("Servlet.forgetpassword::Dispatch to activate.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("activate.jsp");
				dispatcher.forward(request, response);
			}
			else{
				response.sendRedirect("forget-password.jsp");
				return;
			}
		}
		else{
			System.out.println("Servlet.forgetPassword:: incorrect email");
			String msg ="The email address is not yet registered.";
			request.setAttribute("message", msg);
			System.out.println("Servlet.Signup::Dispatch to forget-password.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("forget-password.jsp");
			dispatcher.forward(request, response);
		}
	}

}
