package com.daybook.domain;

public class Recovery {
	private int user_Id;
	private String question;
	private String answer;
	private boolean addStatus;
	private boolean updateStatus;
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isAdded() {
		return addStatus;
	}
	public void setAddStatus(boolean addStatus) {
		this.addStatus = addStatus;
	}
	public boolean isUpdated() {
		return updateStatus;
	}
	public void setUpdateStatus(boolean updateStatus) {
		this.updateStatus = updateStatus;
	}
}
