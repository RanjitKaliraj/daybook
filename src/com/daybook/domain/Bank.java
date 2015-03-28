package com.daybook.domain;

public class Bank {
	private int user_Id;
	private String name;
	private String number;
	private boolean dataAddStatus;
	private boolean bankDetailAlreadyAdded;
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public boolean isDataAdded() {
		return dataAddStatus;
	}
	public void setDataAddStatus(boolean dataAddStatus) {
		this.dataAddStatus = dataAddStatus;
	}
	public boolean isBankDetailAlreadyAdded() {
		return bankDetailAlreadyAdded;
	}
	public void setBankDetailAlreadyAdded(boolean bankDetailAlreadyAdded) {
		this.bankDetailAlreadyAdded = bankDetailAlreadyAdded;
	}
}
