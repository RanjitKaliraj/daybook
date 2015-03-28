<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
 String[] userSession = (String[])session.getAttribute("user");
if (userSession==null){
	 System.out.println("redirect to index page if it is not set. ");
     response.sendRedirect("index.jsp");
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
      <link rel="stylesheet" type="text/css" href="Resources/css/table.css" />
      <link rel="stylesheet" type="text/css" href="Resources/css/button.css" />
      <script src="Resources/js/modernizr.custom.63321.js"></script>
      <!--[if lte IE 7]>
      <style>.main{display:none;} .support-note .note-ie{display:block;}</style>
      <![endif]-->
      <style type="text/css">
         body{
         font-family:verdana;
         }
         /*this css make scrollbar hidden in the page*/
         ::-webkit-scrollbar { 
         display: none; 
         }
         table.fixed { table-layout:fixed; }
         table.fixed td { overflow: none; }
      </style>
      <script type="text/javascript">
         //This function checks the description validity
         function InvalidMsgDes(description) {    
         	if (description.value == '') {
         		description.setCustomValidity('Please, Enter Description.');
         	}
         	else {
         		description.setCustomValidity('');
         	}
         	return true;
         }
         
         //This function checks the source validity
         function InvalidMsgSrc(source) {    
         	if (source.value == '') {
         		source.setCustomValidity('Please, Specify Source.');
         	}
         	else {
         		source.setCustomValidity('');
         	}
         	return true;
         }
         
         //This function checks the amount validity
         function InvalidMsgAmt(amount) {    
         	if (amount.value == '') {
         		amount.setCustomValidity('Please, Enter Amount.');
         	}
         	else {
         		amount.setCustomValidity('');
         	}
         	return true;
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
                  <li><a href="profile.jsp">Profile</a></li>
                  <li class="active"><a href="query.jsp">Query</a></li>
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
      <div  style="height:77%; with:100%; ">
         <div style="height:90%; width:1066px; margin:auto; background-color:white; <!---webkit-box-shadow:0 0 2px grey;-moz-box-shadow:0 0 2px grey;box-shadow:0 0 2px grey-->">
            <br><span style="font-size:28px; margin:50px; font-style:underline"> User Query</span><br><br>
            <form  style="margin-left:50px" action="query" method="post">
               <p>
                  <input type="text" size="49px" name="subject" placeholder="subject"/>
               </p>
               <p>
                  <textarea name="message" rows="10" cols="50" placeholder="Write your query..."></textarea>
               <p class="signup" style="width:100px;color:black;font-size:14px">
                  <button  type="submit" name="record">Send</button>							
               </p>
            </form>
            <!--This div is for error message -->
            <div style ="height:40px; width:180px;position:relative; top:-32px; left:150px; color:red; font-size:12px">
               <p style="color:red">
				<c:out value="${requestScope.msg}"/>
				</p>
            </div>
         </div>
         <div style="height:10%;width:1066px; margin:auto; background-color:rgba(0,0,0,0.7)"> </div>
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
</html>