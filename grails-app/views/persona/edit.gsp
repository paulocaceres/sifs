<%@ page import="ar.org.scouts.sifs.Persona"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'persona.label', default: 'Persona')}" />
		<g:set var="isEditing" value="true" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui.css')}" type="text/css">
		<script src="/sifs-prj/static/js/jquery-ui.js" type="text/javascript" ></script>
	    <script>
	  		$(function() {
	    		$( "#tabs" ).tabs();
	  		});
	  	</script>	
	</head>
	<body>
		<a href="#edit-persona" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;" /></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" /></a></li>
				<sec:ifAnyGranted roles="ROLE_ADMIN">
					<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</sec:ifAnyGranted>
			</ul>
		</div>
		<div id="edit-persona" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${personaInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${personaInstance}" var="error">
						<li><g:if test="${error in org.springframework.validation.FieldError}"></g:if>
							<g:message error="${error}" />
						</li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form url="[resource:personaInstance, action:'update']" method="PUT">
				<g:hiddenField name="version" value="${personaInstance?.version}" />
				<fieldset class="form">
					<g:render template="form" />
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>