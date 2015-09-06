<%@ page import="ar.org.scouts.sifs.Persona"%>



		<div 
			class="fieldcontain ${hasErrors(bean: personaInstance, field: 'documentoNumero', 'error')} required">
			<label for="documentoNumero"><g:message 
				code="persona.documentoNumero.label" default="Documento Numero" />
				<span class="required-indicator">*</span>
			</label>
			<sec:ifNotGranted roles="ROLE_ADMIN,ROLE_SUPERVISOR">
				<g:textField name="documentoNumero" maxlength="8" size="8" 
					required="" value="${personaInstance?.documentoNumero}" 
					disabled="true" />
			</sec:ifNotGranted>
			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<%-- <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SUPERVISOR"> --%>
				<g:textField name="documentoNumero" maxlength="8" size="8" 
					required="" value="${personaInstance?.documentoNumero}" />(Obligatorio)
			</sec:ifAnyGranted>
<%--		</div>--%>
<%----%>
<%--		<div --%>
<%--		class="fieldcontain ${hasErrors(bean: personaInstance, field: 'nombre', 'error')} ">--%>
			<label for="nombre" style="left: 0px; position: relative; max-width: 100px"><g:message code="persona.nombre.label" 
					default="Nombre" />
			</label>
			<g:textField name="nombre" size="20" 
				value="${personaInstance?.nombre}" />
<%--		</div>--%>
<%----%>
<%--		<div --%>
<%--			class="fieldcontain ${hasErrors(bean: personaInstance, field: 'apellido', 'error')} ">--%>
			<label for="apellido"style="left: 0px; position: relative; max-width: 100px">
			<g:message code="persona.apellido.label" default="Apellido" />
			</label>
			<g:textField name="apellido" size="20" 
				value="${personaInstance?.apellido}" />
		</div>

<div id="tabs" style="max-width: 900px; left: 140px; position: relative">
	<ul>

		<li><a href="#tabs-1">Datos Personales</a>		</li>
		<li><a href="#tabs-2">Direccion</a>		</li>
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SUPERVISOR">
			<li><a href="#tabs-3">Informacion Scout</a></li>
			<li><a href="#tabs-4">Seguridad</a></li>
		</sec:ifAnyGranted>
	</ul>

	<div id="tabs-1" style="font-size: 14px;">

		<div 
			class="fieldcontain ${hasErrors(bean: personaInstance, field: 'mail', 'error')} ">
			<label for="mail"><g:message code="persona.mail.label" 
					default="Mail" />
			</label>
			<g:field type="email" name="mail" size="40" 
				value="${personaInstance?.mail}" />(Obligatorio)
		</div>

		<div 
			class="fieldcontain ${hasErrors(bean: personaInstance, field: 'telefono', 'error')} ">
			<label for="telefono"><g:message 
				code="persona.telefono.label" default="Telefono" />
			</label>
			<g:textField name="telefono" size="15" 
				value="${personaInstance?.telefono}" />
		</div>
	</div>

	<div id="tabs-2" style="font-size: 14px;">
		<div 
			class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
			<label for="direccion"><g:message 
				code="persona.direccion.calle.label" default="Calle" />
			</label>
			<g:textField name="direccion.calle" size="60" 
				value="${personaInstance?.direccion?.calle}" />(Obligatorio)
		</div>
		<div 
			class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
			<label for="direccion"><g:message 
				code="persona.direccion.numero.label" default="Numero" />
			</label>
			<g:textField name="direccion.numero" size="5" 
				value="${personaInstance?.direccion?.numero}" />(Obligatorio)
			<label for="direccion" 
				style="left: 0px; position: relative; max-width: 100px"><g:message 
					code="persona.direccion.adicional.label" default="Adicional" />
			</label>
			<g:textField name="direccion.adicional" size="37" 
				value="${personaInstance?.direccion?.adicional}" />
		</div>
