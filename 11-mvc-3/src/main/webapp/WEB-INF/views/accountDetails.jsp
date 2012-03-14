<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	<title>mvc-3-solution: Account Details</title>
</head>

<body>
<div id="main_wrapper">

<h1>Account Details</h1>

<table>
	<tr><td>
		<table>
			<tr>
				<td>Account:</td>
				<td>${account.number}</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td>${account.name}</td>
			</tr>
		</table>
	</td></tr>
	<tr><td>
		<table>
			<thead>
				<tr>
					<td>Beneficiaries:</td>
				</tr>
				<tr>
					<td>Name</td>
					<td>Allocation Percentage</td>
					<td>Savings</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${account.beneficiaries}" var="beneficiary">
					<tr>
						<td>${beneficiary.name}</td>
						<td>${beneficiary.allocationPercentage}</td>
						<td>${beneficiary.savings}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</td></tr>
</table>

<p><a href="editAccount.htm?entityId=${account.entityId}">Edit Account</a></p>

</div>
</body>

</html>
