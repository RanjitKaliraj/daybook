/*
 * This is a UserProfile service layer class.
 * This class handles all the functions related to user details activity which are: 
 * view profile, edit profile, change security question etc.
 * 
 * @Author: Ranjit Kaliraj
 * Date: 19th Aug. 2014
 */
package com.daybook.service;

import com.daybook.dao.UsersDao;
import com.daybook.dao.BankDao;
import com.daybook.dao.AccountDao;
import com.daybook.dao.RecoveryDao;
import com.daybook.dto.ProfileDto;
import com.daybook.domain.Users;
import com.daybook.domain.Bank;
import com.daybook.domain.Account;
import com.daybook.domain.Recovery;

public class UserProfile {
	private UsersDao userDao;
	private BankDao bankDao;
	private AccountDao accountDao;
	private RecoveryDao recoveryDao;
	
	private Users user;	
	private Bank bank;
	private Account account;
	private Recovery recovery;
	
	/*
	 * This method sets all the details of user in Profile Dto
	 */
	public void getUserProfile(ProfileDto profile){
		System.out.println("Service.UserProfile::Starting getting user profile");
		userDao = new UsersDao();
		bankDao = new BankDao();
		accountDao = new AccountDao();
		
		user = new Users();
		bank = new Bank();
		account = new Account();
		
		user.setUser_Id(profile.getUser_Id());
		bank.setUser_Id(profile.getUser_Id());
		account.setUser_Id(profile.getUser_Id());
		
		System.out.println("Service.UserProfile::getting user details");
		userDao.getUserDetails(user);
		System.out.println("Service.UserProfile::getting bank details");
		bankDao.getBankInfo(bank);
		System.out.println("Service.UserProfile::getting account details");
		accountDao.getUserName(account);
		
		System.out.println("Service.UserProfile::Setting details in profile DTO");
		profile.setF_name(user.getFirst_Name());
		profile.setL_name(user.getLast_Name());
		profile.setEmail(user.getEmail_Id());
		profile.setBank_name(bank.getName());
		profile.setAc_No(bank.getNumber());
		profile.setUsername(account.getUsername());	
		profile.setDate(user.getJoin_Date());
	
		
	}
	
	
	/*
	 * This method get user first and last name
	 */
	public void getUser(ProfileDto profile){
		System.out.println("Service.UserProfile::Starting to get User full name");
		userDao = new UsersDao();		
		user = new Users();
		
		user.setUser_Id(profile.getUser_Id());	
		System.out.println("Service.UserProfile::getting user details.");
		userDao.getUserDetails(user);

		System.out.println("Service.UserProfile::Setting user first and last name in profile DTO.");
		profile.setF_name(user.getFirst_Name());
		profile.setL_name(user.getLast_Name());	
	}
	
	
	/*
	 * This function updates edited user profile information 
	 */
	public void updateUserProfile(ProfileDto data){
		System.out.println("Service.UserProfile::Starting updating user profile");
		userDao = new UsersDao();
		bankDao = new BankDao();
		user = new Users();
		bank = new Bank();
		
		user.setFirst_Name(data.getF_name());
		user.setLast_Name(data.getL_name());
		user.setEmail_Id(data.getEmail());
		user.setUser_Id(data.getUser_Id());
		bank.setName(data.getBank_name());
		bank.setNumber(data.getAc_No());
		bank.setUser_Id(data.getUser_Id());
		
		System.out.println("Service.UserProfile::updating user details");
		userDao.updateUserDetails(user);
		System.out.println("Service.UserProfile::updating bank details");
		bankDao.updateBankInfo(bank);
		
		if (user.isDetailAdded() && bank.isDataAdded()){
			System.out.println("Service.UserProfile::profile updated successfully.");
			data.setUserProfileUpdateStatus(true);
			System.out.println("Service.UserProfile::update status set true.");
		}
	}
	
	/*
	 * This function update edited user security information
	 */
	public void updateSecurityDetail(ProfileDto data){
		recoveryDao = new RecoveryDao();
		recovery = new Recovery();
		
		recovery.setUser_Id(data.getUser_Id());
		recovery.setQuestion(data.getRecoveryQues());
		recovery.setAnswer(data.getRecoveryAns());
		
		recoveryDao.updateRecoveryInfo(recovery);
		if (recovery.isUpdated()){
			data.setSecurityInfoUpdateStatus(true);
		}
	}
	
	/*
	 * This function checks the security question and answer are matched
	 */
	public boolean checkSecurityAnswer(ProfileDto data){
		boolean status = false;		
		Recovery recovery = new Recovery();
		RecoveryDao recoveryDao = new RecoveryDao();
		
		recovery.setUser_Id(data.getUser_Id()); //setting user id in recovery object
		recoveryDao.getRecoveryInfo(recovery);	//getting security answer for recovery
		if (recovery.getAnswer()==data.getRecoveryAns()){
			status = true;
		}
		return status;
	}
	
	/*
	 * This method change user password
	 */
	public void changePassword(ProfileDto data){
		System.out.println("Service.UserProfile::Starting to change user password");
		accountDao = new AccountDao();
		account = new Account();
		
		account.setUser_Id(data.getUser_Id());
		System.out.println("Service.UserProfile::changing user password.");
		accountDao.changePassword(account);
		if (account.isPasswordChanged()){
			System.out.println("Service.UserProfile::password changed");
			data.setPasswordUpdate(true);
			System.out.println("Service.UserProfile::[password change status set true.");
		}	
	}
	
	/*
	 * This method checks if the provided old password is correct or not
	 */
	public void checkOldPassword(ProfileDto data){
		System.out.println("Service.UserProfile::Starting checking old password");
		accountDao = new AccountDao();
		account = new Account();
		
		account.setUser_Id(data.getUser_Id());
		account.setPassword(data.getPassword());
		System.out.println("Service.UserProfile::checking password");
		if (accountDao.matchUserPassword(account)){
			System.out.println("Service.UserProfile::password check successful.");
			data.setPasswordMatchStatus(true);
			System.out.println("Service.UserProfile::password match status set true.");
		}
		else {
			data.setPasswordMatchStatus(false);
			System.out.println("Service.UserProfile::password do not match and status set false");
		}
	}
}
