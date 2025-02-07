<%
/*
   This is the sample Checkout Page JSP script. It can be directly used for integration with CCAvenue if your application is developed in JSP. You need to simply change the variables to match your variables as well as insert routines (if any) for handling a successful or unsuccessful transaction.
*/
%>
<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*" %>
<html>
<head>
	<title>Sub-merchant checkout page</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>

	<form id="nonseamless" method="post" name="redirect" action="https://test.ccavenue.com/transaction.do?command=initiateTransaction"/>
		<input type="hidden" id="encRequest" name="encRequest" value="${encRequest}">
		<input type="hidden" name="access_code" id="access_code" value="${accessCode}">
		<script language='javascript'>document.redirect.submit();</script>
	</form>
	
 </body> 
</html>
