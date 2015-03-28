package com.daybook.dto;

public class SignUpDto {	
	private int user_id;
	private String f_name;
	private String l_name;
	private String email;
	private String username;
	private String password;
	private boolean signupStatus;
	//private String activation_Link;
	private int activation_Code;
	private boolean mailSentStatus;
	private boolean emailAvailable;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
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
	public boolean isSignupSuccessful() {
		return signupStatus;
	}
	public void setSignupStatus(boolean signupStatus) {
		this.signupStatus = signupStatus;
	}
	/*public String getActivation_Link() {
		return activation_Link;
	}
	public void setActivation_Link(String activation_Link) {
		this.activation_Link = activation_Link;
	}*/
	public boolean isMailSent() {
		return mailSentStatus;
	}
	public void setMailSentStatus(boolean mailSentStatus) {
		this.mailSentStatus = mailSentStatus;
	}
	public int getActivation_Code() {
		return activation_Code;
	}
	public void setActivation_Code(int activation_Code) {
		this.activation_Code = activation_Code;
	}
	public boolean isEmailAvailable() {
		return emailAvailable;
	}
	public void setEmailAvailable(boolean emailAvailable) {
		this.emailAvailable = emailAvailable;
	}	
}
