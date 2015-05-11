<%@ page import="ar.org.scouts.sifs.Zona" %>


<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
			<g:set var="entityName" value="${message(code: 'zona.label', default: 'Zona')}" />
			<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-zona" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;" /></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" /></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-zona" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list zona">

				<g:if test="${zonaInstance?.numero}">
					<li class="fieldcontain">
						<span id="numero-label" class="property-label"><g:message code="zona.numero.label" default="Numero" /></span>
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${zonaInstance}" field="numero" /></span>
					</li>
				</g:if>

				<g:if test="${zonaInstance?.nombre}">
					<li class="fieldcontain">
						<span id="nombre-label" class="property-label"><g:message code="zona.nombre.label" default="Nombre" /></span>
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${zonaInstance}" field="nombre" /></span>
					</li>
				</g:if>

				<g:if test="${zonaInstance?.distritos}">
					<li class="fieldcontain">
						<span id="distritos-label" class="property-label">
							<g:message code="zona.distritos.label" default="Distritos" />
						</span>
						<g:each in="${zonaInstance.distritos}" var="d">
							<span class="property-value" aria-labelledby="distritos-label">
								<g:link controller="distrito" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link>
							</span>
						</g:each>
					</li>
				</g:if>

			</ol>
			<g:form url="[resource:zonaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${zonaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
