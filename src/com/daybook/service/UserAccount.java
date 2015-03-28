/*
 * This is a UserAccount service layer class.
 * This class handles all the functions related to account activity which are: 
 * login, account signup, setup and recovery.
 * 
 * @Author: Ranjit Kaliraj
 * Date: 19th Aug. 2014
 */
package com.daybook.service;

//importing all the data transfer object needed for this class.
import java.util.ArrayList;
import java.util.Random;

import com.daybook.dto.LoginDto;
import com.daybook.dto.ProfileDto;
import com.daybook.dto.RecoveryDto;
import com.daybook.dto.SetupDto;
import com.daybook.dto.SignUpDto;
import com.daybook.dto.MailDto;

//importing domain object in order to transfer data to DAO objects.
import com.daybook.domain.Account;
import com.daybook.domain.Users;
import com.daybook.domain.Bank;

//importing database layer class.
import com.daybook.dao.*;
import com.daybook.util.FormattedDate;
//importing mail class
import com.daybook.util.Mail;

public class UserAccount {
	//initialiazing all the imported classes.	
	/*private AccountDao accountDao;
	private UsersDao userDao;
	private AmountDao amountDao;
	private AdminDao adminDao;
	private BankDao bankDao;
	
	private Account account;
	private Users user;*/
	
	/*
	 * This method checks the user login and if successful this method will redirect user
	 * to the user's homepage else it will throw error message.
	 * This method take LoginDto object as parameter which holds username and password of user.
	 */
	public void loginCheck(LoginDto login){
		System.out.println("Service.UserAccount::Starting to login check");
		//instantiating DAO and domain object.
		Account account = new Account();
		AccountDao accountDao = new AccountDao(); 
		//BankDao bankDao = new BankDao();
		
		//used for extracting user first and last name from the database
		UsersDao userDao = new UsersDao();
		Users user = new Users();
		
		//setting the username and password value in domain object account.
		account.setUsername(login.getUsername());
		account.setPassword(login.getPassword());
		
		//checking the username and password in database
		System.out.println("Service.UserAccount::calling authenticate");
		accountDao.authenticate(account);
		System.out.println("Service.UserAccount::Setting user id. The user id is:"+account.getUser_Id() );
		login.setUser_Id(account.getUser_Id());		//setting user id in login object
		
		System.out.println("Service.UserAccount::Checking if account is blocked or not");
		if (account.isBlockStatus()==true){
			System.out.println("Service.UserAccount::Account is blocked");
			login.setAccountBlock(true);
		}
		else if(account.isLoggedin()){
			System.out.println("Service.UserAccount::account is logged in.");
			login.setLogin_Status(true);
			System.out.println("Service.UserAccount::setting user id.");
			user.setUser_Id(account.getUser_Id()); //setting user id in user object
			userDao.getUserDetails(user); //getting user detail
			System.out.println("Service.UserAccount::getting first and last name of user.");
			login.setFname(user.getFirst_Name()); //setting user first name
			login.setLname(user.getLast_Name());  //setting user last name		
			System.out.println("Service.UserAccount::login is successful.");
			System.out.println("Service.UserAccount::Getting activation and bank info status.");
			if (account.isActivated()!=true){
				System.out.println("Service.UserAccount::account is not activated.");
				login.setAccountActivation(false);
				
			}
			else if (account.isSetupDetailsIncomplete()){
				System.out.println("Service.UserAccount::Bank info is not present.");
				login.setBankInfoAvailable(false);
				login.setAccountActivation(true);
			}
			else{
				System.out.println("Service.UserAccount::account is activated and bank info are present.");
				login.setAccountActivation(true);
				login.setBankInfoAvailable(true);
			}
		}
	}
	
	/*
	 * This method checks if the user has provided bank or not
	 * if not then user will need to add those info before accessing home screen.
	 *this method takes user id as parameter and return boolean true or false
	 *This method must executed after logincheck() method execution
	 */
	public boolean checkUserAdditionalInfo(int userId){
		boolean status = false;
		BankDao bankDao = new BankDao();
		if (bankDao.checkUserBankInfoAvailability(userId)){
			status = true;
		}
		return status;
	}
	
	/*
	 * This method checks if the user has activated the account or not
	 */
	
	/*
	 * This method logout the user from their current session
	 * This method takes user id as a parameter and return boolean value.
	 */
	/*public boolean logout(int userId){
		//instantiating objects
		Account account = new Account();
		AccountDao accountDao = new AccountDao();		
		account.setUser_Id(userId);		//setting user id
		//accountDao.logout(account);		//calling logout method from database layer		
		boolean isLogout = account.isLogout();
		return isLogout;	
	}*/
	
