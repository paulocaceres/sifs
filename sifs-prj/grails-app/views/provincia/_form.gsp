<%@ page import="ar.org.scouts.sifs.Provincia" %>



<div class="fieldcontain ${hasErrors(bean: provinciaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="provincia.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${provinciaInstance?.descripcion}"/>

</div>

