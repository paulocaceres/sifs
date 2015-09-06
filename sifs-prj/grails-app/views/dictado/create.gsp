<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dictado.label', default: 'Dictado')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui.css')}" type="text/css">
		<script src="/sifs-prj/static/js/jquery-ui.js" type="text/javascript" ></script>
		<script src="/sifs-prj/static/js/datepicker-es.js" type="text/javascript" ></script>
	    <script>
	  		$(function() {
	    		$( "#tabs" ).tabs();
	  		});
	  		$(document).ready(function()
	  		        {
	  		          $("#datepicker").datepicker({defaultDate: +2,minDate: +2, dateFormat: 'dd/mm/yy'});
	  		          $("#datepicker").datepicker($.datepicker.regional["es"]);
	  		          $("div.ui-datepicker").css({fontSize: '13px'});
	  		        })
	  	</script>	
	</head>
	<body>
		<a href="#create-dictado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-dictado" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${dictadoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${dictadoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:dictadoInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
