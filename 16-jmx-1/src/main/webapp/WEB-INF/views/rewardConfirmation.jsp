<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	<title>jmx-1-solution: Reward Confirmation</title>
</head>

<body>
<div id="main_wrapper">

<h1>Reward Confirmation</h1>

<p>
	Account '${rewardConfirmation.accountContribution.accountNumber}' rewarded a
	${rewardConfirmation.accountContribution.amount} benefit for dining at merchant '${dining.merchantNumber}'
</p>

<table>
	<thead>
		<tr>
			<td>Name</td>
			<td>Allocation Percentage</td>
			<td>Amount Awarded</td>
			<td>Total Savings</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="distribution" items="${rewardConfirmation.accountContribution.distributions}">
			<tr>
				<td>${distribution.beneficiary}</td>
				<td>${distribution.percentage}</td>
				<td>${distribution.amount}</td>
				<td>${distribution.totalSavings}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</div>
</body>

</html>
