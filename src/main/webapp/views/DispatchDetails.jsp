<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.List" %>
 <%@ page import="java.util.ArrayList" %>
 <%@ page import="com.verizon.cab.management.domain.User" %>
<!DOCTYPE html>
<html class="vz-theme" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Technician Dispatch Details</title>
<link href="resources/jquery-contextmenu.css"
	rel="stylesheet" type="text/css">
<link href="resources/jquery.jscrollpane.css"
	rel="stylesheet" type="text/css">
<link href="resources/Default.htm.style.min.css"
	rel="stylesheet" type="text/css">
<link href="resources/custom.style.css"
	rel="stylesheet" type="text/css">

<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<%
String empid = request.getAttribute("empid")!=null?(String)request.getAttribute("empid"):"";
String firstname = request.getAttribute("firstname")!=null?(String)request.getAttribute("firstname"):"";
String lastname = request.getAttribute("lastname")!=null?(String)request.getAttribute("lastname"):"";
String email = request.getAttribute("email")!=null?(String)request.getAttribute("email"):"";
String startDate = request.getAttribute("startDate")!=null?(String)request.getAttribute("startDate"):"";
String startTimeHr = request.getAttribute("startTimeHr")!=null?(String)request.getAttribute("startTimeHr"):"00";
String startTimeMin = request.getAttribute("startTimeMin")!=null?(String)request.getAttribute("startTimeMin"):"00";
String location = request.getAttribute("location")!=null?(String)request.getAttribute("location"):"";
String currentPool = request.getAttribute("currentPool")!=null?(String)request.getAttribute("currentPool"):"";
List<User> providerList = request.getAttribute("providerList")!=null?(List<User>)request.getAttribute("providerList"):new ArrayList<User>();
String providers = request.getAttribute("providers")!=null?(String)request.getAttribute("providers"):"[]";
%>
<script>
var providerLocations = <%=providers%>;
var takerLocation = '<%=location%>';	
var takerlatitude = takerLocation.split(",")[0];
var takerlongitude = takerLocation.split(",")[1];
	/*[
  ['Surendra Ganti', -33.890542, 151.274856, "P"],
  ['Pavan Satya', -33.923036, 151.259052, "P"],
  ['Pavan Akruthi', -33.80010128657071, 151.28747820854187, "P"],
  ['Jai Lakshmi Narasimha Swamy', -33.950198, 151.259302, "P"]
];*/ 
	function submitRequest()
	{
		document.getElementById('poolRequest').submit();
	}
</script>

</head>

<body class="isIE" style="cursor: default;" >
<div class="header">
		<div class="layout">
			<div class="VZlogo">
				<IMG title="Verizon" alt="Verizon" src="/resources/VerizonLogo.png"
					width="110%" height="110%">
			</div>
			<div class="app-name">Technician Dispatch Route Planner</div>
			<div class="app-support">&nbsp;<BR>
			Email: <A href="mailto:dispatch-route-planner@one.verizon.com">vdsi.dispatchrouteplanner@gmail.com</A><BR>
			&nbsp;<BR>
			</div>
		</div>
	</div>

	<div class="mast1" style = "font-weight: bold; height: 40px; color: white; overflow: hidden; font-size: 14px; display: block; background-color: rgb(231, 28, 36); margin: auto;">&nbsp;&nbsp;
		<table style = "margin-top: 5px; float: left;">
			<tr>
				<td>
					<a href="login?username=<%=empid%>" id="dispatchDetails" title="dispatchDetails" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Pooling Request</a>&nbsp;&nbsp;
				</td>				
				<td>
					<a href="report?username=<%=empid%>" id="reports" title="reports" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Report</a>&nbsp;&nbsp;
				</td>
				<td>
					<a href="faq?username=<%=empid%>" id="faq" title="FAQ's" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">FAQ's</a>
				</td>
				<td>
					<a href="/" id="logout" title="Log Out" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Log Out</a>
				</td>
			</tr>
		</table>
	</div>
	<form action = "submitRequest" id = "poolRequest" method="post">
	<div id="container" class="container" >
		
		<div id="empInfo" class="empInfo"> 
			<fieldset class="fsempInfo">
				<legend class="legend">Employee Details</legend>
				<section class="readOnlySection">
					<label class="readOnlyLabel">Employee ID : </label> <label><%=empid%></label>
				</section >
				<section class="readOnlySection">
					<label  class="readOnlyLabel">Employee Name : </label> <label><%=firstname%>, <%=lastname%></label>
				</section>
				<section class="readOnlySection">
					<label  class="readOnlyLabel">E-mail : </label> <label><%=email%></label>
				</section>
				<section class="readOnlySection">
					<label  class="readOnlyLabel">Current Pool : </label> <label><%=currentPool%></label>
				</section>
				<section class="readOnlySection">
					<label  class="readOnlyLabel">Start Date : </label> <label><%=startDate%></label>
				</section>
				<section class="readOnlySection">
					<label  class="readOnlyLabel">Estimated Start Time : </label> <label><%=startTimeHr%>:<%=startTimeMin%></label>
				</section>
			</fieldset>
		</div>
			
			
		<div  class="poolingAvlDetails"> 
			<table style="width:100%;">
				<tr>
					<td class="poolAvlMap">
						<div  >
							<jsp:include page="GMapsWithMultipleMarkers.jsp" />
						</div >
					</td>
					<td class="poolAvlGrid">					
						<div  style="height:450px;overflow:auto;">
							<%for(int index=0; index<providerList.size(); index++) { 
								
								User user = providerList.get(index);
								if(index%2==0){%>
								
								<div id="avlVehicle" class="avlVehicleEven">
								<%}else{ %>
									<div id="avlVehicle" class="avlVehicleOdd">
								<%} %>
									<table style="width:100%;">
										<tr><td><label  class="readOnlyLabel">Name:</label><%=(user.getFirstName()+" "+user.getLastName())%> <input type="radio" id="<%=index %>" style="float:right;" name="avlVehicleChk" value="<%=user.getId()%>"></td></tr>
										<tr><td><label  class="readOnlyLabel">Email:</label><%=user.getEmail()%></td></tr>
										<tr><td><label  class="readOnlyLabel">Mobile #:</label><%=user.getPhoneNumber()%></td></tr>
										<tr><td><label  class="readOnlyLabel">Vehicle Type:</label><%=user.getVehicleType()%></td></tr>
										<tr><td><label  class="readOnlyLabel">Address:</label><%=user.getAddressDesc()%></td></tr>
									</table>
								</div>
								<%}%>
						</div >
					</td>
				</tr>
			</table>
		</div>
	
		<div id="btnContainer" class="btnContainer">
			<input type = "button" value="Submit Request" onclick = "javascript: submitRequest();"style = "background-color: rgb(231, 28, 36); color : white; FLOAT: left; MARGIN-LEFT: 5px;">
		</div>
	
	</div>
	<input type= "hidden" id ="userId" name = "userId" value = "<%=empid%>">	
	</form>
</body>
</html>