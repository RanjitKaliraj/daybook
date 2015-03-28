package com.daybook.domain;

public class Admin {
	private int user_Id;
	private boolean fn1_block;
	private boolean fn2_block;
	private boolean fn3_block;
	private boolean fn1_blockStatus;
	private boolean fn2_blockStatus;
	private boolean fn3_blockStatus;
	private boolean fn1_unblockStatus;
	private boolean fn2_unblockStatus;
	private boolean fn3_unblockStatus;
	
	public boolean isFn1_block() {
		return fn1_block;
	}
	public void setFn1_block(boolean fn1_block) {
		this.fn1_block = fn1_block;
	}
	public boolean isFn2_block() {
		return fn2_block;
	}
	public void setFn2_block(boolean fn2_block) {
		this.fn2_block = fn2_block;
	}
	public boolean isFn3_block() {
		return fn3_block;
	}
	public void setFn3_block(boolean fn3_block) {
		this.fn3_block = fn3_block;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}	
	public boolean isFn1_blockStatus() {
		return fn1_blockStatus;
	}
	public void setFn1_blockStatus(boolean fn1_blockStatus) {
		this.fn1_blockStatus = fn1_blockStatus;
	}
	public boolean isFn2_blockStatus() {
		return fn2_blockStatus;
	}
	public void setFn2_blockStatus(boolean fn2_blockStatus) {
		this.fn2_blockStatus = fn2_blockStatus;
	}
	public boolean isFn3_blockStatus() {
		return fn3_blockStatus;
	}
	public void setFn3_blockStatus(boolean fn3_blockStatus) {
		this.fn3_blockStatus = fn3_blockStatus;
	}
	public boolean isFn1_unblockStatus() {
		return fn1_unblockStatus;
	}
	public void setFn1_unblockStatus(boolean fn1_unblockStatus) {
		this.fn1_unblockStatus = fn1_unblockStatus;
	}
	public boolean isFn2_unblockStatus() {
		return fn2_unblockStatus;
	}
	public void setFn2_unblockStatus(boolean fn2_unblockStatus) {
		this.fn2_unblockStatus = fn2_unblockStatus;
	}
	public boolean isFn3_unblockStatus() {
		return fn3_unblockStatus;
	}
	public void setFn3_unblockStatus(boolean fn3_unblockStatus) {
		this.fn3_unblockStatus = fn3_unblockStatus;
	}
}
