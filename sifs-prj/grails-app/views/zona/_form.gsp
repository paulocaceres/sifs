<%@ page import="ar.org.scouts.sifs.Zona" %>



<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="zona.numero.label" default="Número" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" maxlength="2" size="2" required="" pattern="${zonaInstance.constraints.numero.matches}" value="${zonaInstance?.numero}" />(Obligatorio)
</div>

<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="zona.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" size="20" required="" value="${zonaInstance?.nombre}" />(Obligatorio)
</div>

