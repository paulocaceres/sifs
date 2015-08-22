<%@ page import="ar.org.scouts.sifs.Persona" %>



<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
			<g:set var="entityName" value="${message(code: 'persona.label', default: 'Persona')}" />
			<title>
				<g:message code="default.list.label" args="[entityName]" />
			</title>
	</head>
	<body>
		<a href="#list-persona" class="skip" tabindex="-1">
			<g:message code="default.link.skip.label" default="Skip to content&hellip;" />
		</a>
		<div class="nav" role="navigation">
			<ul>
				<li>
					<a class="home" href="${createLink(uri: '/')}">
						<g:message code="default.home.label" />
					</a>
				</li>
				<li>
					<g:link class="create" action="create">
						<g:message code="default.new.label" args="[entityName]" />
					</g:link>
				</li>
				<li> 
					<g:link class="create" action="upload">
						Cargar Archivo
					</g:link>
				</li>
			</ul>
		</div>
		<div id="list-persona" class="content scaffold-list" role="main">
			<h1>
				<g:message code="default.list.label" args="[entityName]" />
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="documentoNumero" title="${message(code: 'persona.documentoNumero.label', default: 'Documento Numero')}" />

						<g:sortableColumn property="nombre" title="${message(code: 'persona.nombre.label', default: 'Nombre')}" />

						<g:sortableColumn property="apellido" title="${message(code: 'persona.apellido.label', default: 'Apellido')}" />

						<g:sortableColumn property="mail" title="${message(code: 'persona.mail.label', default: 'Mail')}" />

						<th>
							<g:message code="persona.direccion.label" default="Direccion" />
						</th>

						<g:sortableColumn property="telefono" title="${message(code: 'persona.telefono.label', default: 'Telefono')}" />

					</tr>
				</thead>
				<tbody>
					<g:each in="${personaInstanceList}" status="i" var="personaInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

							<td>
								<g:link action="show" id="${personaInstance.id}">${fieldValue(bean: personaInstance, field: "documentoNumero")}</g:link>
							</td>

							<td>
								${fieldValue(bean: personaInstance, field: "nombre")}
							</td>

							<td>
								${fieldValue(bean: personaInstance, field: "apellido")}
							</td>

							<td>
								${fieldValue(bean: personaInstance, field: "mail")}
							</td>

							<td>
								${fieldValue(bean: personaInstance, field: "direccion")}
							</td>

							<td>
								${fieldValue(bean: personaInstance, field: "telefono")}
							</td>

						</tr>
					</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${personaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
