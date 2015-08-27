<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="vz-theme" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car Pooling System</title>
<LINK href="resources/jquery-contextmenu.css"
	rel="stylesheet" type="text/css">
<LINK href="resources/jquery.jscrollpane.css"
	rel="stylesheet" type="text/css">
<LINK href="resources/Default.htm.style.min.css"
	rel="stylesheet" type="text/css">
<LINK href="resources/custom.style.css"
	rel="stylesheet" type="text/css">

</head>
<%
String username = request.getAttribute("username")!=null?(String)request.getAttribute("username"):"";
%>
<body class="isIE" style="cursor: default;">
<div class="header">
		<div class="layout">
			<div class="VZlogo">
				<IMG title="Verizon" alt="Verizon" src="/resources/VerizonLogo.png"
					width="110%" height="110%">
			</div>
			<div class="app-name">Car Pooling System</div>
			<div class="app-support">&nbsp;<BR>
			Email: <A href="mailto:VDSI-CarPoolingSystem@one.verizon.com">vdsi.carpooling@gmail.com</A><BR>
			&nbsp;<BR>
			</div>
		</div>
	</div>

	<div class="mast1" style = "font-weight: bold; height: 40px; color: white; overflow: hidden; font-size: 14px; display: block; background-color: rgb(231, 28, 36); margin: auto;">&nbsp;&nbsp;
		<table style = "margin-top: 5px; float: left;">
			<tr>
				<td>
					<a href="login?username=<%=username%>" id="poolingRequest" title="poolingRequest" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Pooling Request</a>&nbsp;&nbsp;
				</td>				
				<td>
					<a href="report?username=<%=username%>" id="reports" title="reports" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Reports</a>&nbsp;&nbsp;
				</td>
				<td>
					<a href="#" id="faq" title="FAQ's" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">FAQ's</a>
				</td>
				<td>
					<a href="/" id="logout" title="Log Out" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Log Out</a>
				</td>
			</tr>
		</table>
	</div>
<div class="layout" style="float:left;display:inline;">
<table style="width:100%;">
	<tr>
		<td style="width:99%;">
			<div class="block" >
			
			<h4>How do I place a pooling request?</h4>
			Whether you want to offer(provider) or need(user) a car pool, Use Pooling Request tab to set your exact home location using the marker on the map and submit.
			<br>
			<h4>I am here to offer car pool, what happens next after I place a pooling request?</h4>
			Application would scan all user's location data who are in need of car pool and filter only the users who fall in your drive route and alert them over email sharing your contact information. Also you would be alerted too with their contacts on your registered email.
			<br>
			<h4>I am here for need of car pool, what happens next after I place a pooling request?</h4>
			Application would scan all provider's location data who offer car pool, have a vacant seat in their vehicle and who's drive route falls within your location and show those provider's on the next page after you place the pooling request.
			You can choose a provider based on your preference and submit request. An email alert will be sent to your provider with your contact details.
			<br>
			<h4>What does the marker colors indicate on Report tab?</h4>
			Red -   indicates users who are in need of car pool.
			<br>
			Green - indicates providers who offer car pool.
			<br>
			Blue -  indicate users/providers who are already part of car pool.
			<br>
			<div class="VZFooter"><img title="Go Green" alt="Go Green" style="margin-left: -18px;"
			src="resources/goGreen.gif" width = "110%" height = "110%" ></div>
			<h4>What happens if I change my home location after I enrol in a provider's car pool?</h4>
			We run our backend algorithm to see if you are still a fit in the existing car pool, if not we will drop you from that car pool, alert you and the provider over email and you need to choose a new provider again otherwise its BAU.
			
			<br>
			<h3 style = "font-weight: bold; color: green;">SAVE FUEL. SAVE ENVIRONMENT. GO GREEN.</h3>
			
			<hr>
		</td>
	</tr>
</table>
	
</div>
<div class="footer">
   <div class="layout">
       <div class="copyrights">&copy; 2015 Designed by Team Hackers (Surendra Ganti, Pavan Akurathi, Pavan Sathya) <a href="#">FAQ</a> |  <a href="#">Feedback</a></div>
   </div>
</div>

<script type="text/javascript">
	function login()
	{
		
		var userName = document.getElementById('username').value;
		var password = document.getElementById("password").value;
		
		if(userName != null && userName != '' && password != null && password != ''){
			document.getElementById('loginForm').submit();
		}else if(userName == null || userName == ''){
			alert("Please enter your User Name");
		}else{
			alert("Please enter your Password");
		}
	}
</script>


</body>
</html>