<%@ page import="ar.org.scouts.sifs.Recurso" %>

<div class="fieldcontain ${hasErrors(bean: recursoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="recurso.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" size="20" value="${recursoInstance?.nombre}"/>

</div>


<div class="fieldcontain ${hasErrors(bean: recursoInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="recurso.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cantidad" type="number" value="${recursoInstance.cantidad}" required=""/>

</div>
<!--
<div class="fieldcontain ${hasErrors(bean: recursoInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="recurso.direccion.label" default="Direccion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="direccion" name="direccion.id" from="${ar.org.scouts.sifs.Direccion.list()}" optionKey="id" required="" value="${recursoInstance?.direccion?.id}" class="many-to-one"/>
</div>
-->
