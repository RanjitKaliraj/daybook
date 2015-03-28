/*Database name : day_book_dbfl*/

SELECT database day_book_dbfl

CREATE table db_usrs_info (
	db_usr_id int AUTO_INCREMENT NOT NULL,
	db_f_name varchar(255) NOT NULL,
	db_l_name varchar(255) NOT NULL,
	db_email_id varchar(255) NOT NULL,
	join_date varchar(255) NOT NULL,	
	activation_id varchar(255) NOT NULL,
	activation_status boolean,/* truen - active or false - incactive*/	
	PRIMARY KEY (db_usr_id)	
);

CREATE table db_acc_info (
	log_id int NOT NULL AUTO_INCREMENT,
	db_usr_id int NOT NULL,
	db_usr_name varchar(255) NOT NULL,
	db_usr_pw varchar(255) NOT NULL,
	/*login_stat boolean,*/
	pw_reset_id int, /*this column holds the setup id if password need to be reset*/
	admin_block boolean,
	PRIMARY KEY (log_id),
	FOREIGN KEY (db_usr_id) REFERENCES db_usrs_info(db_usr_id)
);


CREATE table admin_ctrl (
	db_usr_id int NOT NULL,	
	block_fn1 boolean,
	block_fn2 boolean,
	block_fn3 boolean,
	FOREIGN KEY (db_usr_id) REFERENCES db_usrs_info(db_usr_id)
);

CREATE table total_amt (
	db_usr_id int NOT NULL,
	debit double NOT NULL, 
	credit double NOT NULL,
	total double NOT NULL,
	FOREIGN KEY (db_usr_id) REFERENCES db_usrs_info(db_usr_id)
);

CREATE table db_transac_info (
	db_transac_id int NOT NULL AUTO_INCREMENT,
	db_usr_id int NOT NULL,
	transac_desc varchar(255) NOT NULL,
	db_transac_detail text,
	transac_source varchar(255) NOT NULL,
	transac_type varchar(255) NOT NULL,
	transac_in double NOT NULL,
	transac_out double NOT NULL,
	/*transac_total double NOT NULL,*/
	transac_date varchar(255),
	PRIMARY KEY (db_transac_id),
	FOREIGN KEY (db_usr_id) REFERENCES db_usrs_info(db_usr_id)
);


CREATE table db_bnk_details (
	db_usr_id int NOT NULL,
	db_bnk_name varchar(255) NOT NULL,
	db_bnk_no varchar(255) NOT NULL,
	FOREIGN KEY (db_usr_id) REFERENCES db_usrs_info(db_usr_id)
);


CREATE table db_usr_qry (
	db_usr_id int NOT NULL,
	db_subj varchar(255) NOT NULL,
	db_desc text NOT NULL,
	time_stamp varchar(255) NOT NULL,
	view_status boolean NOT NULL,
	reply_status boolean NOT NULL,
	FOREIGN KEY (db_usr_id) REFERENCES db_usrs_info(db_usr_id)
);


/*
CREATE table login_log (
	log_id int NOT NULL,
	ip_address varchar(255),
	time_log varchar(255),
	FOREIGN KEY (log_id) REFERENCES db_login_auth(log_id)
);*/


/*
CREATE table db_transac_details (
	db_transac_id int NOT NULL,
	db_transac_detail text,
	FOREIGN KEY (db_transac_id) REFERENCES db_transac_info(db_transac_id)
);


CREATE table usr_login_status (
	db_usr_id int NOT NULL,
	login_status varchar(255) NOT NULL,
	login_time varchar(255) NOT NULL,
	login_count int NOT NULL,
	FOREIGN KEY (db_usr_id) REFERENCES db_usrs_info(db_usr_id)
);*/


/*
CREATE table usr_name_lst (
	log_id int NOT NULL,
	db_usr_names varchar(255) NOT NULL,
	FOREIGN KEY (log_id) REFERENCES db_login_auth(log_id)
	);

CREATE table usr_email_lst (
	db_usr_id int NOT NULL,
	db_usr_email varchar(255) NOT NULL,
	FOREIGN KEY (db_usr_id) REFERENCES db_usrs_info(db_usr_id)
);*/


/*
CREATE table db_temp_signup (
	db_usr_name varchar(255) NOT NULL,
	db_f_name varchar(255) NOT NULL,
	db_l_name varchar(255) NOT NULL,
	db_usr_pw varchar(255) NOT NULL,
	db_email_id varchar(255) NOT NULL,
	acc_setup_id int NOT NULL
); */

/*
CREATE table db_recov_tb (
	db_usr_id int NOT NULL,
	db_scr_qn text NOT NULL,
	db_scr_ans text NOT NULL,	
	FOREIGN KEY (db_usr_id) REFERENCES db_usrs_info(db_usr_id)
);*/

