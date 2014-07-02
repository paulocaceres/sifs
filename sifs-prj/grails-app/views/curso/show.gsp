
<%@ page import="ar.org.scouts.sifs.Curso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-curso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list curso">
			
				<g:if test="${cursoInstance?.contenido}">
				<li class="fieldcontain">
					<span id="contenido-label" class="property-label"><g:message code="curso.contenido.label" default="Contenido" /></span>
					
						<span class="property-value" aria-labelledby="contenido-label"><g:link controller="contenido" action="show" id="${cursoInstance?.contenido?.id}">${cursoInstance?.contenido?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.correlativas}">
				<li class="fieldcontain">
					<span id="correlativas-label" class="property-label"><g:message code="curso.correlativas.label" default="Correlativas" /></span>
					
						<span class="property-value" aria-labelledby="correlativas-label"><g:fieldValue bean="${cursoInstance}" field="correlativas"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.cupo}">
				<li class="fieldcontain">
					<span id="cupo-label" class="property-label"><g:message code="curso.cupo.label" default="Cupo" /></span>
					
						<span class="property-value" aria-labelledby="cupo-label"><g:fieldValue bean="${cursoInstance}" field="cupo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="curso.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${cursoInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.inscripto}">
				<li class="fieldcontain">
					<span id="inscripto-label" class="property-label"><g:message code="curso.inscripto.label" default="Inscripto" /></span>
					
						<span class="property-value" aria-labelledby="inscripto-label"><g:link controller="persona" action="show" id="${cursoInstance?.inscripto?.id}">${cursoInstance?.inscripto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.nivel}">
				<li class="fieldcontain">
					<span id="nivel-label" class="property-label"><g:message code="curso.nivel.label" default="Nivel" /></span>
					
						<span class="property-value" aria-labelledby="nivel-label"><g:link controller="nivel" action="show" id="${cursoInstance?.nivel?.id}">${cursoInstance?.nivel?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="curso.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${cursoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.plan}">
				<li class="fieldcontain">
					<span id="plan-label" class="property-label"><g:message code="curso.plan.label" default="Plan" /></span>
					
						<span class="property-value" aria-labelledby="plan-label"><g:link controller="plan" action="show" id="${cursoInstance?.plan?.id}">${cursoInstance?.plan?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.zona}">
				<li class="fieldcontain">
					<span id="zona-label" class="property-label"><g:message code="curso.zona.label" default="Zona" /></span>
					
						<span class="property-value" aria-labelledby="zona-label"><g:link controller="zona" action="show" id="${cursoInstance?.zona?.id}">${cursoInstance?.zona?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:cursoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${cursoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
