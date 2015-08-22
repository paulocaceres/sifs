
<%@ page import="ar.org.scouts.sifs.PlanCurso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'planCurso.label', default: 'PlanCurso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-planCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li>
					<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
				</li>
				<li>
					<g:link class="create" action="create" params="${[planID: planInstance.id]}">
						<g:message code="default.add.label" args="[entityName]" />
					</g:link>
				</li>
			</ul>
		</div>
		<div id="list-planCurso" class="content scaffold-list" role="main">
			<h1>
				<g:message code="default.list.label" args="[entityName]" />: ${planInstance.nombre}
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="planCurso.curso.label" default="Cursos" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${planCursoInstanceList}" status="i" var="planCursoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>
							<g:link action="show" id="${planCursoInstance.id}">${fieldValue(bean: planCursoInstance, field: "curso")}</g:link>
						</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${planCursoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
