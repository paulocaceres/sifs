
<%@ page import="ar.org.scouts.sifs.Recurso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recurso.label', default: 'Recurso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-recurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-recurso" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="cantidad" title="${message(code: 'recurso.cantidad.label', default: 'Cantidad')}" />
					
						<th><g:message code="recurso.direccion.label" default="Direccion" /></th>
					
						<g:sortableColumn property="nombre" title="${message(code: 'recurso.nombre.label', default: 'Nombre')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${recursoInstanceList}" status="i" var="recursoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${recursoInstance.id}">${fieldValue(bean: recursoInstance, field: "cantidad")}</g:link></td>
					
						<td>${fieldValue(bean: recursoInstance, field: "direccion")}</td>
					
						<td>${fieldValue(bean: recursoInstance, field: "nombre")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${recursoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
