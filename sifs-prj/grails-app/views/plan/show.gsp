
<%@ page import="ar.org.scouts.sifs.Plan" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-plan" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-plan" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list plan">
			
			   <g:if test="${planInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="plan.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${planInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${planInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="plan.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${planInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
						
				<g:if test="${planInstance?.validez}">
				<li class="fieldcontain">
					<span id="validez-label" class="property-label"><g:message code="plan.validez.label" default="Validez" /></span>			
						<span class="property-value" aria-labelledby="validez-label">
							<g:formatDate date="${planInstance?.validez}"  format="dd-MM-yyyy" />
						</span>
					
				</li>
				</g:if>
				
				<g:if test="${planInstance?.cursos}">
				<li class="fieldcontain">
					<span id="cursos-label" class="property-label">
						<g:message code="curso.correlativas.label" default="Cursos" />
					</span>
					
					<g:each var="c" in="${planInstance?.cursos?.sort { it.nombre } }">
							<span class="property-value" aria-labelledby="cursos-label">
								<g:link controller="curso" action="show" id="${c.id}">${c.nombre}</g:link>
							</span>
					</g:each>					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:planInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${planInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
