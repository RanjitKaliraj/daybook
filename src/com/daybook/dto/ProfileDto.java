package com.daybook.dto;

public class ProfileDto {
	private int user_Id;
	private String username;
	private String f_name;
	private String l_name;
	private String email;
	private String password;
	private String bank_name;
	private String ac_No;
	private String date;
	private String recoveryQues;
	private String recoveryAns;
	private boolean userProfileUpdateStatus;
	private boolean securityInfoUpdateStatus;
	private boolean passwordUpdate;
	private boolean passwordMatch;
	
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
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAc_No() {
		return ac_No;
	}
	public void setAc_No(String ac_No) {
		this.ac_No = ac_No;
	}
	public String getRecoveryQues() {
		return recoveryQues;
	}
	public void setRecoveryQues(String recoveryQues) {
		this.recoveryQues = recoveryQues;
	}
	public String getRecoveryAns() {
		return recoveryAns;
	}
	public void setRecoveryAns(String recoveryAns) {
		this.recoveryAns = recoveryAns;
	}
	public boolean isUserProfileUpdated() {
		return userProfileUpdateStatus;
	}
	public void setUserProfileUpdateStatus(boolean userProfileUpdateStatus) {
		this.userProfileUpdateStatus = userProfileUpdateStatus;
	}
	public boolean isSecurityInfoUpdated() {
		return securityInfoUpdateStatus;
	}
	public void setSecurityInfoUpdateStatus(boolean securityInfoUpdateStatus) {
		this.securityInfoUpdateStatus = securityInfoUpdateStatus;
	}
	public boolean isPasswordUpdated() {
		return passwordUpdate;
	}
	public void setPasswordUpdate(boolean passwordUpdate) {
		this.passwordUpdate = passwordUpdate;
	}
	public boolean isPasswordMatched() {
		return passwordMatch;
	}
	public void setPasswordMatchStatus(boolean passwordMatch) {
		this.passwordMatch = passwordMatch;
	}
}
