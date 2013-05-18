<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div style="background: #48762A;">

	<div class="row">
		<div class="twelve columns">
			<img src="recursos/images/chamada.jpg" />
		</div>
	</div>

</div>

<div class="row">
	<div class="two columns">
		<h5>Temas</h5>
		<ul class="side-nav">
			<c:forEach items="${temas}" var="tema">
				<li><a href="tema/${tema.id}">${tema.nombre}</a></li>
				
			</c:forEach>
		</ul>
	</div>
	
	<div class="eight columns">
		<h5>Texto</h5>
		<p>Nuestro foro de Comunidades de Java</p>
		
		<p>Sea libre en publicar y responda moderadamente</p>
	</div>
	
	<div class="two columns">
		<h5>Ultimos miembros</h5>
		<ul class="side-nav">
			<c:forEach items="${usuarios}" var="usuario">
				<li><a href="usuario/show/${usuario.id}">${usuario.nombre}</a></li>
				
			</c:forEach>
		</ul>
	</div>
		
		
	
</div>
