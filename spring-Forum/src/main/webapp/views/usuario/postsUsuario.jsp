<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<thead>
		<th>Tema</th>
		<th>T&iacute;tulo</th>
	</thead>
	<tbody>
		<c:forEach items="${topicos}" var="post">
		<tr>
			<td>${post.tema.nombre}</td>
			<td>${post.titulo}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>