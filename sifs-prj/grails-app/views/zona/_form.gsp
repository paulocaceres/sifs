<%@ page import="ar.org.scouts.sifs.Zona" %>

<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="zona.numero.label" default="Número" />
		
	</label>
<input type="text" name="numero" value="${zonaInstance?.numero}" id="numero" maxlength="2" size="2" />

</div>

<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="zona.nombre.label" default="Nombre" />
		
	</label>
<input type="text" name="nombre" value="${zonaInstance?.nombre}" id="nombre" maxlength="100" size="60" />

</div>
