<!DOCTYPE html>
<html class="vz-theme">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dispatch Technician</title>
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
String message = request.getAttribute("message")!=null?(String)request.getAttribute("message"):"";
%>
<body class="isIE" style="cursor: default;">


<div class="header">
<div class="layout">
<div class="VZlogo"><IMG title="Verizon" alt="Verizon" style="margin-left: -18px;"
	src="resources/VerizonLogo.png" width = "110%" height = "110%" ></div>
<div class="app-name">Dispatch Technician Route Planner</div>
<div class="app-support">&nbsp;<BR>
Email: <A href="mailto:VDSI@one.verizon.com">vdsi@gmail.com</A><BR>
&nbsp;<BR>
</div>
</div>
</div>

<div class="mast">
<div class="layout">
<div class="mast-content"></div>
</div>
</div>

<div class="layout" style="float:left;display:inline;">
<table style="width:100%;">
	<tr>
	
		<td style="width:60%;">
			<div class="block" >
			<h1>Verizon Dispatch Technician Route Planner</h1><br>
			Dear All, Dispatchers are communications personnel responsible for receiving and 
			transmitting pure and reliable messages, tracking vehicles and equipment, and 
			recording other important information.A number of organizations, including telecom 
			services,police and fire departments, emergency medical services, taxicab providers, 
			trucking companies,railroads, and public utility companies, use dispatchers to relay 
			information and coordinate their operations. 
			
			Essentially, the dispatcher is the "conductor" of the force, and is responsible for 
			the direction of all units within it.
			<br><br>
			****** TIME IS PRECIOUS. USE IT EFFECTIVELY ***********************
			<br>
			
		</td>
		
		<td style="width:40%;"> 
		
			<div class="login-content" >

			<div class="signin-block">
			<div class="login-text"><font color=red><%=message%></font></div>
			<div class="login-text">Sign in with your User Id and
			Password.</div>
			
			<div id="logonbox-container">
			<div id="logonbox-innerbox" >
			<div id="logonbox-logonform" >
			<form id = "loginForm" class="form insertPoint credentialform" action = "login" method = "post">
			<div class="field CredentialTypeusername">
			<div class="left"><label class="label plain" for="username">User
			name:</label></div>
			<div class="right"><input name="username" id="username"
				spellcheck="false" type="text" autocomplete="off"></div>
			</div>
			<div class="field CredentialTypepassword">
			<div class="left"><label class="label plain" for="password">Password:</label></div>
			<div class="right"><input name="password" id="password"
				spellcheck="false" type="password" autocomplete="off"></div>
			</div>
			<div class="field buttonsrow">

			<div class="right"><a class="button"
				id="loginBtn"  onkeypress="login();" onclick= "login();" >Log On</a></div>
			</div>
			<div class="spacer"></div>
			</form>
			</div>
			</div>
			</div>
			
			
			<div id="logonbox-choices"><!-- placeholder where the first-time-use choices screen will be generated -->
			</div>
			</div>
			</div>

		</td>
	</tr>
</table>
	
</div>
<div class="layout">   
       <div class="copyrights"><b>&copy; 2015 Designed by Rockers (Jeeva, Muthu, Sakthi) </b><a href="#">FAQ</a> |  <a href="#">Feedback</a></div>
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
