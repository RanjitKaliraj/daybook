package com.daybook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daybook.dto.QueryDto;
import com.daybook.service.QueryService;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/query")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] userSession = (String[])request.getSession().getAttribute("user");
		int userId = Integer.parseInt(userSession[0]);
		
		QueryDto queryDto = new QueryDto();
		QueryService userquery = new QueryService();
		
		queryDto.setSubject(request.getParameter("subject"));
		queryDto.setDetails(request.getParameter("message"));
		queryDto.setUserId(userId);
		
		userquery.addUserQuery(queryDto);
		if (queryDto.isQueryAdded()){
			System.out.println("Servlet.query:: query added successfully");
			String msg ="Your message has been sent.";
			request.setAttribute("msg", msg);
			System.out.println("Servlet.Signup::Dispatch to query.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("query.jsp");
			dispatcher.forward(request, response);
		}
		else{
			System.out.println("Servlet.query:: query added is unsuccessful");
			String msg ="Something went wrong.";
			request.setAttribute("msg", msg);
			System.out.println("Servlet.Signup::Dispatch to query.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("query.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
