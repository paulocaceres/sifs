
<%@ page import="ar.org.scouts.sifs.Dictado" %>
<%@ page import="ar.org.scouts.sifs.Inscripto" %>
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
			<h1>${fieldValue(bean: dictadoInstance, field: "nombre")} - ${fieldValue(bean: dictadoInstance, field: "curso.nombre")}</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:form url="[controller:'dictado', action:'aprobar', resource:dictadoInstance]" >
				<fieldset class="form">
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="documento" title="${message(code: 'dictado.nombre.label', default: 'Documento')}" />
						
						<th>${message(code: 'dictado.curso.label', default: 'Nombre')}</th>
					
						<th>${message(code: 'dictado.fecha.label', default: 'Apellido')}</th>
					
						<g:sortableColumn property="nota" title="${message(code: 'dictado.formador.label', default: 'Nota')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dictadoInstance.inscriptos?}" status="i" var="inscriptoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${inscriptoInstance.documento}</td>
				
						<td>${inscriptoInstance.nombre}</td>
					
						<td>${inscriptoInstance.apellido}</td>
					
						<td>
							<g:select id="nota.id" name="nota_${inscriptoInstance.personaId}" from="${ar.org.scouts.sifs.Calificacion.list()}" 
									  optionKey="id" required="" 
									  noSelection="${['null':'Sin calificar...']}"
									  value="${inscriptoInstance?.nota?.id}" 
									  class="many-to-one"/>
						</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</fieldset>
			<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.aprobacionCurso.label', default: 'Guardar')}" />
					<g:link controller="aprobacionCurso" action="index" class="save">Cancelar</g:link>
			</fieldset>
			</g:form>
			<div class="pagination">
				<g:paginate total="${dictadoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

