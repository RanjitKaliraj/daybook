package com.daybook.domain;

public class Amount {
	private int user_Id;
	private double total_Debit;
	private double total_Credit;
	private double total_Amt;
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public double getTotal_Debit() {
		return total_Debit;
	}
	public void setTotal_Debit(double total_Debit) {
		this.total_Debit = total_Debit;
	}
	public double getTotal_Credit() {
		return total_Credit;
	}
	public void setTotal_Credit(double total_Credit) {
		this.total_Credit = total_Credit;
	}
	public double getTotal_Amt() {
		return total_Amt;
	}
	public void setTotal_Amt(double total_Amt) {
		this.total_Amt = total_Amt;
	}
}
