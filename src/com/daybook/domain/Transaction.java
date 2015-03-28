package com.daybook.domain;

public class Transaction {
	private int transac_Id;
	private int user_Id;
	private String description;
	private String detail;
	private String type;
	private String source;
	private double debit_Amt;
	private double credit_Amt;
	private String date;
	private boolean addStatus;
	
	public int getTransac_Id() {
		return transac_Id;
	}
	public void setTransac_Id(int transac_Id) {
		this.transac_Id = transac_Id;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public double getDebit_Amt() {
		return debit_Amt;
	}
	public void setDebit_Amt(double debit_Amt) {
		this.debit_Amt = debit_Amt;
	}
	public double getCredit_Amt() {
		return credit_Amt;
	}
	public void setCredit_Amt(double credit_Amt) {
		this.credit_Amt = credit_Amt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isTransactionAdded() {
		return addStatus;
	}
	public void setTransactionAddStatus(boolean addStatus) {
		this.addStatus = addStatus;
	}	
}
