<%@ include file="/common/taglibs.jsp"%>
<title>Spring Forums - ${post.titulo}</title>
<form:form commandName="comentario">
<h1>${post.titulo} - ${post.tema.descripcion}</h1>
<p><small>Fecha: ${post.fechaCreacion}</small></p>

<p>${post.descripcion}</p>

<h3>Deja un comentario:</h3>

<table>
	<tr>
		<td>Nombre (*):</td>
		<td><form:input path="nombre" maxlength="50"/></td>
		<td><form:errors path="nombre"/></td>
	</tr>
	<tr>
		<td>Correo electronico (*):</td>
		<td><form:input path="correoElectronico" maxlength="50"/></td>
		<td><form:errors path="correoElectronico"/></td>
	</tr>
	<tr>
		<td>Comentario (*):</td>
		<td><form:textarea path="comentario" /></td>
		<td><form:errors path="comentario"/></td>
	</tr>	
</table>
<input type="submit" id="Comentar" name="Comentar" value="Comentar"/>
<c:forEach var="comentario" items="${comentarios}" varStatus="comentarioStatus">
	<p>-------------------------------------------------------------------------------------</p>
	<p>${comentarioStatus.index + 1}. ${comentario.nombre} (${comentario.correoElectronico})</p>
	<p>${comentario.comentario}</p>	
</c:forEach>
<a href="<c:url value="/posts.htm?id=${post.tema.id}"/>"><<< Regresar al menu de Posts</a>
</form:form>