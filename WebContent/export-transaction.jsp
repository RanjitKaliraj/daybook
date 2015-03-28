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
<title>Insert title here</title>
</head>
<body>
<div style="height:500px; width:900px; margin:auto;background-color:gre3en">
	<div style="height:100px; width:100%;background-color:r3ed">
	</div>
	<div style="height:500px; width:800px;background-color:#">
                            <table border="1"  align="center" style="width:800px;border-spacing:0px;">
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
                            <tbody>
                            
                             		<%int newId = Integer.parseInt(userSession[0]); %>
                                    <%TransactionService transactionService = new TransactionService(); %>
                                    <%TransactionDto transactionDto = new TransactionDto(); %>
                                    <%ArrayList<TransactionDto> transactionlist = transactionService.displayUserTransaction(newId); %>
                                    <%int i=0; %>
                                    <%if (transactionlist.size()>0){ %>                              
                                   
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
	                                   </tbody>
                               	 </table>
                               	 <%}else{%>
                             	<div style="margin-left:400px; padding-top:200px"><p><Strong>Transaction not Available</strong></p></div>
                             <% }%>
                            </div>
	<div style="height:50px; width:100%;background-color:blue">
	</div>





</div>
</body>
</html>