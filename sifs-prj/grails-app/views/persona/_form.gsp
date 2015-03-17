<%@ page import="ar.org.scouts.sifs.PersonaController"%>
<%@ page import="ar.org.scouts.sifs.Persona"%>
<%@ page import="ar.org.scouts.sifs.Provincia"%>
<%@ page import="ar.org.scouts.sifs.security.Rol"%>
<%@ page import="ar.org.scouts.sifs.Zona"%>
<%@ page import="ar.org.scouts.sifs.Distrito"%>
<%@ page import="ar.org.scouts.sifs.Grupo"%>



<g:javascript library='jquery'/>

<g:javascript src="application.js" />



<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'documentoNumero', 'error')} required">
	<label for="documentoNumero">
		<g:message code="persona.documentoNumero.label" default="Documento Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="documentoNumero" required="" value="${personaInstance?.documentoNumero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="persona.nombre.label" default="Nombre" />
	</label>
	<g:textField name="nombre" value="${personaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="persona.apellido.label" default="Apellido" />
	</label>
	<g:textField name="apellido" value="${personaInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'roles', 'error')} ">
	<label for="roles">
		<g:message code="persona.roles.label" default="Roles" />
		<span class="required-indicator">*</span>
	</label>
</div>

<g:each var="rol" in="${Rol.list()}" >
	<div class="fieldcontain ">
		<label for="rolRaw[${rol.id}]"></label>
		<g:checkBox name="rolRaw[${rol.id}]" value="${rol.authority}" checked="${personaInstance.hasRol(rol)}"/>${rol.authority}
	</div>
</g:each>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'mail', 'error')} ">
	<label for="mail">
		<g:message code="persona.mail.label" default="Mail" />
	</label>
	<g:textField name="mail" value="${personaInstance?.mail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="persona.direccion.label" default="Direccion" />
		<span class="required-indicator">*</span>
	</label>
<%--	<g:select id="direccion" name="direccion.id" from="${ar.org.scouts.sifs.Direccion.list()}" optionKey="id" required="" value="${personaInstance?.direccion?.id}" class="many-to-one"/>--%>
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

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'zona', 'error')} ">
	<label for="zona">
		<g:message code="persona.zona.label" default="Zona" />
	</label>
	<g:select id="zona" optionKey="id" from="${Zona.list()}" noSelection="${['null':'Select One...']}" name="zona.id" value="${personaInstance?.zona?.id}" class="many-to-one" onchange="${remoteFunction(controller:'zona', action:'ajaxGetDistritos', params:'\'id=\' + escape(this.value)', onSuccess:'updateDistrito(data)')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'distrito', 'error')} ">
	<label for="distrito">
		<g:message code="persona.distrito.label" default="Distrito" />
	</label>
	<g:select id="distrito" optionKey="id" from="${Distrito.list()}" noSelection="${['null':'Select One...']}" name="distrito.id" value="${personaInstance?.distrito?.id}" class="many-to-one" onchange="${remoteFunction(controller:'distrito', action:'ajaxGetGrupos', params:'\'id=\' + escape(this.value)', onSuccess:'updateGrupo(data)')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'grupo', 'error')} ">
	<label for="grupo">
		<g:message code="persona.grupo.label" default="Grupo" />
	</label>
	<g:select id="grupo" optionKey="id" from="${Grupo.list()}" noSelection="${['null':'Select One...']}" name="grupo.id" value="${personaInstance?.grupo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'superior', 'error')} ">
	<label for="superior">
		<g:message code="persona.superior.label" default="Superior" />
	</label>
	<g:select id="superior" name="superior.id" from="${PersonaController.superiores()}" optionKey="id" value="${personaInstance?.superior?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="persona.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${personaInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="persona.enabled.label" default="Enabled" />
	</label>
	<g:checkBox name="enabled" value="${personaInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="persona.accountLocked.label" default="Account Locked" />
	</label>
	<g:checkBox name="accountLocked" value="${personaInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="persona.accountExpired.label" default="Account Expired" />
	</label>
	<g:checkBox name="accountExpired" value="${personaInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="persona.passwordExpired.label" default="Password Expired" />
	</label>
	<g:checkBox name="passwordExpired" value="${personaInstance?.passwordExpired}" />
</div>

<script>


	function updateDistrito(distritos) {
		// The response comes back as a bunch-o-JSON
<%--		alert('updateDistrito'); --%>
		
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
<%--		alert('updateGrupo');--%>
		
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
