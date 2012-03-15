<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	
 	<script type='text/javascript' src="<c:url value='/dwr/engine.js'/>"></script>
 	<script type='text/javascript' src="<c:url value='/dwr/util.js'/>"></script>	
	<script type='text/javascript' src="<c:url value="/dwr/interface/AccountManager.js"/>"></script>	
	<title>dwr-1-solution: Account Summary</title>
</head>

<body>
	
<div id="main_wrapper">

<h1>Account Summary</h1>
	
<display:table name="accounts" cellspacing="0" cellpadding="0" requestURI="" 
    id="account" pagesize="10" export="true" style="width: 500px" >
    
	<display:column property="name" titleKey="account.owner" escapeXml="true"  sortable="true"
		url="accountDetails.htm?"  paramId="entityId" paramProperty="entityId"/>   
		    		     
	<display:column property="number" titleKey="account.number" escapeXml="true"  sortable="true"/>
		         
    <display:setProperty name="export.excel.filename" value="Accounts.xls"/>
    <display:setProperty name="export.pdf.filename" value="Accounts.pdf"/>
</display:table>		

	<br/>
	<br/>
	<br/>
</div>
</body>
</html>