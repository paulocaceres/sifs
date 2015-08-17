<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	    <g:set var="entityName" value="${entityName}" />
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
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${message}">
    			<div class="message" role="status">${message}</div>
			</g:if>
		</div>
	</body>
</html>
