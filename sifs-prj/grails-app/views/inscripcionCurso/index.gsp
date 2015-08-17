<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		<style type="text/css">
			.CheckBoxList {
			    height: 160px; overflow: auto; overflow-x: hidden; width: 460px; border: 0px solid #000;
			    list-style-type: none; margin: 0; padding:0px
			}
			.CheckBoxList li  {
			    padding:2px
			}
			input[type=radio] {
			    vertical-align:middle;
			}
			label{
				margin: 5px;
				padding:2px;
			    vertical-align:middle;  
			}
		</style>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui.css')}" type="text/css">
		<script src="/sifs-prj/static/js/jquery-ui.js" type="text/javascript" ></script>
	    <script>
	  		$(function() {
	    		$( "#tabs" ).tabs();
	  		});
	  
		  	$(function() {
		  	    $( "#accordion" ).accordion({
		  	      collapsible: true
		  	    });
		  	});
	  	</script>
	</head>
	<body>
		<a href="#create-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="create-curso" class="content scaffold-create" role="main">
			<h1><g:message code="default.incripcionCurso.label" args="[entityName]" default="Inscripcion a Cursos"/></h1>
			<g:form url="[action:'save']" >
				<fieldset class="form">
					<g:render template="alta"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.inscripcionCurso.label', default: 'Inscribirme')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
		