<%--		<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">--%>
<%--			--%>
<%--		</div>--%>
		<div 
			class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
			<label for="direccion"><g:message 
				code="persona.direccion.codigoPostal.label" default="Codigo Postal" />
			</label>
			<g:textField name="direccion.codigoPostal" size="4" 
				value="${personaInstance?.direccion?.codigoPostal}" />(Obligatorio)
		</div>
		<div 
			class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
			<label for="direccion"><g:message 
				code="persona.direccion.ciudad.label" default="Ciudad" />
			</label>
			<g:textField name="direccion.ciudad" size="20" 
				value="${personaInstance?.direccion?.ciudad}" />(Obligatorio)
			<label for="direccion" 
				style="left: 0px; position: relative; max-width: 100px"><g:message 
					code="persona.direccion.provincia.label" default="Provincia" />
			</label>
			<g:select optionKey="id" 
				from="${ar.org.scouts.sifs.Provincia.list()}" 
				noSelection="['null': '']" name="direccion.provincia.id" 
				value="${personaInstance?.direccion?.provincia?.id}" 
				style="width: 208px" />
		</div>
		
<%--		<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">--%>
<%--			<label for="direccion">--%>
<%--				<g:message code="persona.direccion.provincia.label" default="Provincia" />--%>
<%--			</label>--%>
<%--			<g:select optionKey="id" from="${ar.org.scouts.sifs.Provincia.list()}" noSelection="['null': '']" name="direccion.provincia.id" value="${personaInstance?.direccion?.provincia?.id}" style="width: 208px" />--%>
<%--		</div>--%>
		
	</div>

	<div id="tabs-3" style="font-size: 14px;">
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SUPERVISOR">

			<div 
				class="fieldcontain ${hasErrors(bean: personaInstance, field: 'zona', 'error')} ">
				<label for="zona"><g:message code="persona.zona.label" 
					default="Zona" /><span class="required-indicator">*</span>
				</label>
				<g:if 
					test="${(sec.loggedInUserInfo(field:'id').toString() == (personaInstance.id.toString()))}">
					<g:select id="zona" optionKey="id" 
						from="${ar.org.scouts.sifs.Zona.list(sort:'nombre')}" 
						noSelection="['null': '']" name="zona.id" 
						value="${personaInstance?.zona?.id}" class="many-to-one" 
						style="width: 208px" disabled="disabled" />(Obligatorio)
				</g:if>
				<g:else>
					<sec:ifNotGranted roles="ROLE_ADMIN">
						<g:select id="zona" optionKey="id" 
							from="${ar.org.scouts.sifs.Zona.list(sort:'nombre')}" 
							noSelection="['null': '']" name="zona.id" 
							value="${personaInstance?.zona?.id}" class="many-to-one" 
							style="width: 208px" disabled="disabled" />(Obligatorio)
					</sec:ifNotGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN">
						<g:select id="zona" optionKey="id" 
							from="${ar.org.scouts.sifs.Zona.list(sort:'nombre')}" 
							noSelection="['null': '']" name="zona.id" 
							value="${personaInstance?.zona?.id}" class="many-to-one" 
							style="width: 208px" />(Obligatorio)
					</sec:ifAnyGranted>
				</g:else>
				<g:hiddenField name="zonaHdd" />
			</div>

			<div 
				class="fieldcontain ${hasErrors(bean: personaInstance, field: 'distrito', 'error')} ">
				<label for="distrito"><g:message 
					code="persona.distrito.label" default="Distrito" /><span 
						class="required-indicator">*</span>
				</label>
				<g:if 
					test="${(sec.loggedInUserInfo(field:'id').toString() == (personaInstance.id.toString()))}">
					<g:select id="distrito" optionKey="id" 
						from="${ar.org.scouts.sifs.Distrito.list(sort:'nombre')}" 
						noSelection="['null': '']" name="distrito.id" 
						value="${personaInstance?.distrito?.id}" class="many-to-one" 
						style="width: 208px" disabled="disabled" />(Obligatorio)
				</g:if>
				<g:else>
					<sec:ifNotGranted roles="ROLE_ADMIN">
						<g:select id="distrito" optionKey="id" 
							from="${ar.org.scouts.sifs.Distrito.list(sort:'nombre')}" 
							noSelection="['null': '']" name="distrito.id" 
							value="${personaInstance?.distrito?.id}" class="many-to-one" 
							style="width: 208px" disabled="disabled" />(Obligatorio)
					</sec:ifNotGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN">
						<g:select id="distrito" optionKey="id" 
							from="${ar.org.scouts.sifs.Distrito.list(sort:'nombre')}" 
							noSelection="['null': '']" name="distrito.id" 
							value="${personaInstance?.distrito?.id}" class="many-to-one" 
							style="width: 208px" />(Obligatorio)
					</sec:ifAnyGranted>
				</g:else>
				<g:hiddenField name="distritoHdd" />
			</div>

			<div 
				class="fieldcontain ${hasErrors(bean: personaInstance, field: 'grupo', 'error')} ">
				<label for="grupo"><g:message code="persona.grupo.label" 
					default="Grupo" /><span class="required-indicator">*</span>
				</label>
				<g:if 
					test="${(sec.loggedInUserInfo(field:'id').toString() == (personaInstance.id.toString()))}">
					<g:select id="grupo" optionKey="id" 
						from="${ar.org.scouts.sifs.Grupo.list(sort:'nombre')}" 
						noSelection="['null': '']" name="grupo.id" 
						value="${personaInstance?.grupo?.id}" class="many-to-one" 
						style="width: 208px" disabled="disabled" />(Obligatorio)
				</g:if>
				<g:else>
					<sec:ifNotGranted roles="ROLE_ADMIN">
						<g:select id="grupo" optionKey="id" 
							from="${ar.org.scouts.sifs.Grupo.list(sort:'nombre')}" 
							noSelection="['null': '']" name="grupo.id" 
							value="${personaInstance?.grupo?.id}" class="many-to-one" 
							style="width: 208px" disabled="disabled" />(Obligatorio)
					</sec:ifNotGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN">
						<g:select id="grupo" optionKey="id" 
							from="${ar.org.scouts.sifs.Grupo.list(sort:'nombre')}" 
							noSelection="['null': '']" name="grupo.id" 
							value="${personaInstance?.grupo?.id}" class="many-to-one" 
							style="width: 208px" />(Obligatorio)
					</sec:ifAnyGranted>
				</g:else>
				<g:hiddenField name="grupoHdd" />
			</div>

			<div 
				class="fieldcontain ${hasErrors(bean: personaInstance, field: 'supervisor', 'error')} ">
				<label for="supervisor"><g:message 
					code="persona.supervisor.label" default="Supervisor" /><span 
					class="required-indicator">*</span>
				</label>
				<g:if 
					test="${(sec.loggedInUserInfo(field:'id').toString() == (personaInstance.id.toString()))}">
					<g:select id="supervisor" optionKey="id" 
						from="${ar.org.scouts.sifs.security.PersonaRol.findAllByRol(ar.org.scouts.sifs.security.Rol.findByAuthority('ROLE_SUPERVISOR')).persona}" 
						noSelection="['null': '']" name="supervisor.id" 
						value="${personaInstance?.supervisor?.id}" class="many-to-one" 
						style="width: 208px" disabled="disabled" />(Obligatorio No supervisores)
				</g:if>
				<g:else>
					<sec:ifNotGranted roles="ROLE_ADMIN">
						<g:if test="${(isEditing == 'true')}">
							<g:select id="supervisor" optionKey="id" 
								from="${ar.org.scouts.sifs.security.PersonaRol.findAllByRol(ar.org.scouts.sifs.security.Rol.findByAuthority('ROLE_SUPERVISOR')).persona}" 
								noSelection="['null': '']" name="supervisor.id" 
								value="${personaInstance?.supervisor?.id}" class="many-to-one" 
								style="width: 208px" />(Obligatorio No supervisores)
						</g:if>
						<g:else>
							<g:select id="supervisor" optionKey="id" 
								from="${ar.org.scouts.sifs.security.PersonaRol.findAllByRol(ar.org.scouts.sifs.security.Rol.findByAuthority('ROLE_SUPERVISOR')).persona}" 
								noSelection="['null': '']" name="supervisor.id" 
								value="${personaInstance?.supervisor?.id}" class="many-to-one" 
								style="width: 208px" disabled="disabled" />(Obligatorio No supervisores)
						</g:else>
					</sec:ifNotGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN">
						<g:select id="supervisor" optionKey="id" 
							from="${ar.org.scouts.sifs.security.PersonaRol.findAllByRol(ar.org.scouts.sifs.security.Rol.findByAuthority('ROLE_SUPERVISOR')).persona}" 
							noSelection="['null': '']" name="supervisor.id" 
							value="${personaInstance?.supervisor?.id}" class="many-to-one" 
							style="width: 208px" />(Obligatorio No supervisores)
					</sec:ifAnyGranted>
				</g:else>
				<g:hiddenField name="supervisorHdd" />
			</div>
		</sec:ifAnyGranted>
	</div>

	<div id="tabs-4" style="font-size: 14px;">
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SUPERVISOR">

			<g:if test="${(isEditing != 'true')}">
				<div 
					class="fieldcontain ${hasErrors(bean: personaInstance, field: 'password', 'error')} required">
					<label for="password"><g:message 
						code="persona.password.label" size="20" default="Contraseña" />
						<span class="required-indicator">*</span>
					</label>
