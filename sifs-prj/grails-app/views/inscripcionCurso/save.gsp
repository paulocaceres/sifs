
<%@ page import="ar.org.scouts.sifs.Curso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Inscripcion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="index"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-curso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${customMessage}">
			<div class="message" role="status">${customMessage}</div>
			</g:if>
			<ol class="property-list curso">
				
				<g:if test="${cursosInstanceList}">
				<li class="fieldcontain">
					<span id="correlativas-label" class="property-label">
						<g:message code="curso.correlativas.label" default="Dictados Seleccionados" />
					</span>
					
					<g:each var="c" in="${cursosInstanceList.sort { it.nombre } }">
							<span class="property-value" aria-labelledby="correlativas-label">
							    <g:fieldValue bean="${c}" field="nombre"/>, <g:fieldValue bean="${c}" field="zona.nombre"/> -  <g:fieldValue bean="${c}" field="formador"/>
							</span>
					</g:each>					
				</li>
				</g:if>
	
			</ol>
			<g:form url="[resource:cursoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" controller="cursosAprobados" action="anotados"><g:message code="default.button.cursos.anotados.label" default="Cursos Anotados" /></g:link>
					<g:link class="edit" action="index"><g:message code="default.button.continuar.inscripcion.label" default="Continuar Inscripcion" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
