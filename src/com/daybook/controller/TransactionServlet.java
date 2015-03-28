package com.daybook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daybook.dto.TransactionDto;
import com.daybook.service.TransactionService;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransactionDto transaction = new TransactionDto();
		TransactionService transactionService = new TransactionService();
		String[] userSession = (String[])request.getSession().getAttribute("user");
		int userId = Integer.parseInt(userSession[0]);
		transaction.setUser_Id(userId);
		
			transaction.setTransac_Id(Integer.parseInt(request.getParameter("tid")));
			transaction.setDescription(request.getParameter("description"));
			transaction.setDetail(request.getParameter("detail"));
			transaction.setType(request.getParameter("type"));
			transaction.setSource(request.getParameter("source"));
			double amount = 1.0;		
			try{
				amount = Double.valueOf(request.getParameter("amount"));
				//int newamount = Integer.parseInt(request.getParameter("amount"));
			}
			catch (NumberFormatException e){
				e.printStackTrace();
				System.out.println("Servlet.Transaction::Amount is not number");
				String msg ="Incorrect input in Amount field.";
				request.setAttribute("msg", msg);
				System.out.println("Servlet.Signup::Dispatch to home.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("manage-transaction.jsp");
				dispatcher.forward(request, response);
			}
			String type = transaction.getType();
			if (type.equals("income")){
				transaction.setDebit_Amt(amount);
				transaction.setCredit_Amt(0.0);
				//System.out.println("ttttttttttttttttttttttis debit:"+transaction.getCredit_Amt()+" "+transaction.getDebit_Amt());
			}
			else if (type.equals("expenses")){
				transaction.setCredit_Amt(amount);
				transaction.setDebit_Amt(0.0);
				//System.out.println("ttttttttttttttttttttttis credit:"+transaction.getCredit_Amt()+" "+transaction.getDebit_Amt());
			}
			transactionService.updateTransaction(transaction);
			if (transaction.isTransactionUpdated()){
				System.out.println("Servlet.Transaction:Transaction is successfully updated");
				String msg ="Transaction is successfully updated.";
				request.setAttribute("msg", msg);
				System.out.println("Servlet.Signup::Dispatch to manage-transaction.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("manage-transaction.jsp");
				dispatcher.forward(request, response);
			}
		}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransactionDto transaction = new TransactionDto();
		TransactionService transactionService = new TransactionService();
		
		String[] userSession = (String[])request.getSession().getAttribute("user");
		int userId = Integer.parseInt(userSession[0]);
		String description = request.getParameter("description");
		String detail = request.getParameter("detail");
		String type = request.getParameter("type");
		System.out.println("tttttttttttttttttttttt:"+type);
		String source = request.getParameter("source");
		double amount = 1.0;		
		try{
			amount = Double.valueOf(request.getParameter("amount"));
			//int newamount = Integer.parseInt(request.getParameter("amount"));
		}
		catch (NumberFormatException e){
			e.printStackTrace();
			System.out.println("Servlet.Transaction::Amount is not number");
			String msg ="Incorrect input in Amount field.";
			request.setAttribute("msg", msg);
			System.out.println("Servlet.Signup::Dispatch to home.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}
		transaction.setUser_Id(userId);
		transaction.setDescription(description);
		transaction.setDetail(detail);
		transaction.setType(type);
		transaction.setSource(source);
		if (type.equals("income")){
			transaction.setDebit_Amt(amount);
			transaction.setCredit_Amt(0.0);
			//System.out.println("ttttttttttttttttttttttis debit:"+transaction.getCredit_Amt()+" "+transaction.getDebit_Amt());
		}
		else if (type.equals("expenses")){
			transaction.setCredit_Amt(amount);
			transaction.setDebit_Amt(0.0);
			//System.out.println("ttttttttttttttttttttttis credit:"+transaction.getCredit_Amt()+" "+transaction.getDebit_Amt());
		}		
		transactionService.addTransactionDetail(transaction);
		if (transaction.isTransactionAdded()){
			System.out.println("Servlet.Transaction:Transaction is successfully added");
			String msg ="Transaction is successfully added.";
			request.setAttribute("msg", msg);
			System.out.println("Servlet.Signup::Dispatch to home.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}		
	}
}
