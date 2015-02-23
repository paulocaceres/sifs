<%@ page import="ar.org.scouts.sifs.Grupo" %>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="grupo.numero.label" default="Número" />
		
	</label>
	<g:textField name="numero" value="${grupoInstance?.numero}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="grupo.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${grupoInstance?.nombre}"/>

</div>

