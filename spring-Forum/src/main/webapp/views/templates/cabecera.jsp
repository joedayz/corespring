<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row">
		<div class="three columns">
			<h1>
				<a href="<c:url value="/"/>"><img src="<c:url value="/recursos/images/logotipo.png"/>" /></a>
			</h1>
		</div>
		<div class="five columns right">
			
			
			
			
			<form style="margin-top: 1.0em;" action="<c:url value="/j_spring_security_check"/>" method="post">
				<div class="row">
					<input type="text" name="j_username" placeholder="Usu&aacute;rio" class="three columns right"/>
					<input type="password" name="j_password" placeholder="Senha" class="three columns right"/>
					<input type="submit" value="Entrar" class="tiny button success" class="three columns right"/>&nbsp;
					<a href="<c:url value='registro'/>" class="tiny button success">Registre-se</a>
				</div>
				
			</form>
			
			
			
		</div>

	</div>