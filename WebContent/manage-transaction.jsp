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
      
      
      <!-- this is form modal box -->
      <link rel="stylesheet" href="Resources/simplemodal/css/simplemodal.css" type="text/css" media="screen" title="no title" charset="utf-8">
    <script src="Resources/simplemodal/js/mootools-core-1.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="Resources/simplemodal/js/mootools-more-1.3.1.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="Resources/simplemodal/js/simple-modal.js" type="text/javascript" charset="utf-8"></script>
   
    
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
    
    
    /****************************************************************************************************************************/
	
		window.addEvent("domready", function(e){
		  /* Alert */
		  $("alert").addEvent("click", function(e){
		    e.stop();
		    var SM = new SimpleModal({"btn_ok":"Alert button"});
		        SM.show({
		          "title":"Alert Modal Title",
		          "contents":"Lorem ipsum dolor sit amet..."
		        });
		  })
		  
		  /* Confirm */
		  $("confirm").addEvent("click", function(e){
		    e.stop();
		    var SM = new SimpleModal({"btn_ok":"Confirm button"});
		        SM.show({
		          "model":"confirm",
		          "callback": function(){
		            alert("Action confirm!");
		          },
		          "title":"Confirm Modal Title",
		          "contents":"Lorem ipsum dolor sit amet..."
		        });
		  })
		  
		  /* Modal */
		  $("modal").addEvent("click", function(e){
		    e.stop();
		    var SM = new SimpleModal({"btn_ok":"Confirm button"});
		        // Aggiunge Bottone Conferma
		        SM.addButton("Confirm", "btn primary", function(){
		            alert("Action confirm modal");
		            this.hide();
		        });
		        // Aggiunge Bottone annulla
		        SM.addButton("Cancel", "btn");
		        SM.show({
		          "model":"modal",
		          "title":"Modal Window Title",
		          "contents":"<p ><img style='text-align:center' src='assets/images/simpleModalSmallWhite.png' />Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
		        });
		  })
		  
		  
	<%int newId = Integer.parseInt(userSession[0]); %>
	<%TransactionService transactionService = new TransactionService(); %>
	<%TransactionDto transactionDto = new TransactionDto(); %>
	<%ArrayList<TransactionDto> transactionlist = transactionService.displayUserTransaction(newId); %>
	
	<%int size =transactionlist.size();%>
	
		  
		    /* This modal is for the edit popup box. */
		/****************************************************************************************************************************/
			<%for (int i=0;i<size;i++){%>
			<%int transac_id = transactionlist.get(i).getTransac_Id();%>
				var name = "modal"+<%=i%>;
				var modal = document.getElementById(name);
				modal.addEvent("click", function(e){
					 e.stop();
					    var SM = new SimpleModal({"btn_ok":"Confirm button"});
					        // Aggiunge Bottone Conferma
					        //SM.addButton("Confirm", "btn primary", function(){
					        //    document.update.submit();
					        //    this.hide();
					        //});
					        // Aggiunge Bottone annulla
					        //SM.addButton("Cancel", "btn");
					        SM.show({
					          "model":"modal",
					          "title":"Update Transaction",
					          "contents":"<form style='font-size:12px; class='form-2' action='transaction' name='update' method='GET'><input type='hidden' name='tid' value=<%=transac_id%>  /> Description:<br> <input style='color:black' size='68' type='text' name='description' placeholder='' oninvalid='InvalidMsgDes(this)' oninput='InvalidMsgDes(this)' required='required'/><br/><br/>Details (Optional): <br><input style='color:black' size='68' type='text' name='detail' placeholder='' /><br /><br /> <input type='radio' name='type' value='income' required='required'/> Income    <input type='radio' name='type' value='expenses' required='required'/> Expenses<br><br>Source of Amount <select style='color:black' name='source' oninvalid='InvalidMsgSrc(this)' oninput='InvalidMsgSrc(this)' required='required'/>  <option value=''>--Select--</option> <option value='cash'>Cash</option><option value='cheque'>Cheque</option><option value='other'>Other</option></select> <br><br> Amount: <input style='color:black' size='19' type='text' name='amount' placeholder='' oninvalid='InvalidMsgAmt(this)' oninput='InvalidMsgAmt(this)' required='required'/> <input type='submit' value='Update'/></form> "
						        });
					  })			
			<%}%>
		  /****************************************************************************************************************************/
		  
		  /* Modal Ajax */
		  $("modal-ajax").addEvent("click", function(e){
		    e.stop();
		    var SM = new SimpleModal({"btn_ok":"Confirm button", "width":600});
		        // Aggiunge Bottone Conferma
		        SM.addButton("Confirm", "btn primary", function(){
								// Check
								if( $("confirm-text").get("value") != "DELETE" ){
									$("confirm-delete-error").setStyle("display", "block");
								}else{
									// Your code ...
									this.hide();
								}
		        });
		        // Aggiunge Bottone annulla
		        SM.addButton("Cancel", "btn");
		        SM.show({
		          "model":"modal-ajax",
		          "title":"Are you sure you want to delete this?",
		          "param":{
		            "url":"ajax-content.html",
		            "onRequestComplete": function(){ }
		          }
		        });
		  })
		
		  /* Modal Image */
		  $("modal-image").addEvent("click", function(e){
		    e.stop();
		    var SM = new SimpleModal({
		          "onAppend":function(){
		            $("simple-modal").fade("hide").fade("in")
		          }
		        });
		        SM.show({
		          "model":"modal-ajax",
							"title":"Modal Lightbox",
		          "param":{
		            "url":"assets/images/lightbox.jpg",
		            "onRequestComplete": function(){ }
		          }
		        });
		  })
		
		  /* NO Header */
		  $("alert-noheader").addEvent("click", function(e){
		    e.stop();
		    var SM = new SimpleModal({"hideHeader":true, "closeButton":false, "btn_ok":"Close window", "width":600});
		        SM.show({
		          "model":"alert",
		          "contents":"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
		        });
		  })
		  
		  /* NO Footer */
		  $("modal-nofooter").addEvent("click", function(e){
		    e.stop();
		    var SM = new SimpleModal({"hideFooter":true, "width":710});
		        SM.show({
		          "title":"Vimeo video",
		          "model":"modal",
		          "contents":'<iframe src="http://player.vimeo.com/video/26198635?title=0&amp;byline=0&amp;portrait=0&amp;color=824571" width="680" height="382" frameborder="0" webkitAllowFullScreen allowFullScreen></iframe>'
		        });
		  })
		  
		  $("example-eheh").addEvent("click", function(e){
		    e.stop();
		    var SM = new SimpleModal(
		            {
		              "btn_ok":"Confirm button",
		              "overlayClick":false,
		              "width":300,
		              "onAppend":function(){
		                $("simple-modal").fade("hide");
		                setTimeout((function(){ $("simple-modal").fade("show")}), 200 );
		                var tw = new Fx.Tween($("simple-modal"),{
		                  duration: 1600,
		                  transition: 'bounce:out',
		                  link: 'cancel',
		                  property: 'top'
		                }).start(-400, 150)
		
		                var item = $("simple-modal").getElement(".simple-modal-footer a");
		                    item.removeClass("primary").setStyles({"background":"#824571","color": "#FFF" });
		                    item.getParent().addClass("align-left");
			                  item.addEvent("mouseenter", function(){
			                    var parent = this.getParent();
			                    if( parent.hasClass("align-left") ){
			                      parent.removeClass("align-left").addClass("align-right");
			                    }else{
			                      parent.removeClass("align-right").addClass("align-left");
			                    }
			                  })
		              }
		            });
		        // Aggiunge Bottone Conferma
		        SM.addButton("Click ME please!", "btn primary", function(){});
		        SM.show({
		          "model":"modal",
		          "title":"Eh eh eh",
		          "contents":"<p>Try clicking on the button \"Click ME please!\"</p>"
		        });
		  });
		});
    
		/****************************************************************************************************************************/
	 
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
                  <li class="active"><a href="transaction.jsp">Transaction</a></li>
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
      <div  style="height:77%; with:100%; ">
      
      <!------------ This has nothing to do with page and it is just for modal box dependency. modal box not work with out this---------------------- -->
      <ul style="list-style:none"><li id="alert"></li><li id="confirm"></li><li id="modal"></li><li id="modal-ajax"></li><li id="modal-image"></li></ul>
      <!-- ------------------------------------------------------------------------------------------------------------------------------------------ -->
      
         <div style="height:100%; width:1066px; margin:auto; background-color:#;">
			 <div style="height:100%; width:1000px; background-color:#; float:left; margin-top:-8px">
				<div style="height:100%; width:100%;background-color:#">
					
					<div style="height:8.7%; width:100%;background-color:#;overflow:hidden">
						<table style="position:absolute; z-index:1;margin-top:3px">
						
						  <thead>
							 <tr>
								<th width="6%">ID</th>
                                <th width="20%">Description</th>
                                <th width="20%">Details</th>
                                <th width="10%">Source</th>
                                <th width="11%">Income</th>
                                <th width="11%">Expenses</th>
                                <th width="11%">Date</th>								
								<th width="11%">Manage</th>
							 </tr>
						  </thead>
					   </table>
					</div>
					
					<div style=" height:90.5%;width:100%;background-color:white; overflow-y:auto; overflow-x:hidden">
						<div style="height:100%; width:100%">
							<table class="fixed"style="margin-top:-5px; font-size:10px;">
							  <tbody>
									 <%int i=0; %>
                                    <%while(i<transactionlist.size()){%>
                                        <tr>
                                            <td id=<%=i%> width="6%" height="10px">
                                                <strong><%= i+1 %></strong>
                                            </td>
                                            <td width="20%"><%= transactionlist.get(i).getDescription() %></td>
                                            <td width="20%"><%= transactionlist.get(i).getDetail()%></td>
                                            <td width="10%"><%= transactionlist.get(i).getSource()%></td>
                                            <td width="11%">Rs. <%= transactionlist.get(i).getDebit_Amt()%></td>
                                            <td width="11%">Rs. <%= transactionlist.get(i).getCredit_Amt()%></td>
                                            <td width="11%"><%= transactionlist.get(i).getDate()%></td>
                                            <td "width="11%"><span id="modal<%=i%>"><a href="#">Edit</a></span><%=" | " %><a href="delete-transaction?delete-id=<%= transactionlist.get(i).getTransac_Id()%>">Delete</a></td>
                                        </tr>
                                        <%i++;} %>							
						
							  </tbody>
						   </table>
						</div>
					</div>					
				</div>
				 
				
			 </div>
			<div style="height:100%; width:66px; background-color:#; margin-top:-8px;float:right">
               <a style="" href="transaction.jsp">
                  <div class="round-button" style="position:relative; top:5px; left:10px; font-size:9px; text-decoration:none;color:white" >GoBack</div>
               </a>
			   <div style="position:relative; top:50px; left:30px; font-size:22px;">
                  <p style="color:red">
                  <c:out value="${requestScope.msg}"/>
                 
                  </p>
             </div>
			   <a style="" href="delete-transaction?action=delete-all">
                  <div class="round-button" style="position:relative; top:400px; left:10px; font-size:9px; text-decoration:none;color:white" >DeleteAll</div>
               </a>              
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