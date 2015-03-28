<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

  <%
 String[] signupSession = (String[])session.getAttribute("signup");
 String[] userSession = (String[])session.getAttribute("user");
 if (signupSession!=null){
	 if(signupSession[2]!="setup"){
	     System.out.println("redirect to index page is this page is not access from register page ");
	       response.sendRedirect("index.jsp");
	 } 
 }
 else if (userSession!=null){
	 System.out.println("redirect to index page this is not time of setup session. ");
     response.sendRedirect("index.jsp");
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
      <script src="Resources/js/modernizr.custom.63321.js"></script>
      <!--[if lte IE 7]>
      <style>.main{display:none;} .support-note .note-ie{display:block;}</style>
      <![endif]-->
      <style type="text/css">
         body{
         font-family:verdana;
         }
      </style>
      <script type="text/javascript">
         //This function checks the username validity
         function InvalidMsgName(name) {    
         	if (name.value == '') {
         		name.setCustomValidity('Please, Enter your Bank name.');
         	}
         	else {
         		name.setCustomValidity('');
         	}
         	return true;
         }
         
         //This function checks the password validity
         function InvalidMsgNo(no) {    
         	if (no.value == '') {
         		no.setCustomValidity('Please, Enter your Bank account number.');
         	}
         	else {
         		no.setCustomValidity('');
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
            <a style="color:white; text-decoration:none;  text-shadow: 0px 0px 15px #000000"href="home.jsp">DayBook</a>
         </div>
         <div style="height:50%; width:300px;float:right;font-size:14px; margin-top:30px;color:white;margin-right:20px">
            <!-- login status -->
         </div>
      </div>
      <div  style="height:79%; with:100%;">
         <div class="container">
            <div class="row">
               <section class="main col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-3 col-xs-12">
                  <form class="form-1" action="setup" name="setup" method="POST">
                     <p style="font-weight:bold; text-align:center">Additional Information: </p>
                     <p style="text-align:Justify;font-size:12px">Please, take a few moment to setup your Account.</p>
                     <p style="font-weight:bold; text-align:left"> Bank Information: </p>
                     <p class="field">
                        <input type="text" name="bank_name" placeholder="Bank Name" oninvalid="InvalidMsgName(this)" oninput="InvalidMsgName(this)" required="required">
                        <i class="icon-user icon-large"></i>
                     </p>
                     <p class="field">
                        <input type="text" name="account_no" placeholder="Bank Account Number" oninvalid="InvalidMsgNo(this)" oninput="InvalidMsgNo(this)" required="required">
                        <i class="icon-user icon-large"></i>
                     </p>
                     <!--
                        <p style="font-weight:bold; text-align:left"> Security Information</p>
                        <select>
                        <option>Please, Select security Question.</option>
                        <option>apple</option>
                        <option>apple</option>
                        <option>apple</option>
                        <option>apple</option>
                        <option>apple</option>
                        <option>apple</option>
                        <option>apple</option>
                        <option>apple</option>
                        </select>
                        <p style="font-weight:bold; text-align:left">Your answer:</p>
                        <p class="field">
                        	<input type="text" name="username" placeholder="Username" required>
                        	<i class="icon-user icon-large"></i>
                        </p>	-->					
                     <p class="signup" style="width:100px;">
                        <button type="submit" name="setup">Continue</button>							
                     </p>
                  </form>
                  <!--This div is for error message -->
                  <div style ="height:40px; width:180px;position:relative; top:-82px; left:150px; color:red; font-size:12px">
                     <p style="color:red">${requestScope.msg}</p>
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
