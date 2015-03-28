package com.daybook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daybook.dto.LoginDto;
import com.daybook.service.UserAccount;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccount userAccount;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
    	userAccount = new UserAccount();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("index.jsp");
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet.Login::Starting servlet login check");
		LoginDto loginDto = new LoginDto();
		
		loginDto.setUsername(request.getParameter("username"));
		loginDto.setPassword(request.getParameter("password"));
		System.out.println("Servlet.Login::Calling login check method from servlet");
		userAccount.loginCheck(loginDto);

		int id = loginDto.getUser_Id();
		System.out.println("Servlet.Login::user id is:"+id);
		
		if (loginDto.isAccountBlocked()){
			System.out.println("Servlet.Login::Account is blocked");
			String msg = "This account is blocked.\n Contact admin.";
			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		else if (loginDto.getLogin_Status()){
			System.out.println("Servlet.Login::Login was successful");
			
			if(loginDto.isAccountActivated()!=true){
				System.out.println("Servlet.Login::Account is not activated");
				System.out.println("Servlet.Login::");
				String user_id = Integer.toString(id);
				String page1 = "activate";
				String page2="";
				String[] signup = {user_id, page1, page2};
				System.out.println("Servlet.Login::Setting session.");
				request.getSession().setAttribute("signup", signup);
				System.out.println("Servlet.Login::Alert: account is not activated. redirect to activate page");
				RequestDispatcher dispatcher = request.getRequestDispatcher("activate.jsp");
				dispatcher.forward(request, response);
			}
			else if(loginDto.isBankInfoAvailable()==false){
				String user_id = Integer.toString(id);
				String page1 = "";
				String page2="setup";
				String[] signup = {user_id, page1, page2};
				System.out.println("Servlet.Login::Setting session.");
				request.getSession().setAttribute("signup", signup);
				System.out.println("Servlet.Login::Bank info is not completed. redirect to setup page");
				RequestDispatcher dispatcher = request.getRequestDispatcher("first-time-setup.jsp");
				dispatcher.forward(request, response);
			}
			else{
				String user_Id = Integer.toString(id);
				String fname = loginDto.getFname();
				String lname = loginDto.getLname();
				String[] user = {user_Id, fname, lname};
				System.out.println("Servlet.Login::Setting session for userid, and name.");
				request.getSession().setAttribute("user", user);
				System.out.println("Servlet.Login::Login successful. redirecting to home page.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
				dispatcher.forward(request, response);
			}
		}
		else{
			System.out.println("Servlet.Login::Setting session for userid, and name.");
			String msg = "Invalid Username/Password.";
			request.setAttribute("msg", msg);
			response.sendRedirect("index.jsp");
		}
	}
}
