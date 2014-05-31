
<%@ page import="ar.org.scouts.sifs.domain.Persona" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'persona.label', default: 'Persona')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-persona" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-persona" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list persona">
			
				<g:if test="${personaInstance?.apellido}">
				<li class="fieldcontain">
					<span id="apellido-label" class="property-label"><g:message code="persona.apellido.label" default="Apellido" /></span>
					
						<span class="property-value" aria-labelledby="apellido-label"><g:fieldValue bean="${personaInstance}" field="apellido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.bloqueado}">
				<li class="fieldcontain">
					<span id="bloqueado-label" class="property-label"><g:message code="persona.bloqueado.label" default="Bloqueado" /></span>
					
						<span class="property-value" aria-labelledby="bloqueado-label"><g:formatBoolean boolean="${personaInstance?.bloqueado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.direccion}">
				<li class="fieldcontain">
					<span id="direccion-label" class="property-label"><g:message code="persona.direccion.label" default="Direccion" /></span>
					
						<span class="property-value" aria-labelledby="direccion-label"><g:link controller="direccion" action="show" id="${personaInstance?.direccion?.id}">${personaInstance?.direccion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.documentoNumero}">
				<li class="fieldcontain">
					<span id="documentoNumero-label" class="property-label"><g:message code="persona.documentoNumero.label" default="Documento Numero" /></span>
					
						<span class="property-value" aria-labelledby="documentoNumero-label"><g:fieldValue bean="${personaInstance}" field="documentoNumero"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.mail}">
				<li class="fieldcontain">
					<span id="mail-label" class="property-label"><g:message code="persona.mail.label" default="Mail" /></span>
					
						<span class="property-value" aria-labelledby="mail-label"><g:fieldValue bean="${personaInstance}" field="mail"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="persona.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${personaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.superior}">
				<li class="fieldcontain">
					<span id="superior-label" class="property-label"><g:message code="persona.superior.label" default="Superior" /></span>
					
						<span class="property-value" aria-labelledby="superior-label"><g:link controller="persona" action="show" id="${personaInstance?.superior?.id}">${personaInstance?.superior?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.zona}">
				<li class="fieldcontain">
					<span id="zona-label" class="property-label"><g:message code="persona.zona.label" default="Zona" /></span>
					
						<span class="property-value" aria-labelledby="zona-label"><g:link controller="zona" action="show" id="${personaInstance?.zona?.id}">${personaInstance?.zona?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:personaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${personaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