<%--					<g:textField name="password" required="" value="${personaInstance?.password}" />--%>
					<g:checkBox name="verpassword" checked="false" title="Ver password" />
					<g:passwordField name="password" 
						value="${personaInstance?.password}" required="true" />
					<g:passwordField name="password2" title="Confirme su clave"/>(Obligatorio)
				</div>
			</g:if>
			<div 
				class="fieldcontain ${hasErrors(bean: personaInstance, field: 'enabled', 'error')} ">
				<label for="enabled"><g:message 
					code="persona.enabled.label" default="Cuenta Activa" />
				</label>
				<g:checkBox name="enabled" value="${personaInstance?.enabled}" 
					checked="true" readonly="true" />
<%--			</div>--%>
<%----%>
<%--			<div --%>
<%--				class="fieldcontain ${hasErrors(bean: personaInstance, field: 'accountLocked', 'error')} ">--%>
				<label for="accountLocked" style="left: 0px; position: relative; max-width: 150px">
				<g:message 	code="persona.accountLocked.label" default="Cuenta Bloqueada" />
				</label>
				<g:checkBox name="accountLocked" 
					value="${personaInstance?.accountLocked}" />
			</div>

<%--			<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'accountExpired', 'error')} ">--%>
<%--				<label for="accountExpired">--%>
<%--					<g:message code="persona.accountExpired.label" default="Cuenta " />--%>
<%--				</label>--%>
<%--				<g:checkBox name="accountExpired" value="${personaInstance?.accountExpired}" />--%>
<%--			</div>--%>

