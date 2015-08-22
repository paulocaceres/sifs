
<%@ page import="ar.org.scouts.sifs.Curso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Asignacion/Modificacion de cupos</title>
	</head>
	<body>
		<a href="#list-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-curso" class="content scaffold-list" role="main">
			<h1>Cursos disponibles:</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'curso.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'curso.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="nivel.nombre" title="${message(code: 'curso.cupo.label', default: 'Cupo')}" />
						
						<th>Asignar/Modificar</th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cursoInstanceList}" status="i" var="cursoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: cursoInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: cursoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: cursoInstance, field: "cupo")}</td>
						
						<td><g:link action="asignar" id="${cursoInstance.id}">
								<g:if test="${cursoInstance.cupo != null && cursoInstance.cupo != 0}">
									${message(code: 'curso.modificarCupo.label', default: 'Modificar')}
								</g:if>
								<g:if test="${cursoInstance.cupo == null || cursoInstance.cupo == 0}">
									${message(code: 'curso.modificarCupo.label', default: 'Asignar')}
								</g:if>								
						    </g:link>
						</td>				
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
