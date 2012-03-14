<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	<title>web-intro-1-solution: Spring MVC Reward Details</title>
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
		<!-- Use of spring-bind tag allows for formatting of amount value -->
		<spring:bind path="reward.amount">
			<td>Amount:</td>
			<td>${status.value}</td>
		</spring:bind>
	</tr>
	<tr>
		<!-- Use of spring-bind tag allows for formatting of date value -->
		<spring:bind path="reward.date">
			<td>Date:</td>
			<td>${status.value}</td>
		</spring:bind>
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