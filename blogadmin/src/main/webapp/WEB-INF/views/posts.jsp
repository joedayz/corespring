<%@ include file="/common/taglibs.jsp"%>
<title>Spring Forums - Posts</title>
<h1>${tema.descripcion} - Spring Forums</h1>
<h3>A continuación le presentamos el listado de posts a la fecha:</h3>
<table style="width: 100%">
<c:forEach var="post" items="${posts}" varStatus="postStatus">
	<tr>
		<td colspan="2">
			<a href="<c:url value="/comentarios.htm?idPost=${post.id}"/>">${postStatus.index + 1}. ${post.titulo} - ${post.fechaCreacion}</a>
		</td>
	</tr>	
</c:forEach>	
</table>

<a href="<c:url value="/temas.htm"/>"> <<< Regresar al menu de Temas</a>