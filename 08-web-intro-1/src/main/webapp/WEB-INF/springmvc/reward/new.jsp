<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	<title>web-intro-1-solution: Spring MVC Reward Points</title>
</head>

<body>
<div id="main_wrapper">

<h1>Reward Points</h1>


<form:form commandName="rewardForm" method="post">
	<form:errors path="*" cssClass="error" />

	<table>
		<tbody>
			<tr>
				<td>Credit Card Number:</td>
				<td><form:input path="creditCardNumber"/></td>
			</tr>
			<tr>
				<td>Amount:</td>
				<td><form:input path="amount"/></td>
			</tr>
			<tr>
				<td>Merchant Number:</td>
				<td><form:input path="merchantNumber"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Reward Points"/></td>
			</tr>
		</tbody>
	</table>
</form:form>

</div>
</body>

</html>
