<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>

	<title><tiles:getAsString name="titulo"/></title>
</head>

<body>
	
	<tiles:insertAttribute name="cabecera"/>
	
	<tiles:insertAttribute name="contenido"/>
	
	<tiles:insertAttribute name="pie"/>

</body>

</html>