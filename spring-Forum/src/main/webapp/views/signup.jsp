<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<sf:form modelAttribute="usuario" action="executarRegistro">
	<label for="nombre">Nombre:</label>
	<sf:input path="nombre"/>

	<label for="email">Email:</label>
	<sf:input path="email"/>

	<label for="login">Nombre de usuario (login):</label>
	<sf:input path="login"/>

	<label for="password">Password:</label>
	<sf:password path="password"/>
	
	<input type="submit" value="Registrar"/>

</sf:form>

