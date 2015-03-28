package com.daybook.dto;

public class TransactionDto {
	private int user_Id;
	private int transac_Id;
	private String description;
	private String detail;
	private String type;
	private String source;
	private String transac_Detail;
	private String date;
	private double debit_Amt;
	private double credit_Amt;
	private double totalAmt;
	private boolean transactionAddStatus;
	private boolean transactionUpdateStatus;
	private boolean transactionDeleteStatus;
	private boolean allTransactionDeleteStatus;
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public int getTransac_Id() {
		return transac_Id;
	}
	public void setTransac_Id(int transac_Id) {
		this.transac_Id = transac_Id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
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
	public String getTransac_Detail() {
		return transac_Detail;
	}
	public void setTransac_Detail(String transac_Detail) {
		this.transac_Detail = transac_Detail;
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
	public boolean isTransactionAdded() {
		return transactionAddStatus;
	}
	public void setTransactionAddStatus(boolean transactionAddStatus) {
		this.transactionAddStatus = transactionAddStatus;
	}
	public boolean isTransactionUpdated() {
		return transactionUpdateStatus;
	}
	public void setTransactionUpdateStatus(boolean transactionUpdateStatus) {
		this.transactionUpdateStatus = transactionUpdateStatus;
	}
	public boolean isTransactionDeleted() {
		return transactionDeleteStatus;
	}
	public void setTransactionDeleteStatus(boolean transactionDeleteStatus) {
		this.transactionDeleteStatus = transactionDeleteStatus;
	}
	public boolean isAllTransactionDeleted() {
		return allTransactionDeleteStatus;
	}
	public void setAllTransactionDeleteStatus(boolean allTransactionDeleteStatus) {
		this.allTransactionDeleteStatus = allTransactionDeleteStatus;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
