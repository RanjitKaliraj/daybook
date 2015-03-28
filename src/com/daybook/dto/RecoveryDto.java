package com.daybook.dto;

public class RecoveryDto {
	private int userId;
	private String f_Name;
	private String l_Name;
	private String email_Id;
	private String security_Qn;
	private String security_Ans;
	private String new_Password;
	private boolean paswordChange;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getF_Name() {
		return f_Name;
	}
	public void setF_Name(String f_Name) {
		this.f_Name = f_Name;
	}
	public String getL_Name() {
		return l_Name;
	}
	public void setL_Name(String l_Name) {
		this.l_Name = l_Name;
	}
	public String getEmail_Id() {
		return email_Id;
	}
	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}	
	public String getSecurity_Qn() {
		return security_Qn;
	}
	public void setSecurity_Qn(String security_Qn) {
		this.security_Qn = security_Qn;
	}
	public String getSecurity_Ans() {
		return security_Ans;
	}
	public void setSecurity_Ans(String security_Ans) {
		this.security_Ans = security_Ans;
	}
	public String getNewPassword() {
		return new_Password;
	}
	public void setNewPassword(String password) {
		this.new_Password = password;
	}
	public boolean isPaswordChanged() {
		return paswordChange;
	}
	public void setPaswordChangeStatus(boolean paswordChange) {
		this.paswordChange = paswordChange;
	}
}
