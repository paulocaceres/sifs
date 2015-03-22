
<%@ page import="ar.org.scouts.sifs.Dictado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dictado.label', default: 'Dictado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dictado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dictado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="dictado.zona.label" default="Zona" /></th>
					
						<g:sortableColumn property="fecha" title="${message(code: 'dictado.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="cupo" title="${message(code: 'dictado.cupo.label', default: 'Cupo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dictadoInstanceList}" status="i" var="dictadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${dictadoInstance.id}">${fieldValue(bean: dictadoInstance, field: "zona")}</g:link></td>
					
						<td><g:formatDate date="${dictadoInstance.fecha}" /></td>
					
						<td>${fieldValue(bean: dictadoInstance, field: "cupo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dictadoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
