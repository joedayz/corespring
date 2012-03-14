<%@ taglib prefix="s" uri="/struts-tags"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<s:url value="/css/springsource.css"/>"
	type="text/css" />
<title>struts2-1-solution: Account Summary</title>
</head>

<body>
<div id="main_wrapper">

<h1>Account Summary</h1>

<ul>
	<s:iterator value="%{accounts}">
		<s:url id="editAccountUrl" action="edit">
			<s:param name="id">
				<s:property value="%{entityId}" />
			</s:param>
		</s:url>
		<li><s:a href="%{editAccountUrl}">
			<s:property value="%{name}" />
		</s:a></li>
	</s:iterator>
</ul>

</div>
</body>

</html>