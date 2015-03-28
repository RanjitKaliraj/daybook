package com.daybook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daybook.service.UserAccount;

/**
 * Servlet implementation class SendActivationServlet
 */
@WebServlet("/send-activation-code")
public class SendActivationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccount userAccount;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendActivationServlet() {
        super();
        userAccount = new UserAccount();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet.SendActivation::Starting sending activation code.");
		String[] userArray = (String[])request.getSession().getAttribute("user");
		String[] signupArray = (String[])request.getSession().getAttribute("signup");
		
		System.out.println("Servlet.SendActivation::getting user id from session.");
		int user_Id = 0;
		if (userArray!=null){
			System.out.println("Servlet.SendActivation::Session is set from login page.");
			user_Id = Integer.parseInt(userArray[0]);
		}
		else if(signupArray!=null){
			System.out.println("Servlet.SendActivation::Session is set from signup page");
			user_Id = Integer.parseInt(signupArray[0]);
		}
		System.out.println("Servlet.SendActivation::Calling method to send activation code.");
		if (userAccount.sendActivationCode(user_Id)){
			System.out.println("Servlet.SendActivation::Activation code sent.");
			String msg = "Activation Code sent.\n Please, check your email.";
			request.setAttribute("msg", msg);
			System.out.println("Servlet.SendActivation::dispatch to activate page.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("activate.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
