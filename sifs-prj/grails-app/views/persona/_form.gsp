<%@ page import="ar.org.scouts.sifs.Persona" %>



<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'documentoNumero', 'error')} required">
	<label for="documentoNumero">
		<g:message code="persona.documentoNumero.label" default="Documento Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="documentoNumero" required="" value="${personaInstance?.documentoNumero}" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="persona.nombre.label" default="Nombre" />
	</label>
	<g:textField name="nombre" value="${personaInstance?.nombre}" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="persona.apellido.label" default="Apellido" />
	</label>
	<g:textField name="apellido" value="${personaInstance?.apellido}" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'roles', 'error')} ">
	<label for="roles">
		<g:message code="persona.roles.label" default="Roles" />
		<span class="required-indicator">*</span>
	</label>
</div>
<g:each var="rol" in="${ar.org.scouts.sifs.security.Rol.list()}" >
	<div class="fieldcontain ">
		<label for="rolRaw[${rol.id}]"></label>
		<g:checkBox name="rolRaw[${rol.id}]" value="${rol.authority}" checked="${personaInstance.hasRol(rol)}"/>${rol.authority}
	</div>
</g:each>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'mail', 'error')} ">
	<label for="mail">
		<g:message code="persona.mail.label" default="Mail" />

	</label>
	<g:field type="email" name="mail" value="${personaInstance?.mail}" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="persona.telefono.label" default="Telefono" />
	</label>
	<g:textField name="telefono" value="${personaInstance?.telefono}" />
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
	<g:select optionKey="id" from="${ar.org.scouts.sifs.Provincia.list()}" name="direccion.provincia.id" value="${personaInstance?.direccion?.provincia?.id}" style="width: 208px" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'zona', 'error')} ">
	<label for="zona">
		<g:message code="persona.zona.label" default="Zona" />
	</label>
	<g:select id="zona" optionKey="id" from="${ar.org.scouts.sifs.Zona.list()}" noSelection="['-1': '']" name="zona.id" value="${personaInstance?.zona?.id}" class="many-to-one" style="width: 208px" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'distrito', 'error')} ">
	<label for="distrito">
		<g:message code="persona.distrito.label" default="Distrito" />
	</label>
	<g:select id="distrito" optionKey="id" from="${ar.org.scouts.sifs.Distrito.list()}" noSelection="['null': '']" name="distrito.id" value="${personaInstance?.distrito?.id}" class="many-to-one" style="width: 208px" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'grupo', 'error')} ">
	<label for="grupo">
		<g:message code="persona.grupo.label" default="Grupo" />
	</label>
	<g:select id="grupo" optionKey="id" from="${ar.org.scouts.sifs.Grupo.list()}" noSelection="['null': '']" name="grupo.id" value="${personaInstance?.grupo?.id}" class="many-to-one" style="width: 208px" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'supervisor', 'error')} ">
	<label for="supervisor">
		<g:message code="persona.supervisor.label" default="Supervisor" />
	</label>
	<g:select id="supervisor" optionKey="id" from="${ar.org.scouts.sifs.Persona.list()}" noSelection="['null': '']" name="supervisor.id" value="${personaInstance?.supervisor?.id}" class="many-to-one" style="width: 208px" />
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="persona.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${personaInstance?.password}" />

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

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'dictadosAprobados', 'error')} ">
	<label for="dictadosAprobados">
		<g:message code="persona.dictadosAprobados.label" default="Dictados Aprobados" />
	</label>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'dictadosAnotados', 'error')} ">
	<label for="dictadosAnotados">
		<g:message code="persona.dictadosAnotados.label" default="Dictados Anotados" />
	</label>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="persona.passwordExpired.label" default="Password Expired" />
	</label>
	<g:checkBox name="passwordExpired" value="${personaInstance?.passwordExpired}" />
</div>

<script>


	$(document).ready(function() {


		$("#zona").change(function() {
			$.ajax({
				url: "ajaxZonaSelected",
				data: "id=" + this.value,
				cache: false,
				success: function(html) {
					var options = null;

					options = ' ';
					options += '<option value="-1"> </option>';
					for (var i = 0; i < html.distritos.length; i++) {
						options += '<option value="' + html.distritos[i].id + '">' + html.distritos[i].nombre + '</option>';
					}                    
					$('#distrito').html(options);
					
					options = ' ';
					options += '<option value="-1"> </option>';
					for (var i = 0; i < html.grupos.length; i++) {
						options += '<option value="' + html.grupos[i].id + '">' + html.grupos[i].nombre + '</option>';
					}                    
					$('#grupo').html(options);
					
					options = ' ';
					options += '<option value="-1"> </option>';
					for (var i = 0; i < html.supervisores.length; i++) {
						options += '<option value="' + html.supervisores[i].id + '">' + html.supervisores[i].apellido + ', ' + html.supervisores[i].nombre + '</option>';
					}                    
					$('#supervisor').html(options);
				}
			});
		});


		$("#distrito").change(function() {
			$.ajax({
				url: "ajaxDistritoSelected",
				data: "id=" + this.value,
				cache: false,
				success: function(html) {
					var options = null;

					$('#zona').val(html.zona);
					
					options = ' ';
					options += '<option value="-1"> </option>';
					for (var i = 0; i < html.grupos.length; i++) {
						options += '<option value="' + html.grupos[i].id + '">' + html.grupos[i].nombre + '</option>';
					}                    
					$('#grupo').html(options);
					
					options = ' ';
					options += '<option value="-1"> </option>';
					for (var i = 0; i < html.supervisores.length; i++) {
						options += '<option value="' + html.supervisores[i].id + '">' + html.supervisores[i].apellido + ', ' + html.supervisores[i].nombre + '</option>';
					}                    
					$('#supervisor').html(options);
				}
			});
		});


	});
	

</script>
