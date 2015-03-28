/*
 * This is a Setup data transfer object
 * which holds different data related to user account setup
 */
package com.daybook.dto;

public class SetupDto {
	private int user_Id;
	private String f_Name;
	private String l_Name;
	private String bank_Name;
	private String bank_No;
	private boolean bankDetailAddedStatus;
	private boolean bankDetailAlreadyAdded;
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
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
	public String getBank_Name() {
		return bank_Name;
	}
	public void setBank_Name(String bank_Name) {
		this.bank_Name = bank_Name;
	}
	public String getBank_No() {
		return bank_No;
	}
	public void setBank_No(String bank_No) {
		this.bank_No = bank_No;
	}
	
	public boolean isBankDetailAdded() {
		return bankDetailAddedStatus;
	}
	public void setBankDetailAddedStatus(boolean bankDetailAddedStatus) {
		this.bankDetailAddedStatus = bankDetailAddedStatus;
	}
	public boolean isBankDetailAlreadyAdded() {
		return bankDetailAlreadyAdded;
	}
	public void setBankDetailAlreadyAdded(boolean bankDetailAlreadyAdded) {
		this.bankDetailAlreadyAdded = bankDetailAlreadyAdded;
	}
}
