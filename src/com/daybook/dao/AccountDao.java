package com.daybook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.daybook.domain.Account;
import com.daybook.dao.UsersDao;
import com.daybook.dao.BankDao;
import com.daybook.domain.Users;
import com.daybook.util.DbConnection;

public class AccountDao {

	private Connection connection;
	ArrayList<String> username_List;
	
	public AccountDao(){
		connection = DbConnection.getConnection();
		username_List = new ArrayList<String>();
	}

	/*
	 * this method checks the login authentication of the user
	 * and set the login status either true of false.
	 * First this function checks the provided username and password are correct or not
	 * 
	 */
	public void authenticate(Account value){
		System.out.println("Dao.Account::starting login check");
		try{
			String username = value.getUsername();
			String password = value.getPassword();
			String query = "SELECT * FROM db_acc_info WHERE db_usr_name=? AND db_usr_pw=?";			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();			
			System.out.println("Dao.Account::getting login check");
			
			if (result.next()){
				System.out.println("Dao.Account::username and password matched.");
				
				//boolean login_status = result.getBoolean(5);
				boolean block_status = result.getBoolean("admin_block");
				int userId = result.getInt("db_usr_id");
				System.out.println("Dao.Account::Getting user id and block status:"+userId);
						
				///instantiating userDao and User object to get user Account Activation status
				UsersDao userDao = new UsersDao();
				Users user = new Users();
				user.setUser_Id(userId);
				System.out.println("Dao.Account::Getting user detail to get user activation status");
				userDao.getUserDetails(user);		//getting user details in order to check account activation.
				System.out.println("Dao.Account:: All user details successfully got.");
				
				//check block status
				//check activate status
				//check bank details status
				//check good.
				System.out.println("Dao.Account::Checking user account status and priorities.");
				
				if (!block_status){
					System.out.println("Dao.Account::account is not blocked.");
					BankDao bankDao = new BankDao();
					value.setActivationStatus(true);
					value.setSetupDetailsIncomplete(false);
					value.setLogin_Status(true);
					value.setUser_Id(result.getInt("db_usr_id"));
					value.setPassword(null);
					System.out.println("Dao.Account::login authentication successful.");
					System.out.println("Dao.Account::Checking for account activation status and bank info available status.");
					if (user.isAccount_Activated()==false){
						System.out.println("Dao.Account::account is not activated.");
						value.setActivationStatus(false);
					}
					else if (bankDao.checkUserBankInfoAvailability(userId)!=true){	//if user did not complete bank info then.
						System.out.println("Dao.Account::Bank details are incomplete.");
						value.setSetupDetailsIncomplete(true);
						System.out.println("Dao.Account::Setting Bank details incomplete status true.");
					}
					else{
						System.out.println("Dao.Account::Account is activated and bank info are present.");
					}
				}
				else{
					System.out.println("Dao.Account::Account is blocked by admin.");
					value.setBlockStatus(true);
				}
			}		
			else{
				System.out.println("Dao.Account::result do not matched.");
				value.setLogin_Status(false);  //if the username/password is incorrect login status will be fals
			}
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Account::database disconnected");
		}
		catch (Exception e){
			System.out.println("Dao.Account::Error while authenticating");
			e.printStackTrace();	
		}
	}	

	
	//This function block particular user in order to avoid login.
	public void BlockUser(Account account){
		System.out.println("Dao.Account::Starting to block user.");
		String query = "UPDATE db_acc_info SET admin_block=? WHERE db_usr_id=?";
		try{
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, false);
			statement.setInt(2, account.getUser_Id());
			statement.executeUpdate();
			account.setAdmin_Block(true);
			System.out.println("Dao.Account::User block status set in database");
			
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Account::database disconnected");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Account::Error while blocking user");
		}
	}

	
	//This function set the login status to false when user logs out.
	//this function is not used since login status is not present in database
	//the system is made to support multiple login
	
	/*public void logout(Account account ){
		System.out.println("Dao.Account::Starting loggin user out.");
		try{
			String query = "UPDATE db_acc_info SET login_stat=? WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, false);
			statement.setInt(2,account.getUser_Id());
			if (statement.executeUpdate()==1){
				System.out.println("Dao.Account::user logged out successfully.");
				account.setLogout_Status(true);
				System.out.println("Dao.Account::setting logout status true.");
			}
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Account::database disconnected");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Account::Error while loggin out.");
		}				
	}*/
	
	//this method add the username and password of the user 
	public void addUserInfo(Account account){
		System.out.println("Dao.Account::Starting adding user account details");
		int userId = account.getUser_Id();
		String name = account.getUsername();
		String password = account.getPassword();
		try{
			String query = "INSERT INTO db_acc_info (db_usr_id, db_usr_name, db_usr_pw, admin_block) VALUES (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			statement.setString(2, name);
			statement.setString(3, password);
			statement.setBoolean(4, false);
			if (statement.executeUpdate()==1){
				System.out.println("Dao.Account::user account details added successfully.");
				account.setDetail_added(true);
				System.out.println("Dao.Account::setting detail added status true.");
			}
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Account::database disconnected.");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Account::Error while adding user account details");
		}
	}
	
	//this method returns array of all usernames
	public void getUserList(Account account){	
		System.out.println("Dao.Account::Starting getting username list");
		try{
			String query = "SELECT db_usr_name FROM db_acc_info";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			System.out.println("Dao.Account::getting and adding user name to list");
			while (result.next()){
				String username = result.getString("db_usr_name");
				username_List.add(username);				
			}
			account.setUsers(username_List);
			System.out.println("Dao.Account::username added to the list");
			DbConnection.close_Connection(connection);	
			System.out.println("Dao.Account::database disconnected");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Account::error while getting user list");
		}	
	}
	
	//this method add username of user in Account domain object
	public void getUserName(Account account){
		System.out.println("Dao.Account::Starting to get username");
		try{
			String query = "SELECT db_usr_name FROM db_acc_info WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, account.getUser_Id());
			ResultSet result = statement.executeQuery();
			System.out.println("Dao.Account::Getting username from database");
			if (result.next()){
				account.setUsername(result.getString("db_usr_name"));	
				System.out.println("Dao.Account::Username successfully get.");
			}
			DbConnection.close_Connection(connection);		
			System.out.println("Dao.Account::database disconnected.");
		}
		catch (SQLException e){
			e.printStackTrace();
		}	
	}
	
	//this method compares if the user input password matched to database password.
	public boolean matchUserPassword(Account account){
		System.out.println("Dao.Account::Starting to match user password.");
		boolean status = false;
		try{
			String query = "SELECT db_usr_pw FROM db_acc_info WHERE db_usr_id=? AND db_usr_pw=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, account.getUser_Id());
			statement.setString(2, account.getPassword());
			ResultSet result = statement.executeQuery();
			if (result.next()){
				System.out.println("Dao.Account::Password matched.");
				status = true;		
				System.out.println("Dao.Account::setting match status to true.");
			}
			DbConnection.close_Connection(connection);		
			System.out.println("Dao.Account::database disconnected");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Account::Error while matching user password.");
		}	
		return status;
	}
	
	//this method set the password reset id of the user when user wants to recover the username and password
	public void setResetId(Account account){
		System.out.println("Dao.Account::Starting to set reset id.");
		int resetId = account.getReset_Id();
		int userId = account.getUser_Id();
		System.out.println("Dao.Account::getting reset id and user id.");
		try{
			String query = "UPDATE db_acc_info SET pw_reset_id=? WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, resetId);
			statement.setInt(2, userId);
			statement.executeUpdate();
			System.out.println("Dao.Account::reset id is successfully set.");
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Account::database disconnected.");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Account::error while setting reset id.");
		}
	}
	
	//This method gets the reset ID of user.
	public int getResetId(Account account){
		System.out.println("Dao.Account::Starting to get reset id.");
		int resetId = 0;
		try{
			String query = "SELECT pw_reset_id FROM db_acc_info WHERE db_use_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, account.getUser_Id());
			ResultSet result = statement.executeQuery();
			System.out.println("Dao.Account::getting reset id from database.");
			
			if (result.next()){
				resetId = result.getInt("pw_reset_id");
				System.out.println("Dao.Account::setting reset id in the variable.");
			}
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Account::database disconnected");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Dao.Account::returning reset id.");
		return resetId;
	}
	
	//This method change the password of the particular user.
	public void changePassword(Account account){
		System.out.println("Dao.Account::Starting to change password.");
		try{
			String query = "UPDATE db_acc_info SET db_usr_pw=? WHERE db_usr_id=? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, account.getPassword());
			statement.setInt(2, account.getUser_Id());
			if (statement.executeUpdate()==1){
				account.setPasswordChangeStatus(true);
				System.out.println("Dao.Account::Password changed successfully.");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Account::Error occured while changing password.");
		}
	}
	
	
	
	//This method delete the account of particular user.
	public boolean deleteAccount(int userId){
		System.out.println("Dao.Account::Starting to delete user account details.");
		boolean deleteStatus = false;
		try{
			String query = "DELETE FROM db_acc_info WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			if (statement.executeUpdate()==1){
				deleteStatus = true;
				System.out.println("Dao.Account::User account deleted.");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Account::error while deleting user account.");
		}	
		System.out.println("Dao.Account::returning delete status.");
		return deleteStatus;
	}
	
}