	/*
	 * This is a sign up method.
	 * This method takes User domain object as parameter which contains user information such as name, email etc.
	 * and pass it to database access layer object - UserDao.
	 * This method sets user details, account details and sent the activation code to user email
	 */
	public void signUp(SignUpDto signupDto){		
		//instantiating objects for database access
		Users user = new Users();
		Account account = new Account();
		UsersDao userDao = new UsersDao();
		AccountDao accountDao = new AccountDao();
		AdminDao adminDao = new AdminDao();
		AmountDao amountDao = new AmountDao();
		ProfileDto profileDto = new ProfileDto();		
		//setting user information in user User domain object
		user.setFirst_Name(signupDto.getF_name());
		user.setLast_Name(signupDto.getL_name());
		user.setEmail_Id(signupDto.getEmail());
		user.setJoin_Date(FormattedDate.getDate());
		
		profileDto.setEmail(user.getEmail_Id());
		//checking if the user email is already used or not
		if (this.checkEmailAvailability(profileDto)==false){
			signupDto.setEmailAvailable(true);
			userDao.addUserDetails(user);	//calling DAO method to add user detail in database
			if (user.isDetailAdded()){
				System.out.println("Service.UserAccount::User profile detail successfully added.");
				adminDao.addUserId(user.getUser_Id());  //adding user id in admin database table
				amountDao.addUserId(user.getUser_Id());  //adding user id in amount database table
				signupDto.setUser_id(user.getUser_Id());
				//setting userid, username and password in account object
				account.setUser_Id(user.getUser_Id());
				account.setUsername(signupDto.getUsername());
				account.setPassword(signupDto.getPassword());
				accountDao.addUserInfo(account);	//calling DAO object AccountDao method to add user info in database
				if (account.isDetail_added()){
					System.out.println("Service.UserAccount::User Account detail successfully added.");
					System.out.println("Service.UserAccount::Calling method to send activation code.");
					this.sendActivationCode(account.getUser_Id());
				}
				signupDto.setSignupStatus(true);				
			}
			else {
				System.out.println("Service.UserAccount::Error while adding User details.");
				signupDto.setSignupStatus(false);
			}
		}
		else {
			//if the email is not available then email availability is false
			signupDto.setEmailAvailable(false);
			//the code will be terminated??
		}
	}
	
	/*
	 * This function sends user an activation code.
	 * This method takes email id as parameter and returns boolean true or false.
	 */
	public boolean sendActivationCode(int user_Id){
		System.out.println("Service.UserAccount::Starting send activation.");
		boolean status = false;
		ProfileDto profileDto = new ProfileDto();
		UserProfile userProfile = new UserProfile();
		UsersDao userDao = new UsersDao();
		
		profileDto.setUser_Id(user_Id);
		
		System.out.println("Service.UserAccount::getting user's name");
		userProfile.getUser(profileDto);
		
		System.out.println("Service.UserAccount::getting user's email id.");
		String email = userDao.getEmailId(user_Id);
		//generating random number.
		//this number will be stored in database and also send to the user email in order to activate the user account
		
		System.out.println("Service.UserAccount::generating random number");
		Random random = new Random();
		int activation_id = random.nextInt();
		if (activation_id<0){
			int num = -1;
			activation_id = activation_id*num;
		}
		
		System.out.println("Service.UserAccount::Adding activation id in database");
		if (userDao.setActivationId(activation_id, user_Id)){
			//send activation email to user
			//instantiating objects for mail from daybook.util
			
			MailDto mailDto = new MailDto();
			Mail mail = new Mail();  //from com.daybook.util.Mail;				
			
			String to = email;
			String from = "registration@daybook.com";
			String subject = "DayBook Account activation";
			//String message = "Dear"+user.getFirst_Name()+" "+user.getLast_Name()+"\n\t"+"Follow the link below to activate your account:\n"+link;
			String message = "Dear "+profileDto.getF_name()+" "+profileDto.getL_name()+",\n"+"Your activation id is: "+activation_id;
			mailDto.setFname(profileDto.getF_name());
			mailDto.setLname(profileDto.getL_name());
			mailDto.setTo(to);
			mailDto.setFrom(from);
			mailDto.setSubject(subject);
			mailDto.setMessage(message);
			System.out.println("Service.UserAccount::sending mail.");
			mail.sendMail(mailDto); 	//sending email
			if (mailDto.isMailSent()){
				status = true;
				System.out.println("Service.UserAccount::mail sent successful.");
			}
			status = true;
		}		
		return status;
	}
	
	
	/*
	 * This is a AccountSetup function.
	 * After user signup, this method will be executed in order to setup the account.
	 * This method add additional user information to the database.
	 * The function takes SetUp DTO as a parameter and return boolean value to determine 
	 * whether the data is successfully added or not.
	 * the Setup object includes user id, bank name and no,security question and answer.
	 */
	public void AccountSetup(SetupDto setupDto){
		//instantiating bankDao DAO and bank domain object
		BankDao bankDao = new BankDao();
		Bank bank = new Bank();
		
		
		//adding bank info in the bank domain object
		bank.setUser_Id(setupDto.getUser_Id());
		bank.setName(setupDto.getBank_Name());
		bank.setNumber(setupDto.getBank_No());
		
		//calling database access object method to add bank information
		bankDao.addBankInfo(bank);
		setupDto.setBankDetailAddedStatus(true);
		if (bank.isBankDetailAlreadyAdded()){
			setupDto.setBankDetailAlreadyAdded(true);
		}
		else if (bank.isDataAdded()){
			setupDto.setBankDetailAddedStatus(true);
		}
		else {
			setupDto.setBankDetailAddedStatus(false);
		}
	}
	
