package com.daybook.dto;

public class QueryDto {
	private int userId;
	private String subject;
	private String details;
	private String queryDate;
	private String admin_reply;
	private boolean view_Status;
	private boolean reply_Status;
	private boolean queryAddStatus;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}
	public String getAdmin_reply() {
		return admin_reply;
	}
	public void setAdmin_reply(String admin_reply) {
		this.admin_reply = admin_reply;
	}
	public boolean isView_Status() {
		return view_Status;
	}
	public void setView_Status(boolean view_Status) {
		this.view_Status = view_Status;
	}
	public boolean isReply_Status() {
		return reply_Status;
	}
	public void setReply_Status(boolean reply_Status) {
		this.reply_Status = reply_Status;
	}
	public boolean isQueryAdded() {
		return queryAddStatus;
	}
	public void setQueryAddStatus(boolean queryAddStatus) {
		this.queryAddStatus = queryAddStatus;
	}
}
