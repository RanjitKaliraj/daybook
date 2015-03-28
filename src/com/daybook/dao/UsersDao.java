package com.daybook.dao;

//importing java library class
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//importing daybook classes
import com.daybook.domain.Users;
import com.daybook.util.DbConnection;

public class UsersDao {
	private Connection connection;
	private ArrayList<Users> users;
	private int userId;
	
	
	public UsersDao(){
		connection = DbConnection.getConnection();	
	}
	
	//this method add the user details in the database
	public void addUserDetails(Users user){	
		System.out.println("Dao.Users::Starting to add user details.");
		try{
			String query = "INSERT INTO db_usrs_info (db_f_name, db_l_name, db_email_id, join_date, activation_id,activation_status) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getFirst_Name());
			statement.setString(2, user.getLast_Name());
			statement.setString(3, user.getEmail_Id());
			statement.setString(4, user.getJoin_Date());
			statement.setInt(5, user.getAct_no());
			statement.setBoolean(6, false);
			statement.executeUpdate();
			System.out.println("Dao.Users::user details successfully added.");
			
			//getting the user id of recently added user
			System.out.println("Dao.Users::Getting user Id.");
			int id = this.getUserId(user);
			user.setUser_Id(id); 
			
			user.setDetailAddedStatus(true);
			System.out.println("Dao.Users::Setting user detail added status true.");
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Users::database disconnected.");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Users::Error occured while adding user details.");
		}		
	}
	
	//this method update the user details in the database
	public void updateUserDetails(Users user){		
		System.out.println("Dao.Users::Starting update user details");
		try{
			String query = "UPDATE db_usrs_info SET db_f_name=?, db_l_name=?, db_email_id=? WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getFirst_Name());
			statement.setString(2, user.getLast_Name());
			statement.setString(3, user.getEmail_Id());
			statement.setInt(4, user.getUser_Id());
			statement.executeUpdate();
			 
			user.setDetailAddedStatus(true);
			System.out.println("Dao.Users::detail added successfully");
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Users::database disconnected.");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Users::error occured while adding details");
		}		
	}
	
	/*
	 * this method activate the user account
	 * THis method resets the activation id to 0 and status to active.
	 * it returns true if activation code successfully reset else false
	 * This method is invoked after user successfully activated in order to avoid 
	 * activate their account again if they follow the link again.
	 */
	public boolean activateAccount(int userId){
		boolean activationStatus = false;
		System.out.println("Dao.Users::Starting account activation.");
		try{
			String query = "UPDATE db_usrs_info SET activation_id=?, activation_status=? WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, 0);
			statement.setBoolean(2, true);
			statement.setInt(3, userId);
			if (statement.executeUpdate()==1){
				activationStatus = true;
				System.out.println("Dao.Users::Account activated successfully");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Users::error occured while activating account");
		}

		System.out.println("Dao.Users::returning account activation status");
		return activationStatus;
	}
	
	/*
	 * This function sets activation id in the database
	 * this method takes user id as parameter and return boolean true or false
	 */
	public boolean setActivationId(int activation_id, int user_Id){
		boolean status = false;
		System.out.println("Dao.Users::Starting setting account activation id.");
		try{
			String query = "UPDATE db_usrs_info SET activation_id=?, activation_status=? WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, activation_id);
			statement.setBoolean(2, false);
			statement.setInt(3, user_Id);
			if (statement.executeUpdate()==1){
				status = true;
				System.out.println("Dao.Users::Account activated id set");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Users::error occured while activating account");
		}

		System.out.println("Dao.Users::returning account activation id set status");
		return status;
	}
	
	
	//This method return the user id of the user
	//this method takes user object variable as parameter which must includes first name, last name and email id.
	//This method is called in addUserDetail() method
	public int getUserId(Users user){
		System.out.println("Dao.Users::Starting to get user id.");
		try{
			String query = "SELECT db_usr_id FROM db_usrs_info WHERE db_f_name=? AND db_l_name=? AND db_email_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getFirst_Name());
			statement.setString(2, user.getLast_Name());
			statement.setString(3, user.getEmail_Id());
			ResultSet result = statement.executeQuery();
			if (result.next()){
				userId = result.getInt("db_usr_id");
				System.out.println("Dao.Users::Successfully got user id.");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Users::Error while getting user Id.");
		}

		System.out.println("Dao.Users::returning user id.");
		return userId;
	}
	
	/*
	 * This function gets user email id from the database
	 * this function takes user id as parameter and return email id.
	 */
	public String getEmailId(int user_Id){
		System.out.println("Dao.Users::Starting getting email id.");
		String email = null;
		try{
			String query = "SELECT db_email_id FROM db_usrs_info WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, user_Id);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				email = result.getString("db_email_id");
				System.out.println("Dao.Users::Successfully got email id.");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Users::Error while getting user Id.");
		}
		System.out.println("Dao.Users::Successfully returning email id."+email+user_Id);
		return email;
	}
	
	
	
	//this method gets details of particular user
	//it takes user id as a parameter and retrieve the detail information
	public void getUserDetails(Users user){
		System.out.println("Dao.Users::Getting user detail.");
		try{
			String query = "SELECT * FROM db_usrs_info WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, user.getUser_Id());
			ResultSet result = statement.executeQuery();
			if (result.next()){
				user.setFirst_Name(result.getString("db_f_name"));
				user.setLast_Name(result.getString("db_l_name"));
				user.setEmail_Id(result.getString("db_email_id"));
				user.setJoin_Date(result.getString("join_date"));
				user.setActivationStatus(result.getBoolean("activation_status"));
				user.setAct_no(result.getInt("activation_id"));
				System.out.println("Dao.Users::User details got successfully.");
			}
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Users::database disconnected");
			}
		catch (SQLException e){
			System.out.println("Dao.Users::database user - error");
			e.printStackTrace();			
		}
	}
	
	//This method returns the userid of particular user.
	
	//This method list details of all users
	public ArrayList<Users> getAllUsers(){
		System.out.println("Dao.Users::Getting list of all user details");
		Users user = new Users();
		users = new ArrayList<Users>();	
		try{
			String query = "SELECT * FROM db_usrs_info ORDER BY db_f_name";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			System.out.println("Dao.Users::Starting to get user details.");
			while (result.next()){
				user.setUser_Id(result.getInt("db_usr_id"));
				user.setFirst_Name(result.getString("db_f_name"));
				user.setLast_Name(result.getString("db_l_name"));
				user.setEmail_Id(result.getString("db_email_id"));
				user.setJoin_Date(result.getString("join_date"));
				user.setActivationStatus(result.getBoolean("activation_status"));
				users.add(user);
				System.out.println("Dao.Users::Adding user "+result.getString("db_f_name")+" details in list.");
			}
			
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Users::database disconnected");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Users::error while getting user detail lists");
		}

		System.out.println("Dao.Users::returning user's detail list");
		return users;
	}
	
	//this method returns arraylist of all email ids.
		public ArrayList<Users> getUserEmailList(){
			System.out.println("Dao.Users::Starting to get user email list.");
			ArrayList<Users> emailList = new ArrayList<Users>();
			try{
				String query = "SELECT db_email_id, db_usr_id FROM db_usrs_info";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(query);
				System.out.println("Dao.Users::getting user email from database.");
				while (result.next()){
					Users userArray = new Users();
					userArray.setEmail_Id(result.getString("db_email_id"));
					userArray.setUser_Id(result.getInt("db_usr_id"));					
					emailList.add(userArray);
					System.out.println("Dao.Users::adding user email to the list");
				}
				DbConnection.close_Connection(connection);	
				System.out.println("Dao.Users::Database disconnected.");
			}
			catch (SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Users::Error while getting user list.");
			}
			return emailList;
		}
	
	//This method delete the user profile of particular user.
	public boolean deleteAccount(int userId){
		System.out.println("Dao.Users::Starting to delete user details");
		boolean deleteStatus = false;
		try{
			String query = "DELETE FROM db_usrs_info WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			if (statement.executeUpdate()==1){
				deleteStatus = true;
				System.out.println("Dao.Users::User details successfully deleted.");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Users::error while deleting user details.");
		}		
		System.out.println("Dao.Users::returning user delete status.");
		return deleteStatus;
	}
}

