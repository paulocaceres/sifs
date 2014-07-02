<%@ page import="ar.org.scouts.sifs.Persona" %>



<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="persona.apellido.label" default="Apellido" />
		
	</label>
	<g:textField name="apellido" value="${personaInstance?.apellido}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'bloqueado', 'error')} ">
	<label for="bloqueado">
		<g:message code="persona.bloqueado.label" default="Bloqueado" />
		
	</label>
	<g:checkBox name="bloqueado" value="${personaInstance?.bloqueado}" />

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="persona.direccion.label" default="Direccion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="direccion" name="direccion.id" from="${ar.org.scouts.sifs.Direccion.list()}" optionKey="id" required="" value="${personaInstance?.direccion?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'documentoNumero', 'error')} ">
	<label for="documentoNumero">
		<g:message code="persona.documentoNumero.label" default="Documento Numero" />
		
	</label>
	<g:textField name="documentoNumero" value="${personaInstance?.documentoNumero}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'mail', 'error')} ">
	<label for="mail">
		<g:message code="persona.mail.label" default="Mail" />
		
	</label>
	<g:textField name="mail" value="${personaInstance?.mail}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="persona.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${personaInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'superior', 'error')} required">
	<label for="superior">
		<g:message code="persona.superior.label" default="Superior" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="superior" name="superior.id" from="${ar.org.scouts.sifs.Persona.list()}" optionKey="id" required="" value="${personaInstance?.superior?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'zona', 'error')} required">
	<label for="zona">
		<g:message code="persona.zona.label" default="Zona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="zona" name="zona.id" from="${ar.org.scouts.sifs.Zona.list()}" optionKey="id" required="" value="${personaInstance?.zona?.id}" class="many-to-one"/>

</div>

