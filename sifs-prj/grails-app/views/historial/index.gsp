
<%@ page import="com.sifs.prj.Historial" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'historial.label', default: 'Historial')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-historial" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-historial" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="calificacion" title="${message(code: 'historial.calificacion.label', default: 'Calificacion')}" />
					
						<th><g:message code="historial.curso.label" default="Curso" /></th>
					
						<g:sortableColumn property="fechaAprobacion" title="${message(code: 'historial.fechaAprobacion.label', default: 'Fecha Aprobacion')}" />
					
						<th><g:message code="historial.persona.label" default="Persona" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${historialInstanceList}" status="i" var="historialInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${historialInstance.id}">${fieldValue(bean: historialInstance, field: "calificacion")}</g:link></td>
					
						<td>${fieldValue(bean: historialInstance, field: "curso")}</td>
					
						<td><g:formatDate date="${historialInstance.fechaAprobacion}" /></td>
					
						<td>${fieldValue(bean: historialInstance, field: "persona")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${historialInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
