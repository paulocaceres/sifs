<%@ page import="ar.org.scouts.sifs.Grupo" %>



<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
			<g:set var="entityName" value="${message(code: 'grupo.label', default: 'Grupo')}" />
			<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-grupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;" /></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" /></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-grupo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list grupo">

				<g:if test="${grupoInstance?.numero}">
					<li class="fieldcontain">
						<span id="numero-label" class="property-label"><g:message code="grupo.numero.label" default="Número" /></span>
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${grupoInstance}" field="numero" /></span>
					</li>
				</g:if>

				<g:if test="${grupoInstance?.nombre}">
					<li class="fieldcontain">
						<span id="nombre-label" class="property-label"><g:message code="grupo.nombre.label" default="Nombre" /></span>
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${grupoInstance}" field="nombre" /></span>
					</li>
				</g:if>

				<g:if test="${grupoInstance?.supervisores}">
					<li class="fieldcontain">
						<span id="supervisores-label" class="property-label">
							<g:message code="grupo.supervisores.label" default="Supervisores" />
						</span>
						<g:each in="${grupoInstance.supervisores}" var="s">
							<span class="property-value" aria-labelledby="supervisores-label">
								<g:link controller="persona" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link>
							</span>
						</g:each>
					</li>
				</g:if>

			</ol>
			<g:form url="[resource:grupoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${grupoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
