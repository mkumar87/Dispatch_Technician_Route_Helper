<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="vz-theme" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dispath Technician Route helper</title>
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
			<div class="app-name">Dispath Technician Route helper</div>
			<div class="app-support">&nbsp;<BR>
			Email: <A href="mailto:VDSI-CarPoolingSystem@one.verizon.com">vdsi@gmail.com</A><BR>
			&nbsp;<BR>
			</div>
		</div>
	</div>

	<div class="mast1" style = "font-weight: bold; height: 40px; color: white; overflow: hidden; font-size: 14px; display: block; background-color: rgb(231, 28, 36); margin: auto;">&nbsp;&nbsp;
		<table style = "margin-top: 5px; float: left;">
			<tr>
				<td>
					<a href="login?username=<%=username%>" id="poolingRequest" title="poolingRequest" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Dispatch Request</a>&nbsp;&nbsp;
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
			
			<h4>What is the Dispatcher?</h4>
			The Dispatcher is the Adobe caching and/or load balancing tool that helps realize a fast and dynamic Web authoring environment. For caching, the Dispatcher works as part of an HTTP server, such as Apache, with the aim of storing (or "caching") as much of the static website content as possible and accessing the website's layout engine as infrequently as possible. In a load balancing role, the Dispatcher distributes user requests (load) across different clustered CQ instances (renders).
			For caching, the Dispatcher module uses the Web server's ability to serve static content. The Dispatcher places the cached documents in the document root of the Web server.
			<br>
			<h4>How does the Dispatcher determine if a document is up-to-date?</h4>
			To determine whether a document is up-to-date, the Dispatcher performs these actions:

			It checks whether the document is subject to auto-invalidation. If not, the document is considered up-to-date.
			If the document is configured for auto-invalidation, the Dispatcher checks whether it is older or newer than the last change available. If it is older, the Dispatcher requests the current version from the CQ instance and replaces the version in the cache.
			<br>
			
			<h4>What does the marker colors indicate on Report tab?</h4>
			Red -   indicates users who are in need of car pool.
			<br>
			Green - indicates providers who offer car pool.
			<br>
			Blue -  indicate users/providers who are already part of car pool.
			<br>
			
		</td>
	</tr>
</table>
	
</div>
<div class="footer">
   <div class="layout">
       <div class="copyrights">&copy; 2015 Designed by Rockers (Jeeva,Muthu,Sakthi) <a href="#">FAQ</a> |  <a href="#">Feedback</a></div>
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
