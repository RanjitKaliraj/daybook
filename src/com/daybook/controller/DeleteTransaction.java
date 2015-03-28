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
 * Servlet implementation class DeleteTransaction
 */
@WebServlet("/delete-transaction")
public class DeleteTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Integer transaction_Id1 = Integer.parseInt(request.getParameter("edit-id"));
		TransactionDto transaction = new TransactionDto();
		TransactionService transactionService = new TransactionService();
		
		Integer delete_Id = 0;
		try{
		delete_Id = Integer.parseInt(request.getParameter("delete-id"));
		}
		catch(NumberFormatException e){
			//e.printStackTrace();
			System.out.println("Servlet::Delete::since delete all action made. delete single id variable is null so number format exception occured");
		}
		
		
		String deleteall = request.getParameter("action");
		System.out.println("Servlet.delete:: ACTION"+deleteall);
		
		String[] userSession = (String[])request.getSession().getAttribute("user");
		int userId = Integer.parseInt(userSession[0]);
		transaction.setUser_Id(userId);
		
		if(!delete_Id.equals(0)){
			System.out.println("Servlet.delete::action is single delete");
			int id = delete_Id;
			transaction.setTransac_Id(id);
			System.out.println("Servlet.delete:: deleteid and user id:"+id+" "+transaction.getTransac_Id());
			System.out.println("Servlet.delete::calling method to delete");
			transactionService.deleteTransaction(transaction);
			if (transaction.isTransactionDeleted()){
				System.out.println("Servlet.delete:Transaction is successfully deleted");
				String msg ="Transaction is successfully deleted.";
				request.setAttribute("msg", msg);
				System.out.println("Servlet.Signup::Dispatch to manage-transaction.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("manage-transaction.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if(deleteall.equals("delete-all")){
			if (transactionService.deleteAllTransaction(userId)==true){
				System.out.println("Servlet.delete:All Transaction is successfully deleted");
				String msg ="ALl transaction is successfully deleted.";
				request.setAttribute("msg", msg);
				System.out.println("Servlet.Signup::Dispatch to manage-transaction.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("manage-transaction.jsp");
				dispatcher.forward(request, response);
			}
			else{
				System.out.println("Servlet.delete:There is nothing to delete");
				String msg ="Transaction is already empty.";
				request.setAttribute("msg", msg);
				System.out.println("Servlet.Signup::Dispatch to manage-transaction.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("manage-transaction.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
