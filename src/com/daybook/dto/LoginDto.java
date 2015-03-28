package com.daybook.dto;

public class LoginDto {
	private int user_Id;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private boolean login_Status;
	private boolean already_login;
	private boolean accountBlock;
	private boolean accountActivation;
	private boolean bankInfoAvailable;
	
	public boolean getLogin_Status() {
		return login_Status;
	}
	public void setLogin_Status(boolean login_Status) {
		this.login_Status = login_Status;
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
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public boolean isAlready_login() {
		return already_login;
	}
	public void setAlready_login(boolean already_login) {
		this.already_login = already_login;
	}
	public boolean isAccountBlocked() {
		return accountBlock;
	}
	public void setAccountBlock(boolean accountBlock) {
		this.accountBlock = accountBlock;
	}
	public boolean isAccountActivated() {
		return accountActivation;
	}
	public void setAccountActivation(boolean accountActivation) {
		this.accountActivation = accountActivation;
	}
	public boolean isBankInfoAvailable() {
		return bankInfoAvailable;
	}
	public void setBankInfoAvailable(boolean bankInfoAvailable) {
		this.bankInfoAvailable = bankInfoAvailable;
	}
}
