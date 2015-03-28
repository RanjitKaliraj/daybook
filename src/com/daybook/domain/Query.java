package com.daybook.domain;

public class Query {
	private int user_Id;
	private String subject;
	private String detail;
	private String date;
	private boolean viewStatus;
	private String admin_Reply;
	private boolean reply_Status;
	private boolean querySubmitStatus;
	private boolean queryReplySubmitStatus;
	
	public boolean isQueryReplySubmitted() {
		return queryReplySubmitStatus;
	}
	public void setQueryReplySubmitStatus(boolean queryReplySubmitStatus) {
		this.queryReplySubmitStatus = queryReplySubmitStatus;
	}
	public boolean isQuerySubmited() {
		return querySubmitStatus;
	}
	public void setQuerySubmitStatus(boolean querySubmitStatus) {
		this.querySubmitStatus = querySubmitStatus;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isViewStatusTrue() {
		return viewStatus;
	}
	public void setViewStatus(boolean viewStatus) {
		this.viewStatus = viewStatus;
	}
	public String getAdmin_Reply() {
		return admin_Reply;
	}
	public void setAdmin_Reply(String admin_Reply) {
		this.admin_Reply = admin_Reply;
	}
	public boolean isReplied() {
		return reply_Status;
	}
	public void setReply_Status(boolean reply_Status) {
		this.reply_Status = reply_Status;
	}
}
