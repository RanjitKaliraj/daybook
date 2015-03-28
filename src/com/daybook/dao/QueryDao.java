package com.daybook.dao;

import java.sql.*;
import java.util.ArrayList;

import com.daybook.domain.Query;
import com.daybook.util.*;

public class QueryDao {
	private Connection connection;
	private ArrayList<Query> queries;

	public QueryDao() {
		connection = DbConnection.getConnection();
	}
	
	//This method add the query detail of a user to the database
	//This method sets the submit status in the parameter object to true is detail successfully added
	//else status will be false
	public void addQueryInfo(Query detail){
		System.out.println("Dao.Query::Starting to add user query detail");
		try{
			String query = "INSERT INTO db_usr_qry (db_usr_id, db_subj, db_desc, time_stamp, view_status,reply_status) VALUE (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, detail.getUser_Id());
			statement.setString(2, detail.getSubject());
			statement.setString(3, detail.getDetail());
			statement.setString(4, FormattedDate.getDate());
			statement.setBoolean(5, false);
			statement.setBoolean(6, false);
			if (statement.executeUpdate()==1){
				detail.setQuerySubmitStatus(true);
				System.out.println("Dao.Query::details added successfully");
			}
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Query::database disconnected");
		}
		
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Query::error while adding query");
		}
	}
	
	//This method add the admin reply answer to the query of user
	public void addQueryReply(Query detail){
		System.out.println("Dao.Query::Starting to add query reply");
		try{
			String query = "UPDATE db_usr_qry SET admin_reply=?, reply_status=? WHERE db_usr_id=? ";
			PreparedStatement statement = connection.prepareStatement(query);				
			statement.setString(1, detail.getAdmin_Reply());
			statement.setBoolean(2, true);
			statement.setInt(3, detail.getUser_Id());
			if (statement.executeUpdate()==1){
				detail.setQueryReplySubmitStatus(true);
				System.out.println("Dao.Query::reply successful.");
			}
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Query::database disconnected");
		}
		
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Query::error while adding query reply");
		}
	}
	
	/*
	 * This method get query detail of user
	 */
	public boolean getQueryDetail(Query data){
		System.out.println("Dao.Query::Starting to get query details");
		boolean status = false;
		try{
			String query = "SELECT * FROM db_usr_qry WHERE db_usr_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, data.getUser_Id());
			ResultSet result = statement.executeQuery();
			if (result.isLast()){
				data.setUser_Id(result.getInt("db_usr_id"));;
				data.setSubject(result.getString("db_subj"));
				data.setDetail(result.getString("db_desc"));
				data.setDate(result.getString("time_stamp"));
				data.setViewStatus(result.getBoolean("view_status"));
				data.setAdmin_Reply(result.getString("admin_reply"));
				data.setReply_Status(result.getBoolean("reply_status"));
				status = true;
				System.out.println("Dao.Query::Query detail successfully get.");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Query::error while getting query detail");
		}
		DbConnection.close_Connection(connection);
		System.out.println("Dao.Query::database disconnected");
		System.out.println("Dao.Query::returning status");
		return status;
	}
	
	
	//this method returns the all user queries info from the database
	//this method is called by admin.
	public ArrayList<Query> getQueryInfo(){
		System.out.println("Dao.Query::Starting to get list of all query");
		queries = new ArrayList<Query>();
		Query obj = new Query();
		try{
			String query = "SELECT * FROM db_usr_qry";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()){
				obj.setUser_Id(result.getInt("db_usr_id"));;
				obj.setSubject(result.getString("db_subj"));
				obj.setDetail(result.getString("db_desc"));
				obj.setDate(result.getString("time_stamp"));
				obj.setViewStatus(result.getBoolean("view_status"));
				obj.setAdmin_Reply(result.getString("admin_reply"));
				obj.setReply_Status(result.getBoolean("reply_status"));
				queries.add(obj);
			}
			System.out.println("Dao.Query::List retrieved all.");
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Query::database disconnected");
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Query::error while getting query list");
		}
		System.out.println("Dao.Query::returning query list.");
		return queries;			
	}
	
	//This method set the query view status to the true
	//When the admin see the query then view status this method is called to change the view status to true.
	public void setViewStatus(int id){
		System.out.println("Dao.Query::Starting to set query view status.");
		try{
			String query = "UPDATE db_usr_qry SET view_status=? WHERE db_usr_id=? ";
			PreparedStatement statement = connection.prepareStatement(query);				
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			statement.executeUpdate();
			System.out.println("Dao.Query::Successful.");
			DbConnection.close_Connection(connection);
			System.out.println("Dao.Query::database disconnected");
		}		
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Dao.Query::error while setting query view status");
		}
	}
	
	
	//This method delete the user profile of particular user.
		public boolean deleteAccount(int userId){
			System.out.println("Dao.Query::Starting to delete user info from query database table.");
			boolean deleteStatus = false;
			try{
				String query = "DELETE FROM db_usr_qry WHERE db_usr_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, userId);
				if (statement.executeUpdate()==1){
					deleteStatus = true;
					System.out.println("Dao.Query::delete successful.");
				}
			}
			catch (SQLException e){
				e.printStackTrace();
				System.out.println("Dao.Query::error while deleting");
			}		
			System.out.println("Dao.Query::returning delete status");
			return deleteStatus;
		}
}
