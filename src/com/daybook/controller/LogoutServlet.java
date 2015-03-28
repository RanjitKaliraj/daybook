package com.daybook.controller;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.daybook.service.UserAccount;


/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private UserAccount userAccount;
       
    /**
     * @see HttpServlet#HttpServlet()
     
    public LogoutServlet() {
     //   userAccount = new UserAccount();
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet.Logout::Starting user logout.");
		HttpSession session = request.getSession();
		//String[] user = (String[])session.getAttribute("user");
		//int user_Id = Integer.parseInt(user[0]);
		//if (userAccount.logout(user_Id)){
			//System.out.println("Servlet.Logout::User is logout.");
			request.getSession().setAttribute("user", null);
			System.out.println("Servlet.Logout::Session is set null.");
			session.invalidate();
			System.out.println("Servlet.Logout::Session invalidate");
			String msg ="You have successfully logout.";
			request.setAttribute("msg", msg);
			System.out.println("Servlet.Logout::Dispatch to index page.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		//}
		/*else{
			System.out.println("Servlet.Logout::User logout is not completed.");
			PrintWriter writer = response.getWriter();
			writer.print("Sorry. we are unable to logout successfully at this moment.\n");
			writer.print("Try Again.");
		}*/
	}

}
