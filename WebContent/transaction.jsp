<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page import="com.daybook.service.TransactionService"%>
 <%@ page import="com.daybook.dto.TransactionDto" %>
 <%@ page import="java.util.ArrayList"%>
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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>DayBook</title>
        <meta name="description" content="Custom Login Form Styling with CSS3" />
        <meta name="keywords" content="css3, login, form, custom, input, submit, button, html5, placeholder" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="Resources/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="Resources/css/style.css" />
        <link rel="stylesheet" type="text/css" href="Resources/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="Resources/css/bootstrap-theme.css" />
        <link rel="stylesheet" type="text/css" href="Resources/css/table.css" />
        <link rel="stylesheet" type="text/css" href="Resources/css/button.css" />
        <!-- password encrypter  -->
        <script src="Resources/js/modernizr.custom.63321.js"></script>
        <!--[if lte IE 7]>
      <style>.main{display:none;} .support-note .note-ie{display:block;}</style>
      <![endif]-->
	  <style type="text/css">
         body{
         font-family:verdana;
         }
         /*this css make scrollbar hidden in the page
         ::-webkit-scrollbar { 
         display: none; 
         }*/
         table.fixed { table-layout:fixed; }
         table.fixed td { overflow: none; }
      </style>
    </head>
    <body style="height:100%; width:100%; overflow:hidden">
        <!--header-->
        <div style="height:2%; with:100%; background-color:rgba(0, 0, 0, 0.5);"></div>
        <div style="height:12%; with:100%;padding-top:10px; text-shadow: 0px 0px 5px #000000; background-color:rgba(0, 0, 0, 0.2)">
            <div style="height:100%; width:80px;float:left; margin-left:120px; margin-top:-10px">
                <a href="home.jsp">
                    <img src="Resources/images/logo.png" />
                </a>
            </div>
            <div style="height:100%; width:250px;float:left;font-size:32px;padding-top:12px">
                <a style="color:white; text-decoration:none; text-shadow: 0px 0px 15px #000000;" href="home.jsp">DayBook</a>
            </div>
            <div style="height:100%; width:auto;float:left; margin-top:8px">
                <nav id="mainnav">
                    <ul>
                        <li>
                            <a href="home.jsp">Home</a>
                        </li>
                        <li class="active">
                            <a href="transaction.jsp">Transaction</a>
                        </li>
                        <li>
                            <a href="profile.jsp">Profile</a>
                        </li>
                        <li>
                            <a href="query.jsp">Query</a>
                        </li>
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
        <div style="height:77%; with:100%;">
            <div style="height:100%; width:1066px; margin:auto; background-color:#;">
                <div style="height:100%; width:1000px; background-color:#; float:left; margin-top:-8px">
                    <div style="height:90%; width:100%;background-color:#;">
                        <div style="height:9.57%; width:100%;background-color:#;overflow:hidden">
                            <table style="position:absolute; z-index:1;margin-top:3px">
                                <thead>
                                    <tr>
                                        <th width="8%">ID</th>
                                        <th width="23%">Description</th>
                                        <th width="23%">Details</th>
                                        <th width="10%">Source</th>
                                        <th width="12%">Income</th>
                                        <th width="12%">Expenses</th>
                                        <th width="12%">Date</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div style=" height:90.43%;width:100%;background-color:white; overflow-y:auto; overflow-x:hidden">
                            <div style="height:100%; width:100%">
                            
                             		<%int newId = Integer.parseInt(userSession[0]); %>
                                    <%TransactionService transactionService = new TransactionService(); %>
                                    <%TransactionDto transactionDto = new TransactionDto(); %>
                                    <%ArrayList<TransactionDto> transactionlist = transactionService.displayUserTransaction(newId); %>
                                    <%int i=0; %>
                                    <%if (transactionlist.size()>0){ %>
                                	<table class="fixed" style="margin-top:-5px; font-size:10px;">                               
                                   
	                                    <%while(i<transactionlist.size()){%>
	                                        <tr>
	                                            <td width="8%" height="10px">
	                                                <strong><%= i+1 %></strong>
	                                            </td>
	                                            <%System.out.println("forloop:"+i); %>
	                                            <td width="23%"><%= transactionlist.get(i).getDescription() %></td>
	                                            <td width="23%"><%= transactionlist.get(i).getDetail()%></td>
	                                            <td width="10%"><%= transactionlist.get(i).getSource()%></td>
	                                            <td width="12%">Rs. <%= transactionlist.get(i).getDebit_Amt()%></td>
	                                            <td width="12%">Rs. <%= transactionlist.get(i).getCredit_Amt()%></td>
	                                            <td width="12%"><%= transactionlist.get(i).getDate()%></td>
	                                        </tr>
	                                        <%i++;} %>
                               	 </table>
                               	 <%}else{%>
                             	<div style="margin-left:400px; padding-top:200px"><Strong>Transaction not Available</strong></p></div>
                             <% }%>
                            </div>
                        </div>
                    </div>
                    <div style="height:9.5%; width:100%; background-color:rgba(0,0,0,0.6);font-size:12px;color:white">
                        <div style="height:100%; width:25%; background-color:#;float:left;border:1px solid white">
                            <p style="margin:15px">Transaction View: Week</p>
                        </div>
                        <div style="height:100%; width:43%; background-color:#;float:left;border:1px solid white">
                            <p style="margin:15px;text-align:center">From: 10-10-2014 to 20-2-2014</p>
                        </div>
                        <div style="height:100%; width:32%; background-color:#;float:left">
                            <div style="height:100%; width:100%; background-color:#;float:left">
                                <div style="height:50%; width:100%; background-color:#">
                                <%transactionDto.setUser_Id(Integer.parseInt(userSession[0])); %>
                                <%transactionService.getAmount(transactionDto);%>
                                    <div style="height:100%; width:50%; background-color:#;float:left;text-align:center;border-bottom:1px solid white;;border-top:1px solid white">
                                    Rs. <%=transactionDto.getDebit_Amt() %></div>
                                    <div style="height:100%; width:50%; background-color:#;float:left;text-align:center;border:1px solid white">
                                    Rs. <%=transactionDto.getCredit_Amt() %></div>
                                </div>
                                <div style="height:50%; width:100%; background-color:#;text-align:center;border-bottom:1px solid white;;border-right:1px solid white">
                                Total Rs. <%=transactionDto.getTotalAmt() %></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="height:100%; width:66px; background-color:#; margin-top:-8px;float:right">
                <div style="width:80px;color:white;font-size:16px; font-weight:bold; position:relative; top:-6px; left:10px">
                    <h4>VIEW</h4>
                </div>
                <a style="" href="">
                    <div class="round-button"
                    style="position:relative; top:0px; left:10px; font-size:9px; text-decoration:none;color:white">Custom</div>
                </a> 
                <a style="" href="">
                    <div class="round-button"
                    style="position:relative; top:5px; left:10px; font-size:9px; text-decoration:none;color:white">Month</div>
                </a> 
                <a style="" href="">
                    <div class="round-button"
                    style="position:relative; top:10px; left:10px; font-size:9px; text-decoration:none;color:white">Week</div>
                </a> 
                <a style="" href="">
                    <div class="round-button"
                    style="position:relative; top:15px; left:10px; font-size:9px; text-decoration:none;color:white">All</div>
                </a> 
                <a style="" href="manage-transaction.jsp">
                    <div class="round-button"
                    style="position:relative; top:140px; left:10px; font-size:9px; text-decoration:none;color:white">Manage</div>
                </a> 
                <a style="export" href="">
                    <div class="round-button"
                    style="position:relative; top:145px; left:10px; font-size:9px; text-decoration:none;color:white">Export</div>
                </a></div>
            </div>
        </div>
        <!--footer-->
        <div style="height:6.5%; width:100%;background-color: rgba(0, 0, 0, 0.5);">
            <div style="height:100%;width:20%; float:left"></div>
            <div style="height:100%;width:60%;color:white;float:left; text-align:center;font-size:12px; padding-top:10px">
            <a style="margin:5px; color:white; margin-left:20px" href="#">About</a>| 
            <a style="margin:5px; color:white" href="#">Terms &amp; Conditions</a> | 
            <span style="margin-left:10px">Follow us :</span> 
            <a href="">
            <img src="Resources/images/icons/Facebook.png" height="25" width="25" alt="facebook" title="facebook" /> 
            <a href="">
            <img src="Resources/images/icons/Google+.png" height="25" width="25" alt="google+" title="google+" /> 
            <a href="">
            <img src="Resources/images/icons/Tumblr.png" height="25" width="25" alt="tumblr" title="tumblr" /> 
            <a href="">
                <img src="Resources/images/icons/Twitter.png" height="25" width="25" alt="twitter" title="twitter" />
            </a></a></a></a></div>
            <div style="height:100%;width:20%;float:left"></div>
        </div>
        <div style="height:2.5%; width:100%;background-color: rgba(0, 0, 0, 0.9);color:grey; text-align:center; font-size:10px;">
        <span style="font-weight:bold">DayBook</span> 2014 ɠAll Rights Reserved.</div>
    </body>
</html>