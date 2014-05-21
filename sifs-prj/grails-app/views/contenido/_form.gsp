<%@ page import="com.sifs.prj.Contenido" %>



<div class="fieldcontain ${hasErrors(bean: contenidoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="contenido.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${contenidoInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: contenidoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="contenido.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${contenidoInstance?.nombre}"/>

</div>

