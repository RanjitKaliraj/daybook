package com.daybook.domain;

import java.util.ArrayList;

public class Account {
	private int log_Id;
	private int user_Id;
	private String username;
	private String password;
	private boolean login_Status;
	private boolean logout_Status;
	private boolean admin_Block;
	private boolean setupDetailsIncomplete;
	private boolean detail_added;
	private int reset_Id;
	private ArrayList<String> users;
	private boolean passwordChangeStatus;
	private boolean activationStatus;
	private boolean blockStatus;
	
	public int getLog_Id() {
		return log_Id;
	}
	public void setLog_Id(int log_Id) {
		this.log_Id = log_Id;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoggedin() {
		return login_Status;
	}
	public void setLogin_Status(boolean login_Status) {
		this.login_Status = login_Status;
	}
	public boolean isAdmin_Block() {
		return admin_Block;
	}
	public void setAdmin_Block(boolean admin_Block) {
		this.admin_Block = admin_Block;
	}
	public int getReset_Id() {
		return reset_Id;
	}
	public void setReset_Id(int reset_Id) {
		this.reset_Id = reset_Id;
	}
	public ArrayList<String> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}
	public boolean isSetupDetailsIncomplete() {
		return setupDetailsIncomplete;
	}
	public void setSetupDetailsIncomplete(boolean setupDetailsIncomplete) {
		this.setupDetailsIncomplete = setupDetailsIncomplete;
	}
	public boolean isLogout() {
		return logout_Status;
	}
	public void setLogout_Status(boolean logout_Status) {
		this.logout_Status = logout_Status;
	}
	public boolean isDetail_added() {
		return detail_added;
	}
	public void setDetail_added(boolean detail_added) {
		this.detail_added = detail_added;
	}
	public boolean isPasswordChanged() {
		return passwordChangeStatus;
	}
	public void setPasswordChangeStatus(boolean passwordChangeStatus) {
		this.passwordChangeStatus = passwordChangeStatus;
	}
	public boolean isActivated() {
		return activationStatus;
	}
	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}
	public boolean isBlockStatus() {
		return blockStatus;
	}
	public void setBlockStatus(boolean blockStatus) {
		this.blockStatus = blockStatus;
	}		
}
