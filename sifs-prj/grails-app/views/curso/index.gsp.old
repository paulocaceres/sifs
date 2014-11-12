
<%@ page import="ar.org.scouts.sifs.Curso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-curso" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="curso.contenido.label" default="Contenido" /></th>
					
						<g:sortableColumn property="correlativas" title="${message(code: 'curso.correlativas.label', default: 'Correlativas')}" />
					
						<g:sortableColumn property="cupo" title="${message(code: 'curso.cupo.label', default: 'Cupo')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'curso.fecha.label', default: 'Fecha')}" />
					
						<th><g:message code="curso.inscripto.label" default="Inscripto" /></th>
					
						<th><g:message code="curso.nivel.label" default="Nivel" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cursoInstanceList}" status="i" var="cursoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cursoInstance.id}">${fieldValue(bean: cursoInstance, field: "contenido")}</g:link></td>
					
						<td>${fieldValue(bean: cursoInstance, field: "correlativas")}</td>
					
						<td>${fieldValue(bean: cursoInstance, field: "cupo")}</td>
					
						<td><g:formatDate date="${cursoInstance.fecha}" /></td>
					
						<td>${fieldValue(bean: cursoInstance, field: "inscripto")}</td>
					
						<td>${fieldValue(bean: cursoInstance, field: "nivel")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cursoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
