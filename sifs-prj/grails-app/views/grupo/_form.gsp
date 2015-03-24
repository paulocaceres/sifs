<%@ page import="ar.org.scouts.sifs.Grupo" %>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="grupo.numero.label" default="Número" />
		
	</label>
	<input type="text" name="numero" value="${grupoInstance?.numero}" id="numero" maxlength="4" size="4" />

</div>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="grupo.nombre.label" default="Nombre" />
		
	</label>
	<input type="text" name="nombre" value="${grupoInstance?.nombre}" id="nombre" maxlength="100" size="60" />

</div>

