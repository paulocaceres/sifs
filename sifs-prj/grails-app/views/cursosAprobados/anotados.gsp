
<%@ page import="ar.org.scouts.sifs.Dictado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dictado.label', default: 'Dictado')}" />
		<g:set var="validationMsg" value="${message(code: 'desinscribir.validacion.mensaje', default: 'Seleccione un dictado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		
		<script type="text/javascript">
			function setAllSelected(selectAllCheckBx) {
				checkboxes = document.getElementsByName('dictadoCheckBox');
				for(var i=0, n=checkboxes.length; i<n; i++) {
				    checkboxes[i].checked = selectAllCheckBx.checked;
			    }
			}

			function validarSeleccion(msg) {
				checkboxes = document.getElementsByName('dictadoCheckBox');
				if(checkboxes != null) {
					for(var i=0, n=checkboxes.length; i<n; i++) {
					    if (checkboxes[i].checked) {
						    return true;
						}
				    }
				    alert(msg);
				    return false;
				} else {
					return false;
				}
			}
		</script>
	</head>
	<body>
		<a href="#list-dictado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-dictado" class="content scaffold-list" role="main">
			<h1>Mis Dictados Anotados</h1>
			<g:if test="${messageNotSelected}">
				<div class="message" role="status">${messageNotSelected}</div>
			</g:if>
			
			<g:form url="[controller:'cursosAprobados', action:'desinscribir']" 
					onsubmit="return validarSeleccion('${validationMsg}')">
			<fieldset class="form">
			<table>
			<thead>
					<tr>
						<th>
							<g:checkBox name="selectAll" id="selectAllId" onclick="setAllSelected(this)"/>
						</th>
						
						<g:sortableColumn property="nombre" title="${message(code: 'dictado.nombre.label', default: 'Nombre Dictado')}" />
					
						<g:sortableColumn property="curso" title="${message(code: 'dictado.curso.label', default: 'Curso')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'dictado.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="zona" title="${message(code: 'dictado.zona.label', default: 'Zona')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dictadoInstanceList?}" status="i" var="dictadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
					    <td><g:checkBox name="dictadoCheckBox" value="${dictadoInstance.id}" checked="false"/>
					    
						<td>${fieldValue(bean: dictadoInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: dictadoInstance, field: "curso")}</td>
					
						<td><g:formatDate date="${dictadoInstance.fecha}" format="dd-MM-yyyy"/></td>
					
						<td>${fieldValue(bean: dictadoInstance, field: "zona.nombre")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</fieldset>
			<fieldset class="buttons">
				<g:if test="${message == null}">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.aprobacionCurso.label', default: 'Desinscribir')}" />
				</g:if>	
			</fieldset>
			</g:form>
			<div class="pagination">
				<g:paginate total="${dictadoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
