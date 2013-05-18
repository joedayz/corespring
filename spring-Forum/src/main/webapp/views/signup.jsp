<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="four columns">
		<h4>Bienvenido(a) a Spring F&oacute;rum</h4>
		<p>Esperamos poderle a ayudarle a conocer en profundidad Spring Framework y sus proyectos relacionados</p>
	</div>
	<div class="eight columns">
	<sf:form modelAttribute="usuario" action="executarRegistro" enctype="multipart/form-data">
		<label for="nombre">Nombre:<sf:errors path="nombre" cssClass="erro"/></label>
		<sf:input path="nombre" class="four"/>
		
		<label for="email">Email:<sf:errors path="email" cssClass="erro"/></label>
		<sf:input path="email" class="four"/>
		
		
		<label for="login">Nombre de usuario (login):<sf:errors path="login" cssClass="erro"/></label>
		<sf:input path="login" class="three"/>
		<label for="password">Password:</label>
		<sf:password path="password" class="three"/>
	
		<label for="avatar">Avatar:</label>
		<input type="file" name="avatar"/>
		
		<input type="submit" value="Registrar" class="tiny button success"/>
	</sf:form>
	</div>
</div>