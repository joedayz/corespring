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
	<title>mvc-3-solution: Edit Account Details</title>
</head>

<body>
<div id="main_wrapper">

<h1>Edit Account Details</h1>

<form:form commandName="account">
	<table>
		<tr>
			<td>Number:</td>
			<td><form:input path="number"/></td>
			<td><form:errors path="number"/></td>
		</tr>
		<tr>
			<td>Name:</td>
			<td><form:input path="name"/></td>
			<td><form:errors path="name"/></td>
		</tr>
		<tr>
			<td colspan="3"><input type="submit" value="Update Account"/></td>
		</tr>
	</table>
</form:form>

</div>
</body>

</html>
