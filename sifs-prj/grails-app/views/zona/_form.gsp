<%@ page import="ar.org.scouts.sifs.Zona" %>



<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="zona.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${zonaInstance?.nombre}"/>

</div>

