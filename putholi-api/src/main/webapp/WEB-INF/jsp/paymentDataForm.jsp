<html>
<head>
<script>
	window.onload = function() {
		var d = new Date().getTime();
		document.getElementById("tid").value = d;
	};
</script>
</head>
<body>
	<form method="post" name="customerData" action="checkout">
		<table width="40%" height="100" border='1' align="center">
			<caption>
				<font size="4" color="blue"><b>Integration Kit</b></font>
			</caption>
		</table>
		<table width="40%" height="100" border='1' align="center">
			<tr>
				<td>Parameter Name:</td>
				<td>Parameter Value:</td>
			</tr>
			<tr>
				<td colspan="2">Compulsory information</td>
			</tr>
			<tr>
				<td>TID	:</td><td><input type="text" name="tid" id="tid" readonly /></td>
			</tr>
			<tr>
				<td>Merchant Id</td>
				<td><input type="text" name="merchant_id" id="merchant_id" value="" /></td>
			</tr>
			<tr>
				<td>Order Id</td>
				<td><input type="text" name="order_id" value="" /></td>
			</tr>
			<tr>
				<td>Currency</td>
				<td><input type="text" name="currency" value="INR" /></td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><input type="text" name="amount" value="1.00" /></td>
			</tr>
			<tr>
				<td>Redirect URL</td>
				<td><input type="text" name="redirect_url"
					value="http://localhost:9100/ccavenue/v1/api/response" />
				</td>
			</tr>
			<tr>
				<td>Cancel URL</td>
				<td><input type="text" name="cancel_url"
					value="http://localhost:9100/ccavenue/v1/api/cancel" />
				</td>
			</tr>
			<tr>
				<td>Language</td>
				<td><input type="text" name="language" id="language" value="EN" /></td>
			</tr>

			<tr>
				<td></td>
				<td><INPUT TYPE="submit" value="Checkout"></td>
			</tr>
		</table>
	</form>
</body>
</html>
