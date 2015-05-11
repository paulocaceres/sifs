<%@ page import="ar.org.scouts.sifs.Grupo" %>



<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="grupo.numero.label" default="Numero" />
	</label>
	<g:textField name="numero" maxlength="4" pattern="${grupoInstance.constraints.numero.matches}" value="${grupoInstance?.numero}" />
</div>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="grupo.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${grupoInstance?.nombre}" />
</div>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'supervisores', 'error')} ">
	<label for="supervisores">
		<g:message code="grupo.supervisores.label" default="Supervisores" />
	</label>
	<ul class="one-to-many">
		<g:each in="${grupoInstance?.supervisores?}" var="s">
			<li>
				<g:link controller="persona" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link>
			</li>
		</g:each>
		<li class="add">
			<g:link controller="persona" action="create" params="['grupo.id': grupoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'persona.label', default: 'Persona')])}</g:link>
		</li>
	</ul>
</div>

