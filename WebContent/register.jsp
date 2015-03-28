<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

 <%
 String[] signupSession = (String[])session.getAttribute("signup");
 String[] userSession = (String[])session.getAttribute("user");
 if (signupSession!=null){
	 if(signupSession[1]=="activate"){
	     System.out.println("redirect to activate page.");
	     response.sendRedirect("activate.jsp");
	     return;
	 } 
	 else if(signupSession[2]=="setup"){
		 System.out.println("redirect to first time setup page.");
	     response.sendRedirect("first-time-setup.jsp");
	     return;
	 }
 }
 else if (userSession!=null){
	 System.out.println("redirect to home page if user session is present. ");
     response.sendRedirect("home.jsp");
     return;
 }
  
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>DayBook</title>
      <meta name="description" content="Custom Login Form Styling with CSS3" />
      <meta name="keywords" content="css3, login, form, custom, input, submit, button, html5, placeholder" />
      <meta name="author" content="Codrops" />
      <link rel="shortcut icon" href="Resources/favicon.ico">
      <link rel="stylesheet" type="text/css" href="Resources/css/style.css" />
      <link rel="stylesheet" type="text/css" href="Resources/css/bootstrap.css" />
      <link rel="stylesheet" type="text/css" href="Resources/css/bootstrap-theme.css" />
      <link rel="stylesheet" type="text/css" href="Resources/css/font-awesome.css" />
      <script src="Resources/js/md5.js"></script>  <!-- password encrypter  -->
      <script src="Resources/js/modernizr.custom.63321.js"></script>
      <script src="Resources/js/md5.js"></script>
      <!--[if lte IE 7]>
      <style>.main{display:none;} .support-note .note-ie{display:block;}</style>
      <![endif]-->
      <style type="text/css">
         body{
         font-family:verdana;
         }
      </style>
	  
	  <form name="sendvalue" action="register" method="post">
		 <input type="hidden" name="fname" />
		 <input type="hidden" name="lname" />
		 <input type="hidden" name="email" />		
         <input type="hidden" name="username" />
         <input type="hidden" name="password2" />			
      </form>
	  
	  
      <script type="text/javascript">
	  
		 function doRegister() {
		 document.sendvalue.fname.value = document.register.fname.value;
		 document.sendvalue.lname.value = document.register.lname.value;
		 document.sendvalue.email.value = document.register.email.value;
         document.sendvalue.username.value = document.register.username.value;
         document.sendvalue.password2.value = hexMD5('\143' + document.register.password2.value + '\362\151\173\074\017\115\314\076\027\131\063\251\076\270\163\142');
         document.sendvalue.submit();
         return false;			
         }
		 
		 
         //This function checks the firstname validity
         function InvalidMsgFname(fname) {    
         	if (fname.value == '') {
         		fname.setCustomValidity('Please, Enter your First Name.');
         	}
         	else {
         		fname.setCustomValidity('');
         	}
         	return true;
         }
         
         //This function checks the lastname validity
         function InvalidMsgLname(lname) {    
         	if (lname.value == '') {
         		lname.setCustomValidity('Please, Enter your Last Name.');
         	}
         	else {
         		lname.setCustomValidity('');
         	}
         	return true;
         }
         
         //This function checks the email validity
         function InvalidMsgEmail(email) {    
         	if (email.value == '') {
         		email.setCustomValidity('Please, Enter your Email Address.');
         	}
         	else if(email.validity.typeMismatch){
         		email.setCustomValidity('please, Enter valid Email Address.');
         	}
         	else {
         		email.setCustomValidity('');
         	}
         	return true;
         }
         
         //This function checks the username validity
         function InvalidMsgUsr(username) {    
         	if (username.value == '') {
         		username.setCustomValidity('Please, Enter your Username.');
         	}
         	else {
         		username.setCustomValidity('');
         	}
         	return true;
         }
         
         //This function checks the password validity
         function InvalidMsgPw(password) {    
         	if (password.value == '') {
         		password.setCustomValidity('Please, Enter your new Password.');
         	}
         	else {
         		password.setCustomValidity('');
         	}
         	return true;
         }
         
         //This function checks the re-entered password validity
         function InvalidMsgPw2(password) {    
         	if (password.value !=document.register.password1.value) {
         		password.setCustomValidity('Password do not match.');
         	}
         	else {
         		password.setCustomValidity('');
         	}
         	return true;
         }
         
      </script>
   </head>
   <body style="height:100%; width:100%; overflow:hidden">
      <!--header-->
      <div style="height:2%; with:100%; background-color:rgba(0, 0, 0, 0.5);"></div>
      <div style="height:10%; with:100%;padding-top:10px">
         <div style="height:100%; width:80px;float:left; margin-left:120px; margin-top:-10px">
            <a href="home.jsp"><img src="Resources/images/logo.png"/></a>
         </div>
         <div style="height:100%; width:250px;float:left;font-size:32px;padding-top:12px">
            <a style="color:white; text-decoration:none;  text-shadow: 0px 0px 15px #000000" href="home.jsp">DayBook</a>
         </div>
         <div style="height:50%; width:300px;float:right;font-size:14px; margin-top:30px;color:white;margin-right:20px">
            <!-- login status -->
         </div>
      </div>
      <div  style="height:79%; with:100%;">
         <div class="container">
            <div class="row">
               <section class="main col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-3 col-xs-12">
                  <form class="form-2" action="register" name="register" method="POST" onsubmit="return doRegister()" style="background-color:rgba(0, 0, 0, 0.4);font-size:11px; font-weight:bold; color:white; text-shadow: 0px 0px 5px #000000;">
                     <p style="font-weight:bold; text-align:center;font-size:14px"> Register for new Account </p>
                     First Name:
                     <p class="field">
                        <input style="color:black" type="text" name="fname" placeholder="first name" oninvalid="InvalidMsgFname(this);" oninput="InvalidMsgFname(this);" required="required">
                     </p>
                     Last Name:
                     <p class="field">
                        <input style="color:black" type="text" name="lname" placeholder="last name" oninvalid="InvalidMsgLname(this);" oninput="InvalidMsgLname(this);" required="required">
                     </p>
                     Email Address:
                     <p class="field">
                        <input style="color:black" type="text" name="email" placeholder="john@example.com" oninvalid="InvalidMsgEmail(this);" oninput="InvalidMsgEmail(this);" required="required">
                     </p>
                     Username:
                     <p class="field">
                        <input style="color:black" type="text" name="username" placeholder="username" oninvalid="InvalidMsgUsr(this);" oninput="InvalidMsgUsr(this);" required="required">
                     </p>
                     New Password:
                     <p class="field">
                        <input style="color:black" type="password" name="password1" id="password" placeholder="********" oninvalid="InvalidMsgPw(this);" oninput="InvalidMsgPw(this);" required="required">
                     </p>
                     Re-enter Password:
                     <p class="field">
                        <input style="color:black" type="password" name="password2" placeholder="********" oninvalid="InvalidMsgPw2(this);" oninput="InvalidMsgPw2(this);" required="required">
                     </p>
                     <p class="signup" style="width:100px;font-size:14px;color:black;">
                        <button type="submit" name="register" onclick="return registerCheck()">Register</button>							
                     </p>
                  </form>
                  <!--This div is for error message -->
                  <div style ="height:40px; width:180px;position:relative; top:-86px; left:130px; color:red; font-size:12px">
                     <p style="color:red;text-shadow: 0px 0px 10px white">
                     <c:out value="${requestScope.msg}" />
                     </p>
                  </div>
               </section>
            </div>
         </div>
      </div>
      <!--footer-->
      <div style="height:6.5%; width:100%;background-color: rgba(0, 0, 0, 0.5);">
         <div style="height:100%;width:20%; float:left"></div>
         <div style="height:100%;width:60%;color:white;float:left; text-align:center;font-size:12px; padding-top:10px">
            <a style="margin:5px; color:white;  margin-left:20px" href="#">About</a>|
            <a style="margin:5px; color:white" href="#">Terms & Conditions </a>	|
            <span style="margin-left:10px"> Follow us : </span>
            <a href=""> <img src="Resources/images/icons/Facebook.png" height="25" width="25" alt="facebook" title="facebook"/>
            <a href=""> <img src="Resources/images/icons/Google+.png" height="25" width="25" alt="google+" title="google+"/>
            <a href=""> <img src="Resources/images/icons/Tumblr.png" height="25" width="25" alt="tumblr" title="tumblr"/>
            <a href=""> <img src="Resources/images/icons/Twitter.png" height="25" width="25" alt="twitter" title="twitter"/>
         </div>
         <div style="height:100%;width:20%;float:left"></div>
      </div>
      <div style="height:2.5%; width:100%;background-color: rgba(0, 0, 0, 0.9);color:grey; text-align:center; font-size:10px;"> <span style="font-weight:bold">DayBook</span> 2014 &copy; All Rights Reserved.</div>
   </body>
</html>
</body>
</html>