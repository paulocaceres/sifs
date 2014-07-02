<%@ page import="ar.org.scouts.sifs.Recurso" %>



<div class="fieldcontain ${hasErrors(bean: recursoInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="recurso.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cantidad" type="number" value="${recursoInstance.cantidad}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: recursoInstance, field: 'direccion', 'error')} ">
	<label for="direccion">
		<g:message code="recurso.direccion.label" default="Direccion" />
		
	</label>
	<g:textField name="direccion" value="${recursoInstance?.direccion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recursoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="recurso.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${recursoInstance?.nombre}"/>

</div>

