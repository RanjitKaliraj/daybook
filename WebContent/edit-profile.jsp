<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page import="com.daybook.dto.ProfileDto" %>
    
 <%
 String[] userSession = (String[])session.getAttribute("user");
if (userSession==null){
	 System.out.println("redirect to index page if it is not set. ");
     response.sendRedirect("index.jsp");
     return;
 }  
 %>
 
 <%ProfileDto profileDto = new ProfileDto(); 
	profileDto = (ProfileDto)request.getAttribute("profile"); 
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
      <link rel="stylesheet" type="text/css" href="Resources/css/table.css" />
      <link rel="stylesheet" type="text/css" href="Resources/css/button.css" />
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
     
       <form name="sendvalue" action="editprofile" method="post">
		 <input type="hidden" name="fname" />
		 <input type="hidden" name="lname" />
		 <input type="hidden" name="email" />		
         <input type="hidden" name="oldpassword" />
         <input type="hidden" name="newpassword" />
         <input type="hidden" name="bankName" />
         <input type="hidden" name="acNo" />			
      </form>
      
     <script type="text/javascript">
	  
		 function doUpdate() {
		 document.sendvalue.fname.value = document.update.fname.value;
		 document.sendvalue.lname.value = document.update.lname.value;
		 document.sendvalue.email.value = document.update.email.value;
		 if (document.register.oldpassword.value!=""){
         	document.sendvalue.oldpassword.value = hexMD5('\143' + document.register.oldpassword.value + '\362\151\173\074\017\115\314\076\027\131\063\251\076\270\163\142');
		 }
		 else{
			 document.sendvalue.oldpassword.value =""; 
		 }
         if (document.register.newpassword.value!=""){
         	document.sendvalue.newpassword.value = hexMD5('\143' + document.register.newpassword.value + '\362\151\173\074\017\115\314\076\027\131\063\251\076\270\163\142');
         }
         else{
        	 document.sendvalue.newpassword.value="";
         }
         document.sendvalue.bankName.value = document.update.bankName.value;
         document.sendvalue.acNo.value = document.update.acNo.value;
         document.sendvalue.submit();
         return false;			
         }
		 
		 
		 
		 </script>
   </head>
   <body style="height:100%; width:100%; overflow:hidden">
      <!--header-->
      <div style="height:2%; with:100%; background-color:rgba(0, 0, 0, 0.5);"></div>
      <div style="height:12%; with:100%;padding-top:10px; text-shadow: 0px 0px 5px #000000; background-color:rgba(0, 0, 0, 0.2)">
         <div style="height:100%; width:80px;float:left; margin-left:120px; margin-top:-10px">
            <a href="home.jsp"><img src="Resources/images/logo.png"/></a>
         </div>
         <div style="height:100%; width:250px;float:left;font-size:32px;padding-top:12px">
            <a style="color:white; text-decoration:none; text-shadow: 0px 0px 15px #000000;"href="home.jsp">DayBook</a>
         </div>
         <div style="height:100%; width:auto;float:left; margin-top:8px">
            <nav id="mainnav">
               <ul>
                  <li><a href="home.jsp">Home</a></li>
                  <li><a href="transaction.jsp">Transaction</a></li>
                  <li class="active"><a href="profile.jsp">Profile</a></li>
                  <li><a href="query.jsp">Query</a></li>
               </ul>
            </nav>
         </div>
         <div style="height:80%; width:200px;float:right;font-size:14px;color:white; margin-top:6px">
                <!-- login status -->
                <span style="font-size:12px">
				Welcome,
				<%= userSession[1] %> 
				<%= ' ' %>
				<%= userSession[2] %>			
                <br />
                <a href="profile.jsp">Profile</a> | <a href="logout">Logout</a></span>
            </div>
      </div>
	  <!--main body-->
      <div  style="height:77%; with:100%; ">
         <div style="height:90%; width:1066px; margin:auto; background-color:white; <!---webkit-box-shadow:0 0 2px grey;-moz-box-shadow:0 0 2px grey;box-shadow:0 0 2px grey-->">
            <br><span style="font-size:28px; margin:50px; font-style:underline"> Edit Profile</span><br><br>
            <p style="color:red;font-size:11px;margin-left:48px">(Note: Please, modify selected contents or leave unchanged.)</p>
            <form style="font-size:12px;margin-left:50px" action="editprofile" name="update" method="POST">
           	<strong>Personal Information</strong><br/>
            First Name: <input type="text" name="fname" value="<%=profileDto.getF_name() %>" size="35" style="height:24px; margin:5px; margin-left:22px; padding-left:5px"/><br/>
            Last Name:<input type="text" name="lname" size="35" value="<%= profileDto.getL_name() %>" style="height:24px; margin:5px; margin-left:28px;padding-left:5px"/><br/>
            Email Address:<input type="text" name="email" size="35" value="<%= profileDto.getEmail() %>" style="height:24px; margin:5px;; padding-left:5px"/><br/><br/>
            Old Password:<input type="password" name="oldpassword" placeholder="********" size="35" style="height:24px; margin:5px; margin-left:10px;padding-left:5px"/> <br/>
            New Password:<input type="password" name="newpassword" placeholder="********" size="35" style="height:24px; margin:5px; margin-left:5px; padding-left:5px"/><p style="color:red; margin-left:105px"><c:out value="${requestScope.password}"/></p><br/>
            <strong>Bank Information</strong><br/>
            Bank Name: <input type="text" name="bankName" size="35" value="<%=profileDto.getBank_name() %>" style="height:24px; margin:5px; margin-left:18px;padding-left:5px"/><br/>
            Account No:<input type="text" name="acNo" size="35" value="<%=profileDto.getAc_No() %>" style="height:24px; margin:5px; margin-left:25px; padding-left:5px"/><br/>
            <input type="submit" value="Update" size="35" onclick="return doUpdate()" style="height:24px; margin:5px; font-weight:bold"/><br/>
            
            </form>
            <div style="height:20px; width:180px; position:relative; top:0px; left:50px; font-size:12px;">
                  <p style="color:red"><c:out value="${requestScope.msg}"/></p>
            </div>
         </div>
         <div style="height:10%;width:1066px; margin:auto; background-color:rgba(0,0,0,0.7)"> </div>
<!--		<div style="position:absolute; top:555px; left:150px;width:1066px; height:52px;float:left; background-color:rgba(0,0,0,0.7)"></div>-->
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