<%@ page import="ar.org.scouts.sifs.Distrito" %>



<div class="fieldcontain ${hasErrors(bean: distritoInstance, field: 'zona', 'error')} required">
	<label for="zona">
		<g:message code="distrito.zona.label" default="Zona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="zona" name="zona.id" from="${ar.org.scouts.sifs.Zona.list(sort:'numero')}" optionKey="id" required="" value="${distritoInstance?.zona?.id}" class="many-to-one"/>(Obligatorio)
</div>

<div class="fieldcontain ${hasErrors(bean: distritoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="distrito.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" size="20" required="" value="${distritoInstance?.nombre}"/>(Obligatorio)
</div>
