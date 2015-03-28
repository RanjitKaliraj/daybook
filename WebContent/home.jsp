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
 else if (userSession==null){
	 System.out.println("redirect to index page if it is not set. ");
     response.sendRedirect("index.jsp");
     return;
 }
  
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
      <script src="Resources/Resources/js/md5.js"></script>  <!-- password encrypter  -->
      <script src="Resources/Resources/js/modernizr.custom.63321.js"></script>
      <!--[if lte IE 7]>
      <style>.main{display:none;} .support-note .note-ie{display:block;}</style>
      <![endif]-->
      <style type="text/css">
         body{
         font-family:verdana;
         }
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
            <a href="home.html"><img src="Resources/images/logo.png"/></a>
         </div>
         <div style="height:100%; width:250px;float:left;font-size:32px;padding-top:12px">
            <a style="color:white; text-decoration:none; text-shadow: 0px 0px 15px #000000;"href="home.jsp">DayBook</a>
         </div>
         <div style="height:100%; width:auto;float:left; margin-top:8px">
            <nav id="mainnav">
               <ul>
                  <li class="active"><a href="home.jsp">Home</a></li>
                  <li><a href="transaction.jsp">Transaction</a></li>
                  <li><a href="profile.jsp">Profile</a></li>
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
      <div  style="height:77%; with:100%;">
         <div style="height:99%; width:1066px; margin:auto; background-color:#; <!---webkit-box-shadow:0 0 2px grey;-moz-box-shadow:0 0 2px grey;box-shadow:0 0 2px grey-->">
            <div style="height:98%;width:50%;float:left; background-color:#">
               <form style="background-color:rgba(0, 0, 0, 0.4);font-size:12px;color:white; text-shadow: 0px 0px 5px #000000; position:relative; top:-20px; left:-50px; font-weight:bold" class="form-2" action="transaction" name="signup" method="POST">
                  <p style="font-size:16px; text-align:center" > Record new Transaction </p>
                  Description<input style="color:black" type="text" name="description" placeholder="eg. Borrowed from Mike" oninvalid="InvalidMsgDes(this)" oninput="InvalidMsgDes(this)" required="required"/>					
                  Details (Optional)<input style="color:black" type="text" name="detail" placeholder="eg. Need to buy a RayBan from Amazon." />
                  <br>
                  <input type="radio" name="type" value="income" required="required"/>Income 
                  <input type="radio" name="type" value="expenses" required="required"/> Expenses<br><br>
                  Source of Amount 
                  <select style="color:black" name="source" oninvalid="InvalidMsgSrc(this)" oninput="InvalidMsgSrc(this)" required="required"/>
                     <option value="">--Select--</option>
                     <option value="cash">Cash</option>
                     <option value="cheque">Cheque</option>
                     <option value="other">Other</option>
                  </select>
                  <br><br>
                  Amount<input style="color:black" type="text" name="amount" placeholder="Enter amount" oninvalid="InvalidMsgAmt(this)" oninput="InvalidMsgAmt(this)" required="required"/>
                  <p class="signup" style="width:100px;color:black;font-size:14px">
                     <button  type="submit" name="record">Record</button>							
                  </p>
               </form>  
                <div style="height:20px; width:180px; position:relative; top:-100px; left:140px; font-size:12px;">
                  <p style="color:red">
                  <c:out value="${requestScope.msg}"/>
                  </p>
             </div>       
            </div>
           
            <div style="height:98%;width:50%;float:left; background-color:#">
               <div style="height:50%;width:100%;float:left; background-color:#">
               </div>
               <div style="height:50%;width:100%;float:left; background-color:#">
               </div>
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
