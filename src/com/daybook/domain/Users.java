package com.daybook.domain;

public class Users {
	
	private int user_Id;
	private String first_Name;
	private String last_Name;
	private String email_Id;
	private String join_Date;
	private boolean acc_Status;
	private int activation_no;
	private boolean detailAdded;
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	public String getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
	public String getEmail_Id() {
		return email_Id;
	}
	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}
	public String getJoin_Date() {
		return join_Date;
	}
	public void setJoin_Date(String join_Date) {
		this.join_Date = join_Date;
	}
	public boolean isAccount_Activated() {
		return acc_Status;
	}
	public void setActivationStatus(boolean acc_Status) {
		this.acc_Status = acc_Status;
	}
	public int getAct_no() {
		return activation_no;
	}
	public void setAct_no(int random_no) {
		this.activation_no = random_no;
	}
	public boolean isDetailAdded() {
		return detailAdded;
	}
	public void setDetailAddedStatus(boolean detailAdded) {
		this.detailAdded = detailAdded;
	}
}
