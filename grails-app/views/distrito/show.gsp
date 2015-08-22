<%@ page import="ar.org.scouts.sifs.Distrito" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
			<g:set var="entityName" value="${message(code: 'distrito.label', default: 'Distrito')}" />
			<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-distrito" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;" /></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" /></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-distrito" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list distrito">

				<g:if test="${distritoInstance?.zona}">
					<li class="fieldcontain">
						<span id="zona-label" class="property-label">
							<g:message code="distrito.zona.label" default="Zona" />
						</span>
						<span class="property-value" aria-labelledby="zona-label">
							${distritoInstance?.zona?.nombre}
						</span>
					</li>
				</g:if>

				<g:if test="${distritoInstance?.nombre}">
					<li class="fieldcontain">
						<span id="nombre-label" class="property-label"><g:message code="distrito.nombre.label" default="Nombre" /></span>
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${distritoInstance}" field="nombre" /></span>
					</li>
				</g:if>

			</ol>
			<g:form url="[resource:distritoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${distritoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
