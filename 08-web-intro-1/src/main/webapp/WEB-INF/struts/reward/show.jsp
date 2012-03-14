<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	<title>web-intro-1-solution: Struts Reward Details</title>
</head>

<body>
<div id="main_wrapper">

<h1>Reward Details</h1>

<table>
	<tr>
		<td>Confirmation Number:</td>
		<td>${reward.confirmationNumber}</td>
	</tr>
	<tr>
		<td>Amount:</td>
		<td>${reward.amount}</td>
	</tr>
	<tr>
		<td>Date:</td>
		<td>${reward.date}</td>
	</tr>
	<tr>
		<td>Account Number:</td>
		<td>${reward.accountNumber}</td>
	</tr>
	<tr>
		<td>Merchant Number:</td>
		<td>${reward.merchantNumber}</td>
	</tr>
</table>

</div>
</body>

</html>