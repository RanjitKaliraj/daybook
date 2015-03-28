package com.daybook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.daybook.domain.Recovery;
import com.daybook.util.DbConnection;

public class RecoveryDao {
	private Connection connection;
	
	public RecoveryDao() {
		connection = DbConnection.getConnection();
	}
	
	//This method store the recovery question and answer
	public void addRecoveryInfo(Recovery recovery_Info){
		try{
			String query = "INSERT INTO db_recov_tb (db_usr_id, db_scr_qn, db_scr_ans) VALUE (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, recovery_Info.getUser_Id());
			statement.setString(2, recovery_Info.getQuestion());
			statement.setString(3, recovery_Info.getAnswer());
			if (statement.executeUpdate()==1){
				recovery_Info.setAddStatus(true);
			}
			DbConnection.close_Connection(connection);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	//This method update the recovery question and answer
		public void updateRecoveryInfo(Recovery recovery_Info){
			try{
				String query = "UPDATE db_recov_tb SET db_scr_qn=?, db_scr_ans=? WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, recovery_Info.getQuestion());
				statement.setString(2, recovery_Info.getAnswer());
				statement.setInt(3, recovery_Info.getUser_Id());
				if (statement.executeUpdate()==1){
					recovery_Info.setUpdateStatus(true);
				}
				DbConnection.close_Connection(connection);
			}
			catch (SQLException e){
				e.printStackTrace();
			}
		}
	
	//this function get the recovery information of particular user
	public void getRecoveryInfo(Recovery info){
		try {
			String query = "SELECT * FROM db_recov_tb WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, info.getUser_Id());
			ResultSet result = statement.executeQuery();
			if (result.next()){
				info.setQuestion(result.getString("db_scr_qn"));
				info.setAnswer(result.getString("db_scr_ans"));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		DbConnection.close_Connection(connection);
	}
	
	//This method delete the user profile of particular user.
	public boolean deleteAccount(int userId){
		boolean deleteStatus = false;
		try{
			String query = "DELETE FROM db_recov_tb WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			if (statement.executeUpdate()==1){
				deleteStatus = true;
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}		
		return deleteStatus;
	}
	
	/*
	 * This method checks if the user recovery info are present or not
	 * This method is useful in order to avoid user login to home with out completing recovery info
	 * This method takes user id as parameter and returns boolean true or false.
	 */
	public boolean checkUser(int userId){
		boolean isUserAvailable = true;
		try{
			String query="SELECT * FROM db_recov_tb WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			if (statement.executeUpdate()==0){
				isUserAvailable = false;
			}		
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return isUserAvailable;
	}
}
