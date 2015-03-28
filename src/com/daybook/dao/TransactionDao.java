package com.daybook.dao;

import java.sql.*;
import java.util.ArrayList;

import com.daybook.domain.Transaction;
import com.daybook.util.DbConnection;

public class TransactionDao {
	private Connection connection;
	private ArrayList<Transaction> all_Transaction;
	
	public TransactionDao() {
		connection = DbConnection.getConnection();
		all_Transaction = new ArrayList<Transaction>();
	}
	
	//This method add transaction to the database
	public void addTransaction(Transaction trans){
		System.out.println("Dao.Transaction::Starting to add transaction");
		try{
			String query = "INSERT INTO db_transac_info (db_usr_id, transac_desc,db_transac_detail, transac_source,transac_type, transac_in, transac_out, transac_date) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, trans.getUser_Id());
			statement.setString(2, trans.getDescription());
			statement.setString(3, trans.getDetail());
			statement.setString(4, trans.getSource());
			statement.setString(5, trans.getType());
			statement.setDouble(6, trans.getDebit_Amt());
			statement.setDouble(7, trans.getCredit_Amt());
			//statement.setDouble(6, trans.getTotal());
			statement.setString(8, trans.getDate());
			if (statement.executeUpdate()==1){
				System.out.println("Dao.Transaction::add successful.");
				trans.setTransactionAddStatus(true);
				System.out.println("Dao.Transaction::add status set true.");
			}
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Transaction::database disconnected");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Transaction::error while adding transaction");
		}
	}
	
	//This method retrieve transaction information from the database per user
	public ArrayList<Transaction> getTransactionDetail(Transaction trans){
		System.out.println("Dao.Transaction::Starting to get transactin details.");
		try{
			String query = "SELECT * FROM db_transac_info WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, trans.getUser_Id());
			ResultSet result = statement.executeQuery();
			System.out.println("Dao.Transaction::getting list of all transaction");
			while (result.next()){
				Transaction transaction = new Transaction();
				transaction.setTransac_Id(result.getInt(1));
				transaction.setDescription(result.getString(3));
				transaction.setDetail(result.getString(4));
				transaction.setSource(result.getString(5));
				transaction.setDebit_Amt(result.getDouble(7));
				transaction.setCredit_Amt(result.getDouble(8));
				transaction.setDate(result.getString(9));
				all_Transaction.add(transaction);
				System.out.println("Dao.Transaction::5555555555555 transaction add."+trans.getDescription());
			}
			for (int i=0; i<all_Transaction.size();i++){
				System.out.println("apple: "+all_Transaction.get(i).getDescription());
			}
			System.out.println("Dao.Transaction::all transaction get.");
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Transaction::database disconnected");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Transaction::error while getting transaction");
		}
		System.out.println("Dao.Transaction::returning transaction details.");
		return all_Transaction;			
	}
	
	//This method edit the information of particular transaction.
	public boolean updateTransaction(Transaction transaction){
		System.out.println("Dao.Transaction::Starting to update transaction ");
		boolean update = false;
		try{
			String query = "UPDATE db_transac_info SET transac_desc=?,db_transac_detail=?, transac_source=?,transac_type=?, transac_in=?, transac_out=? WHERE db_usr_id=? AND db_transac_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, transaction.getDescription());
			statement.setString(2, transaction.getDetail());
			statement.setString(3, transaction.getSource());
			statement.setString(4, transaction.getType());
			statement.setDouble(5, transaction.getDebit_Amt());
			statement.setDouble(6, transaction.getCredit_Amt());
			statement.setDouble(7, transaction.getUser_Id());
			statement.setDouble(8, transaction.getTransac_Id());
			if (statement.executeUpdate()==1){
				update = true;
				System.out.println("Dao.Transaction::update successful.");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Transaction::error while updating");
		}
		System.out.println("Dao.Transaction::returning update status.");
		return update;
	}
	
	//This method deletes the particular transaction
	public boolean deleteUserTransaction(Transaction transaction){
		System.out.println("Dao.Transaction::Starting to delete user transaction.");
		boolean deleteStatus = false;
		try{
			String query = "DELETE FROM db_transac_info WHERE db_usr_id=? AND db_transac_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, transaction.getUser_Id());
			statement.setInt(2, transaction.getTransac_Id());
			if (statement.executeUpdate()==1){
				deleteStatus = true;
				System.out.println("Dao.Transaction::delete successful.");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Transaction::error while deleting");
		}		
		System.out.println("Dao.Transaction::returning delete status.");
		return deleteStatus;
	}
	
	//This method delete the all user transaction details of particular user.
	public boolean deleteAllTransaction(int userId){
		System.out.println("Dao.Transaction::Starting to delete all transaction:"+userId);
		boolean deleteStatus = false;
		try{
			String query = "DELETE FROM db_transac_info WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			if (statement.executeUpdate()==1){
				deleteStatus = true;
				System.out.println("Dao.Transaction::delete successful");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Transaction::error while deleting all transaction");
		}		
		System.out.println("Dao.Transaction::returning delete status.");
		return deleteStatus;
	}
}

