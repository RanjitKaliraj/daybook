package com.daybook.dao;

import java.sql.*;

import com.daybook.domain.Amount;
import com.daybook.util.DbConnection;

public class AmountDao {
	private Connection connection;
	
	public AmountDao() {
		connection = DbConnection.getConnection();
	}

	//This method calculate the total debit and credit amount of user's transaction amounts
	//This method is called is updateDebitCredit(int id)
	public Amount calculateTotalAmount(int id){
		System.out.println("Dao.Amount::starting to calculate total amount");
		Amount amount = new Amount();
		try{
			String query1 = "SELECT SUM(transac_in) AS total_in FROM db_transac_info WHERE db_usr_id=?";
			String query2 = "SELECT SUM(transac_out) AS total_out FROM db_transac_info WHERE db_usr_id=?";
			PreparedStatement statement1 = connection.prepareStatement(query1);
			PreparedStatement statement2 = connection.prepareStatement(query2);
			statement1.setInt(1, id);
			statement2.setInt(1, id);
			ResultSet result1 = statement1.executeQuery();
			ResultSet result2 = statement2.executeQuery();
			if 	(result1.next()){
				amount.setTotal_Debit(result1.getDouble("total_in"));
				System.out.println("Dao.Amount::debit total amount calculated:"+amount.getTotal_Debit());
			}
			if (result2.next()){
				amount.setTotal_Credit(result2.getDouble("total_out"));
				System.out.println("Dao.Amount::credit total amount calculated:"+amount.getTotal_Credit());
			}
			System.out.println("Dao.Amount::database disconnected.");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Amount::error while calculating amount");
		}
		System.out.println("Dao.Amount::returning amount details.");
		return amount;
	}
	
	//This method sets the total debit credit amount in the database
	//This method is called in getDebitCredit(int id)
	public void updateDebitCredit(int id){
		System.out.println("Dao.Amount::Starting to update debit/credit amount");
		Amount amount = this.calculateTotalAmount(id);
		double total = (amount.getTotal_Debit())-(amount.getTotal_Credit());
		System.out.println("Dao.Amount::"+total);
		try{
			String query = "UPDATE total_amt SET debit=?, credit=?, total=? WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDouble(1,amount.getTotal_Debit());
			statement.setDouble(2, amount.getTotal_Credit());
			statement.setDouble(3, total);
			statement.setInt(4, id);
			statement.executeUpdate();
			System.out.println("Dao.Amount::Amount updated successfully.");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Amount::error while updating amount");
		}
	}
	
	//This method gets the total debit credit amount from the database
		public void getDebitCredit(Amount amount){
			System.out.println("Dao.Amount::Starting to get debit/credit amount");
			int id = amount.getUser_Id();
			this.updateDebitCredit(id);
			try{
				String query = "SELECT * FROM total_amt WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setDouble(1,id);
				System.out.println("Dao.Amount::getting amount from database");
				ResultSet result = statement.executeQuery();
				if (result.next()){
					amount.setUser_Id(id);
					amount.setTotal_Debit(result.getDouble(2));
					amount.setTotal_Credit(result.getDouble(3));
					amount.setTotal_Amt(result.getDouble(4));
					System.out.println("Dao.Amount::Amount successfully get.");
				}
			}
			catch (SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Amount::error while getting amount");
			}
		}
	
	//This method adds user id in the Amount database.
	// and this method is called by the User DAO when all user details are added first time
	public void addUserId(int id){
		System.out.println("Dao.Amount::Starting to add user id in amount database table.");
		if (this.checkId(id)==false){
			try{
				String query = "INSERT INTO total_amt (db_usr_id, debit, credit, total) VALUES (?,?,?,?)";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				statement.setDouble(2, 0);
				statement.setDouble(3, 0);
				statement.setDouble(4, 0);
				statement.executeUpdate();
				System.out.println("Dao.Amount::user id added successfully");
				DbConnection.close_Connection(connection);
				System.out.println("Dao.Amount::database disconnected.");
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Amount::error while adding user id.");
			}
		}
	}
	
	//this method checks if the user id is already added in this table database or not
	//This method is called in addUserId(int id) method
	public boolean checkId(int id){
		System.out.println("Dao.Amount::Starting to check user id");
		boolean status = false;
		try{
			String query = "SELECT * FROM total_amt WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				System.out.println("Dao.Amount::user id check successful.");
				status = true;
			}
			else{
				System.out.println("Dao.Amount::user id check unsuccessful.");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Amount::error while checking user id.");
		}
		System.out.println("Dao.Amount::returning user id check status");
		return status;
	}
	
	//This method delete the user amount details.
	public boolean deleteAccount(int userId){
		System.out.println("Dao.Amount::Starting deleting user details in amount database");
		boolean deleteStatus = false;
		try{
			String query = "DELETE FROM total_amt WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			if (statement.executeUpdate()==1){
				deleteStatus = true;
				System.out.println("Dao.Amount::delete successful.");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Amount::error while deleting");
		}		
		System.out.println("Dao.Amount::returning delete status.");
		return deleteStatus;
	}
}
