<%@ include file="/common/taglibs.jsp"%>


<title>Spring Forums</title>
<table style="width: 100%">
<h1>Bienvenido a Spring Forums!</h1>
<h3>A continuación le presentamos el listado de temas:</h3>

<c:forEach var="tema" items="${temas}" varStatus="temaStatus">
	<tr>
		<td colspan="2" >
			<a href="<c:url value="/posts.htm?id=${tema.id}"/>">${temaStatus.index + 1}. ${tema.descripcion}</a>
		</td>
	</tr>	
</c:forEach>	
</table>