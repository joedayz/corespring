<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
	<link rel="stylesheet" href="<c:url value="/recursos/stylesheets/foundation.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/recursos/stylesheets/estilo.css"/>" />
	<title><tiles:getAsString name="titulo" /></title>
	<script type="text/javascript" src="<c:url value="/recursos/javascripts/jquery.js"/>"></script>
</head>
<body>
	<tiles:insertAttribute name="cabecera"/>
	
	<tiles:insertAttribute name="contenido"/>
	
	<tiles:insertAttribute name="pie"/>

</body>

</html>