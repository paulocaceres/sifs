<%@ page import="ar.org.scouts.sifs.Zona" %>



<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="zona.numero.label" default="Número" />
	</label>
	<g:textField name="numero" size="2" pattern="${zonaInstance.constraints.numero.matches}" value="${zonaInstance?.numero}" />
</div>

<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="zona.nombre.label" default="Nombre" />
	</label>
	<g:textField name="nombre" size="20" value="${zonaInstance?.nombre}" />
</div>

