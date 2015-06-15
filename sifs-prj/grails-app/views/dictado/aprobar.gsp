
<%@ page import="ar.org.scouts.sifs.Dictado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Aprobacion de Dictados')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="show-curso" class="content scaffold-show" role="main">
			<h1>${entityName}</h1>
			<g:if test="${sucessMessage}">
				<div class="message" role="status">${sucessMessage}</div>
			</g:if>
			<g:form url="[resource:dictadoInstance, action:'cerrar']">
				<fieldset class="buttons">
					<g:submitButton name="cerrar" class="save" value="${message(code: 'default.button.aprobacionDictado.label', default: 'Cerrar Dictado')}" />
					<g:link controller="aprobacionCurso" action="index" class="save">Continuar</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
