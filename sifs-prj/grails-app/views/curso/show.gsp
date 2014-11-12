
<%@ page import="ar.org.scouts.sifs.Curso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-curso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list curso">
			
				<g:if test="${cursoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="curso.nombre.label" default="Nombre" /></span>
						<span class="property-value" aria-labelledby="nombre-label">
							<g:fieldValue bean="${cursoInstance}" field="nombre"/>
						</span>
				</li>
				</g:if>
				
				<g:if test="${cursoInstance?.nivel}">
				<li class="fieldcontain">
					<span id="nivel-label" class="property-label"><g:message code="curso.nivel.label" default="Nivel" /></span>
						<span class="property-value" aria-labelledby="nivel-label">
							<g:fieldValue bean="${cursoInstance}" field="nivel.nombre"/>
						</span>
				</li>
				</g:if>
				
				<g:if test="${cursoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="curso.descripcion.label" default="Descripcion" /></span>
						<span class="property-value" aria-labelledby="descripcion-label">
							<g:fieldValue bean="${cursoInstance}" field="descripcion"/>
						</span>
				</li>
				</g:if>
				
				<g:if test="${cursoInstance?.correlativas}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label">
						<g:message code="curso.correlativas.label" default="Correlativas" />
					</span>
					
					<g:each var="c" in="${cursoInstance?.correlativas?.sort { it.nombre } }">
							<span class="property-value" aria-labelledby="correlativas-label">
								<g:link action="show" id="${c.id}">${c.nombre}</g:link>
							</span>
					</g:each>					
				</li>
				</g:if>
	
			</ol>
			<g:form url="[resource:cursoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${cursoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
