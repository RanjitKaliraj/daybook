package com.daybook.dao;

import java.sql.*;

import com.daybook.domain.Admin;
import com.daybook.util.DbConnection;

public class AdminDao {
	private Connection connection;
	
	public AdminDao() {
		connection = DbConnection.getConnection();
	}

	//This method adds user id in the admin table in database.
	// and this method is called by the User DAO when the user details are 
	//added in the database and user id is created in the users table.
	public void addUserId(int id){
		System.out.println("Dao.Admin::Starting to add user id in admin database table.");
		if (this.checkId(id)==false){
			try{
				String query = "INSERT INTO admin_ctrl (db_usr_id, block_fn1, block_fn2, block_fn3) VALUES (?,?,?,?)";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				statement.setBoolean(2, false);
				statement.setBoolean(3, false);
				statement.setBoolean(4, false);
				statement.executeUpdate();
				System.out.println("Dao.Admin::user id added successfully");
				DbConnection.close_Connection(connection);
				System.out.println("Dao.Admin::database disconneted");
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Admin::error while adding user id.");
			}
		}
	}
	
	//this method checks if the user id is already added in the database
	//this method is called in addUserId() method
	public boolean checkId(int id){
		System.out.println("Dao.Admin::Starting to check user id.");
		boolean status = false;
		try{
			String query = "SELECT * FROM admin_ctrl WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				System.out.println("Dao.Admin::user id check successful.");
				status = true;
			}
			else{
				System.out.println("Dao.Admin::user id check unsuccessful.");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Admin::error while checking user id.");
		}
		System.out.println("Dao.Admin::returning user check id  status.");
		return status;
	}
	
	//This method block the feature of the user.
	public void blockUserFunction(Admin admin){
		System.out.println("Dao.Admin::Starting to block user function.");
		if (admin.isFn1_block()==true){   //blocking function 1
			try{
				String query = "UPDATE admin_ctrl SET block_fn1=? WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setBoolean(1, true);
				statement.setInt(2, admin.getUser_Id());
				if (statement.executeUpdate()==1){
					admin.setFn1_blockStatus(true);
					System.out.println("Dao.Admin::user function 1 blocked");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Admin::error while blocking function1");
			}
		}		
		if (admin.isFn2_block()==true){		//blocking function 2
			try{
				String query = "UPDATE admin_ctrl SET block_fn2=? WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setBoolean(1, true);
				statement.setInt(2, admin.getUser_Id());
				if (statement.executeUpdate()==1){
					admin.setFn2_blockStatus(true);
					System.out.println("Dao.Admin::user function 2 blocked ");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Admin::error while blocking");
			}
		}		
		if (admin.isFn3_block()==true){		//blocking function 3
			try{
				String query = "UPDATE admin_ctrl SET block_fn3=? WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setBoolean(1, true);
				statement.setInt(2, admin.getUser_Id());
				if (statement.executeUpdate()==1){
					admin.setFn3_blockStatus(true);
					System.out.println("Dao.Admin::user function 3 blocked");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Admin::error while blocking");
			}
		}
		DbConnection.close_Connection(connection);	
		System.out.println("Dao.Admin::database disconnected.");
	}
	
	//This method unblock the features of the user.
	public void unblockUserFunction(Admin admin){
		System.out.println("Dao.Admin::Starting to unblock user function.");
		if (admin.isFn1_blockStatus()==false){   //unblocking function 1
			try{
				String query = "UPDATE admin_ctrl SET block_fn1=? WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setBoolean(1, false);
				statement.setInt(2, admin.getUser_Id());
				if (statement.executeUpdate()==1){
					admin.setFn1_unblockStatus(false);
					System.out.println("Dao.Admin::user function 1 unblocked");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Admin::error while unblocking");
			}
		}		
		if (admin.isFn2_blockStatus()==false){		//unblocking function 2
			try{
				String query = "UPDATE admin_ctrl SET block_fn2=? WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setBoolean(1, false);
				statement.setInt(2, admin.getUser_Id());
				if (statement.executeUpdate()==1){
					admin.setFn2_unblockStatus(false);
					System.out.println("Dao.Admin::user function 2 unblocked");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Admin::error while unblocking");
			}
		}		
		if (admin.isFn3_blockStatus()==false){		//unblocking function 3
			try{
				String query = "UPDATE admin_ctrl SET block_fn3=? WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setBoolean(1, false);
				statement.setInt(2, admin.getUser_Id());
				if (statement.executeUpdate()==1){
					admin.setFn3_unblockStatus(false);
					System.out.println("Dao.Admin::user function 3 unblocked");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Admin::error while unblocking");
			}
		}
		DbConnection.close_Connection(connection);	
		System.out.println("Dao.Admin::database disconneted");
	}
	
	//This method delete the information of user in the admin
	public boolean deleteAccount(int userId){
		System.out.println("Dao.Admin::Starting to delete account in admin");
		boolean deleteStatus = false;
		try{
			String query = "DELETE FROM admin_ctrl WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			if (statement.executeUpdate()==1){
				deleteStatus = true;
				System.out.println("Dao.Admin::delete successful");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Admin::error while deleting");
		}		
		System.out.println("Dao.Admin::returning delete status");
		return deleteStatus;
	}

}