	/*
	 * This method checks the activation code
	 * It takes the activation code as parameter with value user id and activation code
	 * and then return boolean value whether code is correct or not.
	 */
	public boolean checkActivationCode(SignUpDto signupDto){
		//initializing objects and variables
		Users user = new Users();
		UsersDao userDao = new UsersDao();
		boolean activation_Match = false;
		
		user.setUser_Id(signupDto.getUser_id());	//setting user id in user domain object
		userDao.getUserDetails(user);	//getting user detail from database
		//checking if activation code of database is matched with user input code
		if (user.getAct_no()==signupDto.getActivation_Code()){	
			activation_Match = true;
		}
		return activation_Match;
	}
	
	/*
	 * This method reset the activation code and change user status to active
	 * This method is executed after actiavtion code is matched.
	 */
	public void activateAccount(int userId){
		UsersDao userDao = new UsersDao();
		userDao.activateAccount(userId);
	}


	/*
	 * This function change the user password.
	 */
	public void changePassword(RecoveryDto recoveryDto){
		Account account = new Account();
		AccountDao accountDao = new AccountDao();		
		account.setUser_Id(recoveryDto.getUserId());	//setting user id
		account.setPassword(recoveryDto.getNewPassword());	//setting password value
		accountDao.changePassword(account);		//calling method to change password
		if (account.isPasswordChanged()){
			recoveryDto.setPaswordChangeStatus(true);
		}	
	}
	
	/*
	 * This function delete user account
	 */
	public boolean deleteUserAccount(int userId){
		//instantiating dao objects
		AccountDao accountDao = new AccountDao();
		AdminDao adminDao = new AdminDao();
		AmountDao amountDao = new AmountDao();
		BankDao bankDao = new BankDao();
		QueryDao queryDao = new QueryDao();
		TransactionDao transactionDao = new TransactionDao();
		UsersDao userDao = new UsersDao();
		
		//deleting all the information of user.
		if (userDao.deleteAccount(userId) &&
		accountDao.deleteAccount(userId) &&
		transactionDao.deleteAllTransaction(userId) &&
		adminDao.deleteAccount(userId) &&
		amountDao.deleteAccount(userId) &&
		bankDao.deleteAccount(userId) &&
		queryDao.deleteAccount(userId)){
			return true;
		}
		else{
			return false;
		}		
	}
	

	/*
	 * This method checks if the email address is already used to sign up the account or not
	 * this method takes email string as parameter and check with database email list.
	 */
	public Boolean checkEmailAvailability(ProfileDto profile){
		System.out.println("Service.UserAccount::Checking email availability");
		UsersDao userDao = new UsersDao();
		boolean status = false;
		ArrayList<Users> userList = new ArrayList<Users>();
		userList = userDao.getUserEmailList();
		int size = userList.size();
		
		for (int i = 0; i<size; i++){
			Users user = new Users();
			user = userList.get(i);
			System.out.println("Service.UserAccount::email :"+ userList.get(i).getEmail_Id()+ i + profile.getEmail());
			if (user.getEmail_Id().equals(profile.getEmail())){
				System.out.println("Service.UserAccount::email is present");
				profile.setUser_Id(user.getUser_Id());
				status = true;
				i = size;
			}	
			else{
				i++;
			}
			if (status == false){
				System.out.println("Service.UserAccount::email is not present");
			}
		}
		return status;
	}
}