<%--			<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'passwordExpired', 'error')} ">--%>
<%--				<label for="passwordExpired">--%>
<%--					<g:message code="persona.passwordExpired.label" default="Password E" />--%>
<%--				</label>--%>
<%--				<g:checkBox name="passwordExpired" value="${personaInstance?.passwordExpired}" />--%>
<%--			</div>--%>

			<div 
				class="fieldcontain ${hasErrors(bean: personaInstance, field: 'roles', 'error')} ">
				<label for="roles"><g:message code="persona.roles.label" 
					default="Roles" />
				</label>
			</div>

			<g:each var="rol" in="${ar.org.scouts.sifs.security.Rol.list()}">
				<sec:ifAnyGranted roles="ROLE_SUPERVISOR,ROLE_ADMIN">
					<g:if test="${(rol.authority == 'ROLE_FORMADOR') || (rol.authority == 'ROLE_SUPERVISOR') || (rol.authority == 'ROLE_DISENADOR')}">
						<div class="fieldcontain ">
							<g:if test="${(rol.authority == 'ROLE_DISENADOR')}">
								<label for="rolRaw[${rol.id}]"></label>
								<g:checkBox name="rolRaw[${rol.id}]" value="${rol.authority}" checked="${personaInstance.hasRol(rol)}" />Diseñador
							</g:if>
							<g:else>
								<label for="rolRaw[${rol.id}]"></label>
								<g:checkBox name="rolRaw[${rol.id}]" value="${rol.authority}" checked="${personaInstance.hasRol(rol)}" />${rol.name}
							</g:else>
						</div>
					</g:if>
				</sec:ifAnyGranted>
				<sec:ifAnyGranted roles="ROLE_ADMIN">
					<g:if test="${(rol.authority == 'ROLE_ADMIN')}">
						<div class="fieldcontain ">
							<label for="rolRaw[${rol.id}]"></label>
							<g:checkBox name="rolRaw[${rol.id}]" value="${rol.authority}" 
								checked="${personaInstance.hasRol(rol)}" />
								${rol.name}
						</div>
					</g:if>
				</sec:ifAnyGranted>
			</g:each>
