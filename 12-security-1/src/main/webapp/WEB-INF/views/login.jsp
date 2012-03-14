<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	<title>security-1-solution: Login</title>
</head>

<body>
<div id="main_wrapper">

<h1>Login</h1>

<c:if test="${!empty param.login_error}">
	<h2><spring:message code="login.invalid"/></h2>
</c:if>

<form action="<c:url value='/j_spring_security_check'/>" method="post">
	<table>
		<tr>
			<td>Username:</td>
			<td><input type="text" name="j_username"/></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="j_password"/></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit" value="Login"/></td>
		</tr>
	</table>
</form>

</div>
</body>

</html>
