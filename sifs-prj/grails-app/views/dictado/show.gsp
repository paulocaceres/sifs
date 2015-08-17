
<%@ page import="ar.org.scouts.sifs.Dictado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dictado.label', default: 'Dictado')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-dictado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-dictado" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list dictado">
			
				<g:if test="${dictadoInstance?.curso}">
				<li class="fieldcontain">
					<span id="curso-label" class="property-label"><g:message code="dictado.curso.label" default="Curso" /></span>
					
						<span class="property-value" aria-labelledby="curso-label">${dictadoInstance?.curso?.nombre}</span>
					
				</li>
				</g:if>
							
				<g:if test="${dictadoInstance?.zona}">
				<li class="fieldcontain">
					<span id="zona-label" class="property-label"><g:message code="dictado.zona.label" default="Zona" /></span>
					
						<span class="property-value" aria-labelledby="zona-label">${dictadoInstance?.zona?.nombre}</span>
					
				</li>
				</g:if>
			
				<g:if test="${dictadoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="dictado.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate format="dd-MM-yyyy" date="${dictadoInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${dictadoInstance?.cupo}">
				<li class="fieldcontain">
					<span id="cupo-label" class="property-label"><g:message code="dictado.cupo.label" default="Cupo" /></span>
					
						<span class="property-value" aria-labelledby="cupo-label"><g:fieldValue bean="${dictadoInstance}" field="cupo"/></span>
					
				</li>
				</g:if>
			
				
				<g:if test="${dictadoInstance?.recursos}">
				<li class="fieldcontain">
					<span id="recursos-label" class="property-label"><g:message code="dictado.recursos.label" default="Recursos" /></span>
					
						<g:each in="${dictadoInstance.recursos}" var="r">
						<span class="property-value" aria-labelledby="recursos-label">${r?.nombre}</span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:dictadoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${dictadoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
