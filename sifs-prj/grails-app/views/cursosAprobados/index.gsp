
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
					
						<g:sortableColumn property="curso" title="${message(code: 'dictado.curso.label', default: 'Curso')}" />
						
						<g:sortableColumn property="nombre" title="${message(code: 'dictado.nombre.label', default: 'Nombre Dictado')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'dictado.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="formador" title="${message(code: 'dictado.formador.label', default: 'Formador')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dictadoInstanceList?}" status="i" var="dictadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: dictadoInstance, field: "curso")}</td>
					
						<td>${fieldValue(bean: dictadoInstance, field: "nombre")}</td>
					
						<td><g:formatDate date="${dictadoInstance.fecha}" format="dd-MM-yyyy"/></td>
					
						<td>${fieldValue(bean: dictadoInstance, field: "formador")}</td>
					
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
