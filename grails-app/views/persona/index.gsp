<%@ page import="ar.org.scouts.sifs.Persona" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
			<g:set var="entityName" value="${message(code: 'persona.label', default: 'Persona')}" />
			<title>
				<g:message code="default.list.label" args="[entityName]" />
			</title>
			
			<r:require modules="easygrid-jqgrid-dev,export"/>
			<script></script>
		
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
					<g:link class="plus" controller="persona" action="upload">
						<g:message code="Cargar Archivo" default="Cargar Archivo" />
					</g:link>
				</li>
				
			</ul>
		</div>
		<div id="list-persona" class="content scaffold-list" role="main" >
<%--			<h1>--%>
<%--				<g:message code="default.list.label" args="[entityName]" />--%>
<%--			</h1>--%>
			<br/>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		
			<grid:grid name='personas'>
				<grid:set caption="Lista de Personas" width="1261"/>
	    		<grid:set col="documentoNumero" formatter="f:customShowFormat" width="128px"/>
	    		<grid:set col="nombre"  width='148px'/>
	    		<grid:set col="apellido"  width='145px'/>
	    		<grid:set col="direccion"  width='360px'/>
	    		<grid:set col="mail"  width='240px'/>
	    		<grid:set col="telefono"  width='240px'/>
			</grid:grid>
			<grid:exportButton name="personas" formats="['excel', 'pdf']"/>
		
		</div>
		
	</body>
	
	<r:script>
			
		function customShowFormat(cellvalue, options, rowObject) {
            return "<a href='${g.createLink(controller: "persona", action: "show")}/" + cellvalue + "'>" + cellvalue + "</a> ";
        }
        
        $(document).ready(function() { 
        	$("#gview_personas_table").css("font-size", "14px");
        	$(".ui-jqgrid-titlebar").css("font-size", "16px");
        	$(".ui-widget-header").css("background", "#99CC00")
        	$("#gs_nombre").css("font-size", "14px");
        	$("#gs_nombre").css("width", "120px");
        	$("#gs_nombre").css("height", "20px");
        	$("#gs_apellido").css("font-size", "14px");
        	$("#gs_apellido").css("width", "120px");
        	$("#gs_apellido").css("height", "20px");
        	$("#gs_documentoNumero").css("font-size", "14px");
        	$("#gs_documentoNumero").css("width", "95px");
        	$("#gs_documentoNumero").css("height", "20px");
        	$("#gs_mail").css("font-size", "14px");
        	$("#gs_mail").css("height", "20px");
        	$("#gs_direccion").css("font-size", "14px");
        	$("#gs_direccion").css("height", "20px");
        	$("#gs_telefono").css("font-size", "14px");
        	$("#gs_telefono").css("height", "20px");
        	$(".ui-search-oper").css("display", "none");
        })
        
	</r:script>

</html>
