package com.daybook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.daybook.domain.Bank;
import com.daybook.util.DbConnection;

public class BankDao {
	private Connection connection;

	public BankDao() {
		connection = DbConnection.getConnection();
	}
	
	//This function store the user's bank information in the database
	public void addBankInfo(Bank bank){
		System.out.println("Dao.Bank::Starting to add bank information.");
		System.out.println("Dao.Bank::Checking if bank details are already present or not.");
		if (this.checkUserBankInfoAvailability(bank.getUser_Id())==false){			
			try{
				String query = "INSERT INTO db_bnk_details (db_usr_id, db_bnk_name, db_bnk_no) VALUES (?,?,?)";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, bank.getUser_Id());
				statement.setString(2, bank.getName());
				statement.setString(3, bank.getNumber());
				if (statement.executeUpdate()==1){
					System.out.println("Dao.Bank::bank details added successfully.");
					System.out.println("Dao.Bank::setting bank added status true.");
					bank.setDataAddStatus(true);
				}
				else{
					System.out.println("Dao.Bank::setting added status false.");
					bank.setDataAddStatus(false);
				}
				DbConnection.close_Connection(connection);
				System.out.println("Dao.Bank::database disconnected.");
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Bank::error while adding bank info.");
			}
		}
		else{
			bank.setBankDetailAlreadyAdded(true);
		}
	}
	
	//This function update the user's bank information in the database
		public void updateBankInfo(Bank bank){
			System.out.println("Dao.Bank::Starting to updata bank info.");
			try{
				String query = "UPDATE db_bnk_details SET db_bnk_name=?, db_bnk_no=? WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, bank.getName());
				statement.setString(2, bank.getNumber());
				statement.setInt(3, bank.getUser_Id());
				
				if (statement.executeUpdate()==1){
					System.out.println("Dao.Bank::update successful.");
					bank.setDataAddStatus(true);
					System.out.println("Dao.Bank::update status set true");
				}
				else{
					bank.setDataAddStatus(false);
					System.out.println("Dao.Bank::update status set false");
				}
				DbConnection.close_Connection(connection);
				System.out.println("Dao.Bank::database disconnected");
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Bank::error while updating");
			}
		}
		
	//This function get the bank info of particular user
	public Bank getBankInfo(Bank bankinfo){
		System.out.println("Dao.Bank::Starting to get bank info.");
		try{
			String query = "SELECT * FROM db_bnk_details WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, bankinfo.getUser_Id());
			ResultSet result = statement.executeQuery();
			if (result.next()){
				bankinfo.setName(result.getString("db_bnk_name"));
				bankinfo.setNumber(result.getString("db_bnk_no"));
				System.out.println("Dao.Bank::successfully get bank info.");
			}
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Bank::database disconnected");
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Bank::error while getting bank info");
		}
		System.out.println("Dao.Bank::returning bank info details");
		return bankinfo;
	}
	
	//This method delete the user profile of particular user.
	public boolean deleteAccount(int userId){
		System.out.println("Dao.Bank::Starting to delete user details in bank database table");
		boolean deleteStatus = false;
		try{
			String query = "DELETE FROM db_bnk_details WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			if (statement.executeUpdate()==1){
				deleteStatus = true;
				System.out.println("Dao.Bank::delete successful.");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Bank::error while deleting ");
		}	
		System.out.println("Dao.Bank::returning delete status");
		return deleteStatus;
	}
	
	/*
	 * This method checks if the user bank info are present or not
	 * This method is useful in order to avoid user login to home with out completing bank info
	 * This method takes user id as parameter and returns boolean true or false.
	 */
	public boolean checkUserBankInfoAvailability(int userId){
		System.out.println("Dao.Bank::Starting to check user bank info is present or not.");
		boolean isUserAvailable = false;
		try{
			String query="SELECT * FROM db_bnk_details WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				isUserAvailable = true;
				System.out.println("Dao.Bank::user bank detail is not available");
			}		
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Bank::error while checking");
		}
		System.out.println("Dao.Bank::returning status of unavailability.");
		return isUserAvailable;
	}
}
