
<%@ page import="ar.org.scouts.sifs.Historial" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'historial.label', default: 'Historial')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-historial" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-historial" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list historial">
			
				<g:if test="${historialInstance?.calificacion}">
				<li class="fieldcontain">
					<span id="calificacion-label" class="property-label"><g:message code="historial.calificacion.label" default="Calificacion" /></span>
					
						<span class="property-value" aria-labelledby="calificacion-label"><g:fieldValue bean="${historialInstance}" field="calificacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${historialInstance?.curso}">
				<li class="fieldcontain">
					<span id="curso-label" class="property-label"><g:message code="historial.curso.label" default="Curso" /></span>
					
						<span class="property-value" aria-labelledby="curso-label"><g:link controller="curso" action="show" id="${historialInstance?.curso?.id}">${historialInstance?.curso?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${historialInstance?.fechaAprobacion}">
				<li class="fieldcontain">
					<span id="fechaAprobacion-label" class="property-label"><g:message code="historial.fechaAprobacion.label" default="Fecha Aprobacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaAprobacion-label"><g:formatDate date="${historialInstance?.fechaAprobacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${historialInstance?.persona}">
				<li class="fieldcontain">
					<span id="persona-label" class="property-label"><g:message code="historial.persona.label" default="Persona" /></span>
					
						<span class="property-value" aria-labelledby="persona-label"><g:link controller="persona" action="show" id="${historialInstance?.persona?.id}">${historialInstance?.persona?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:historialInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${historialInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
