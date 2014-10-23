<%@ page import="ar.org.scouts.sifs.Persona"%>
<%@ page import="ar.org.scouts.sifs.Provincia"%>
<%@ page import="ar.org.scouts.sifs.security.Rol"%>
<%@ page import="ar.org.scouts.sifs.Zona"%>
<%@ page import="ar.org.scouts.sifs.Distrito"%>
<%@ page import="ar.org.scouts.sifs.Grupo"%>



<g:javascript library='jquery'/>

<g:javascript src="application.js" />



<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'documentoNumero', 'error')} ">
	<label for="documentoNumero">
		<g:message code="persona.documentoNumero.label" default="Documento Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="documentoNumero" value="${personaInstance?.documentoNumero}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="persona.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" value="${personaInstance?.nombre}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="persona.apellido.label" default="Apellido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apellido" value="${personaInstance?.apellido}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'roles', 'error')} ">
	<label for="roles">
		<g:message code="persona.roles.label" default="Roles" />
		<span class="required-indicator">*</span>
	</label>
	<g:each var="rol" in="${Rol.list()}" >
<%--		<g:checkBox name="rolRaw[${rol.id}]" value="${rol.authority}" checked="${personaInstance?.authorities.contains(rol)}"/>${rol.authority}--%>
		<g:checkBox name="rolRaw[${rol.id}]" value="${rol.authority}" checked="false"/>${rol.authority}
	</g:each> 
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'mail', 'error')} ">
	<label for="mail">
		<g:message code="persona.mail.label" default="Mail" />
	</label>
	<g:textField name="mail" value="${personaInstance?.mail}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="persona.direccion.label" default="Direccion" />
	</label>
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="persona.direccion.calle.label" default="Calle" />
	</label>
	<g:textField name="direccion.calle" value="${personaInstance?.direccion?.calle}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="persona.direccion.numero.label" default="Numero" />
	</label>
	<g:textField name="direccion.numero" value="${personaInstance?.direccion?.numero}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="persona.direccion.adicional.label" default="Adicional" />
	</label>
	<g:textField name="direccion.adicional" value="${personaInstance?.direccion?.adicional}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="persona.direccion.codigoPostal.label" default="Codigo Postal" />
	</label>
	<g:textField name="direccion.codigoPostal" value="${personaInstance?.direccion?.codigoPostal}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="persona.direccion.ciudad.label" default="Ciudad" />
	</label>
	<g:textField name="direccion.ciudad" value="${personaInstance?.direccion?.ciudad}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="persona.direccion.provincia.label" default="Provincia" />
	</label>
	<g:select optionKey="id" from="${Provincia.list()}" name="direccion.provincia.id" value="${personaInstance?.direccion?.provincia?.id}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'zona', 'error')} required">
	<label for="zona">
		<g:message code="persona.zona.label" default="Zona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select optionKey="id" from="${Zona.list()}" noSelection="${['null':'Select One...']}" name="zona.id" value="${personaInstance?.zona?.id}" onchange="${remoteFunction(controller:'zona', action:'ajaxGetDistritos', params:'\'id=\' + escape(this.value)', onSuccess:'updateDistrito(data)')}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'distrito', 'error')} required">
	<label for="distrito">
		<g:message code="persona.zona.distrito.label" default="Distrito" />
		<span class="required-indicator">*</span>
	</label>
	<g:select optionKey="id" from="${Distrito.list()}" noSelection="${['null':'Select One...']}" name="distrito.id" value="${personaInstance?.distrito?.id}" onchange="${remoteFunction(controller:'distrito', action:'ajaxGetGrupos', params:'\'id=\' + escape(this.value)', onSuccess:'updateGrupo(data)')}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'grupo', 'error')} required">
	<label for="grupo">
		<g:message code="persona.zona.grupo.label" default="Grupo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select optionKey="id" from="${Grupo.list()}" noSelection="${['null':'Select One...']}" name="grupo.id" value="${personaInstance?.grupo?.id}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'superior', 'error')} required">
	<label for="superior">
		<g:message code="persona.superior.label" default="Superior" />
	</label>
	<g:textField name="superior" value="${personaInstance?.superior}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'bloqueado', 'error')} ">
	<label for="bloqueado">
		<g:message code="persona.bloqueado.label" default="Bloqueado" />
	</label>
	<g:checkBox name="bloqueado" value="${personaInstance?.accountLocked}" />
</div>
<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="persona.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" value="${personaInstance?.password}" />
</div>



<script>


	function updateDistrito(distritos) {
		// The response comes back as a bunch-o-JSON
		alert('updateDistrito'); 
		
		if (distritos) {
			var rselect = document.getElementById('distrito.id')
		
			// Clear all previous options
			var l = rselect.length
			while (l > 0) {
				l--
				rselect.remove(l)
			}
		
			// Rebuild the select
			for (var i=0; i < distritos.length; i++) {
				var distrito = distritos[i]
				var opt = document.createElement('option');
				opt.text = distrito.nombre
				opt.value = distrito.id
				try {
					rselect.add(opt, null) // standards compliant; doesn't work in IE
				} catch(ex) {
					rselect.add(opt) // IE only
				}
			}
		}
	}
	
	
	function updateGrupo(grupos) {
		// The response comes back as a bunch-o-JSON
		alert('updateGrupo');
		
		if (grupos) {
			var rselect = document.getElementById('grupo.id')
		
			// Clear all previous options
			var l = rselect.length
			while (l > 0) {
				l--
				rselect.remove(l)
			}
		
			// Rebuild the select
			for (var i=0; i < grupos.length; i++) {
				var grupo = grupos[i]
				var opt = document.createElement('option');
				opt.text = grupo.nombre
				opt.value = grupo.id
				try {
					rselect.add(opt, null) // standards compliant; doesn't work in IE
				} catch(ex) {
					rselect.add(opt) // IE only
				}
			}
		}
	}
	
	
	// This is called when the page loads to initialize distrito
<%--	var zselect = document.getElementById('zona.id');--%>
<%--	var zopt = zselect.options[zselect.selectedIndex];--%>
<%--	<g:remoteFunction controller="zona" action="ajaxGetDistritos" params="'id='+zopt.value" onSuccess="updateDistrito(data)" />--%>

	// This is called when the page loads to initialize grupo
<%--	zselect = document.getElementById('distrito.id');--%>
<%--	zopt = zselect.options[zselect.selectedIndex];--%>
<%--	<g:remoteFunction controller="distrito" action="ajaxGetGrupos" params="'id='+zopt.value" onSuccess="updateGrupo(data)" />--%>

<%--	document.getElementById('zona.id').selectedIndex = -1;--%>
<%--	document.getElementById('distrito.id').selectedIndex = -1;--%>
<%--	document.getElementById('grupo.id').selectedIndex = -1;--%>


</script>