<%--			</div>--%>
			<%-- || (rol.authority == 'ROLE_DISENADOR')--%>
		</sec:ifAnyGranted>
	</div>
</div>


<script>

	function jsonZonaDistritoGrupoSupervisorSuccess(html) {
		var options = null;
	
		$('#zona').val(html.zonaSelected);
		$("input[name='zonaHdd']").val(html.zonaSelected);

		options = ' ';
		options += '<option value="null"></option>';
		for (var i = 0; i < html.distritos.length; i++) {
			options += '<option value="' + html.distritos[i].id + '">' + html.distritos[i].nombre + '</option>';
		}
		$('#distrito').html(options);
		$('#distrito').val(html.distritoSelected);
		$("input[name='distritoHdd']").val(html.distritoSelected);
	
		options = ' ';
		options += '<option value="null"></option>';
		for (var i = 0; i < html.grupos.length; i++) {
			options += '<option value="' + html.grupos[i].id + '">' + html.grupos[i].nombre + '</option>';
		}
		$('#grupo').html(options);
		$('#grupo').val(html.grupoSelected);
		$("input[name='grupoHdd']").val(html.grupoSelected);
	
		options = ' ';
		options += '<option value="null"></option>';
		for (var i = 0; i < html.supervisores.length; i++) {
			options += '<option value="' + html.supervisores[i].id + '">' + html.supervisores[i].apellido + ', ' + html.supervisores[i].nombre + '</option>';
		}
		$('#supervisor').html(options);
		$('#supervisor').val(html.supervisorSelected);
		$("input[name='supervisorHdd']").val(html.supervisorSelected);
	}


	$(document).ready(function() {


		$("#zona").change(function() {
			$.ajax({
				url: "${g.createLink(controller:'persona',action:'jsonZonaDistritoGrupoSupervisor')}",
				data: { znParam: this.value, dstrtParam: null, grpParam: null, prsnParam: null },
				cache: false,
				success: function(html) {
					jsonZonaDistritoGrupoSupervisorSuccess(html);
				}
			});
		});
	
	
		$("#distrito").change(function() {
			$.ajax({
				url: "${g.createLink(controller:'persona',action:'jsonZonaDistritoGrupoSupervisor')}",
				data: { znParam: $('#zona').val(), dstrtParam: this.value, grpParam: null, prsnParam: null },
				cache: false,
				success: function(html) {
					jsonZonaDistritoGrupoSupervisorSuccess(html);
				}
			});
		});
	
	
		$("#grupo").change(function() {
			$.ajax({
				url: "${g.createLink(controller:'persona',action:'jsonZonaDistritoGrupoSupervisor')}",
				data: { znParam: $('#zona').val(), dstrtParam: $('#distrito').val(), grpParam: this.value, prsnParam: null },
				cache: false,
				success: function(html) {
					jsonZonaDistritoGrupoSupervisorSuccess(html);
				}
			});
		});
	
	
		$("#supervisor").change(function() {
			$.ajax({
				url: "${g.createLink(controller:'persona',action:'jsonZonaDistritoGrupoSupervisor')}",
				data: { znParam: $('#zona').val(), dstrtParam: $('#distrito').val(), grpParam: $('#grupo').val(), prsnParam: this.value },
				cache: false,
				success: function(html) {
					jsonZonaDistritoGrupoSupervisorSuccess(html);
				}
			});
		});
	
	
		$("#verpassword").change(function() {
			if (this.checked) {
				document.getElementById('password').type = 'text';
				document.getElementById('password2').style.display = "none";
			} else {
				document.getElementById('password').type = 'password';
				document.getElementById('password2').style.display = "inline";
			}
		});
	
		<g:if test="${(isEditing != 'true')}">
			<sec:ifAnyGranted roles="ROLE_SUPERVISOR">
				$('#supervisor').val(${sec.loggedInUserInfo(field:'id').toString()});
				$('#supervisor').trigger('change');
			</sec:ifAnyGranted>
		</g:if>

	});


</script>
