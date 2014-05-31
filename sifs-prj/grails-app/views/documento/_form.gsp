<%@ page import="ar.org.scouts.sifs.domain.Documento" %>



<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="documento.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${documentoInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="documento.tipo.label" default="Tipo" />
		
	</label>
	<g:textField name="tipo" value="${documentoInstance?.tipo}"/>

</div>

