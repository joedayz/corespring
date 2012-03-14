<%@ taglib prefix="s" uri="/struts-tags"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<s:url value="/css/springsource.css"/>" type="text/css" />
<title>struts2-1-solution: Edit Account</title>
</head>

<body>
<div id="main_wrapper">

<h1>Account Details</h1>

<s:if test="#parameters.saved">
	<h4>Account saved successfully.</h4>
</s:if>

<s:actionerror />
<s:actionmessage />

<s:form action="save" method="post">
	<s:hidden name="account.entityId" value="%{account.entityId}" />
	<table>
		<tr>
			<td>Account:</td>
			<td><s:textfield name="account.number" size="20" value="%{account.number}" /></td>
		</tr>
		<tr>
			<td>Name:</td>
			<td><s:textfield name="account.name" size="20" value="%{account.name}" /></td>
		</tr>
		<tr>
			<td>Credit Card:</td>
			<td><s:textfield name="account.creditCardNumber" size="16" value="%{account.creditCardNumber}" /></td>
		</tr>		
		<tr>
			<td colspan="1"><s:submit value="Save" /></td>
		</tr>
	</table>
</s:form>
<br />
<s:url id="all" action="accountSummary" namespace="/accounts" includeParams="none" /> <s:a href="%{all}">Return to all accounts.</s:a><br />
</div>
</body>

</html>
