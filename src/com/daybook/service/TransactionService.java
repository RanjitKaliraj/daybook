/*
 * This is a TransactionService service layer class.
 * This class handles all the functions related to transaction activity which are: 
 * transaction add, transaction view, edit and delete.
 * 
 * @Author: Ranjit Kaliraj
 * Date: 19th Aug. 2014
 */

package com.daybook.service;

//importing java arrylist
import java.util.ArrayList;














import com.daybook.dao.AmountDao;
//importing daybook classes
import com.daybook.dao.TransactionDao;
import com.daybook.dto.TransactionDto;
import com.daybook.domain.Amount;
import com.daybook.domain.Transaction;
import com.daybook.util.FormattedDate;

public class TransactionService {
	//add transaction view transaction reports
	private TransactionDao transactionDao;
	private Transaction transaction;
	private String date;
	private ArrayList<Transaction> transactionList;
	
	/*
	 * This is addTransactionDetail() method.
	 * this method add transaction details to the database.
	 * This method take Transaction DTO object as parameter which contains transaction data
	 * and then set the transaction add status to true if successfully added else false.
	 */
	public void addTransactionDetail(TransactionDto data){	
		System.out.println("Service:Transaction::Starting to add transaction details.");
		transactionDao = new TransactionDao();
		transaction = new Transaction();
		date = FormattedDate.getDate();
		
		//setting transaction data in Transaction domain object 
		transaction.setUser_Id(data.getUser_Id());
		transaction.setDescription(data.getDescription());
		transaction.setDetail(data.getDetail());
		transaction.setSource(data.getSource());
		transaction.setType(data.getType());
		transaction.setDebit_Amt(data.getDebit_Amt());
		transaction.setCredit_Amt(data.getCredit_Amt());
		transaction.setDate(date);
		
		//calling Data access layer method to store data 
		System.out.println("Service:Transaction::adding transaction");
		transactionDao.addTransaction(transaction);
		if (transaction.isTransactionAdded()){
			data.setTransactionAddStatus(true);
			System.out.println("Service:Transaction::transaction add successful and add status set true.");
		}
		else{
			data.setTransactionAddStatus(false);
			System.out.println("Service:Transaction::transaction add is not successful and add status set false.");
		}
	}
	
	/*
	 * This method returns list of user's transaction.
	 * This method takes Transaction domain object as parameter which contains user Id value
	 * and then return arraylist of user transaction data. 
	 */
	public ArrayList<TransactionDto> displayUserTransaction(int userId){
		System.out.println("Service:Transaction::Starting to display all transaction");
		transactionDao = new TransactionDao();
		transaction = new Transaction();
		transactionList = new ArrayList<Transaction>();
		ArrayList<TransactionDto> transactionDtoList = new ArrayList<TransactionDto>();
		//TransactionDto transactionDto = new TransactionDto();
		
		//setting user id in transaction object
		System.out.println("Service:Transaction::setting user id.");
		transaction.setUser_Id(userId);
		
		//Getting list of transaction and setting in arraylist
		System.out.println("Service:Transaction::getting transaction list");
		transactionList = transactionDao.getTransactionDetail(transaction);
		for (int i=0; i<transactionList.size();i++){
			TransactionDto transaction = new TransactionDto();
			transaction.setTransac_Id(transactionList.get(i).getTransac_Id());
			transaction.setDescription(transactionList.get(i).getDescription());
			transaction.setDetail(transactionList.get(i).getDetail());
			transaction.setSource(transactionList.get(i).getSource());
			transaction.setDebit_Amt(transactionList.get(i).getDebit_Amt());
			transaction.setCredit_Amt(transactionList.get(i).getCredit_Amt());	
			transaction.setDate(transactionList.get(i).getDate());
			transactionDtoList.add(transaction);
			System.out.println("Service:Transaction::555555555555555555:"+i+transaction.getDescription());
		}
		System.out.println("Service:Transaction::returning transaction list");
		return transactionDtoList;		
	}
	
	/*
	 * This method update edited particular transaction detail
	 * This method takes user id and transaction id as parameter and update Transaction DTO updateStatus() method
	 * to boolean true if data is successfully edited else set false
	 */
	public void updateTransaction(TransactionDto data){
		System.out.println("Service:Transaction::Starting to update transaction");
		transactionDao = new TransactionDao();
		transaction = new Transaction();
		
		//setting user id in transaction object
		transaction.setUser_Id(data.getUser_Id());
		transaction.setTransac_Id(data.getTransac_Id());
		transaction.setDescription(data.getDescription());
		transaction.setDetail(data.getDetail());
		transaction.setSource(data.getSource());
		transaction.setType(data.getType());
		transaction.setDebit_Amt(data.getDebit_Amt());
		transaction.setCredit_Amt(data.getCredit_Amt());
		
		//calling DAO method to update edited transaction and setting update status true
		System.out.println("Service:Transaction::updating transaction");
		if (transactionDao.updateTransaction(transaction)){
			data.setTransactionUpdateStatus(true);	//setting update status true
			System.out.println("Service:Transaction::update successful and update status set true");
		}
		else{
			data.setTransactionUpdateStatus(false);	//setting update status false
			System.out.println("Service:Transaction::update is not successful and status update set false");
		}
	}
	
	/*
	 * This method delete particular user transaction
	 * it takes user id and transaction id as parameter and set boolean delete
	 * status either true or false in parameter object's method setDeleteStatus().
	 */
	public void deleteTransaction(TransactionDto data){
		System.out.println("Service:Transaction::Starting deleting single transaction");
		transactionDao = new TransactionDao();
		transaction = new Transaction();
		
		//setting user id in transaction object
		transaction.setUser_Id(data.getUser_Id());
		transaction.setTransac_Id(data.getTransac_Id());
		
		//calling DAO method to delete transaction
		System.out.println("Service:Transaction::deleting transaction");
		if (transactionDao.deleteUserTransaction(transaction)){
			data.setTransactionDeleteStatus(true);
			System.out.println("Service:Transaction::delete successful and delete status set true.");
		}
	}
	
	/*
	 * This method delete all transaction details of user
	 * it takes user id as parameter and return boolean delete status either true or false.
	 */
	public boolean deleteAllTransaction(int userId){
		System.out.println("Service:Transaction::Starting deleting all transaction");
		boolean status = false;
		transactionDao = new TransactionDao();
		System.out.println("Service:Transaction::deleting transaction");
		if (transactionDao.deleteAllTransaction(userId)){
			status = true;
			System.out.println("Service:Transaction::delete succssful and delete status set true");
		}
		System.out.println("Service:Transaction::returning delete status");
		return status;		
	}
	
	public void getAmount(TransactionDto data){
		AmountDao amountDao = new AmountDao();
		Amount amount = new Amount();
		amount.setUser_Id(data.getUser_Id());
		System.out.println("Service:Transaction::returning delete status:"+amount.getUser_Id());
		amountDao.getDebitCredit(amount);
		data.setCredit_Amt(amount.getTotal_Credit());
		data.setDebit_Amt(amount.getTotal_Debit());
		data.setTotalAmt(amount.getTotal_Amt());
		System.out.println("Service:Transaction::amount details: Credit:"+data.getCredit_Amt()+"debit:"+data.getDebit_Amt()+"Total:"+data.getTotalAmt());
	}
}
