
<%@ page import="ar.org.scouts.sifs.Direccion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'direccion.label', default: 'Direccion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-direccion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-direccion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="adicional" title="${message(code: 'direccion.adicional.label', default: 'Adicional')}" />
					
						<g:sortableColumn property="calle" title="${message(code: 'direccion.calle.label', default: 'Calle')}" />
					
						<g:sortableColumn property="ciudad" title="${message(code: 'direccion.ciudad.label', default: 'Ciudad')}" />
					
						<g:sortableColumn property="codigoPostal" title="${message(code: 'direccion.codigoPostal.label', default: 'Codigo Postal')}" />
					
						<g:sortableColumn property="numero" title="${message(code: 'direccion.numero.label', default: 'Numero')}" />
					
						<th><g:message code="direccion.provincia.label" default="Provincia" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${direccionInstanceList}" status="i" var="direccionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${direccionInstance.id}">${fieldValue(bean: direccionInstance, field: "adicional")}</g:link></td>
					
						<td>${fieldValue(bean: direccionInstance, field: "calle")}</td>
					
						<td>${fieldValue(bean: direccionInstance, field: "ciudad")}</td>
					
						<td>${fieldValue(bean: direccionInstance, field: "codigoPostal")}</td>
					
						<td>${fieldValue(bean: direccionInstance, field: "numero")}</td>
					
						<td>${fieldValue(bean: direccionInstance, field: "provincia")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${direccionInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
