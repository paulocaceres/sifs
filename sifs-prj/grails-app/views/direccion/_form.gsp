<%@ page import="ar.org.scouts.sifs.Direccion" %>



<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'adicional', 'error')} ">
	<label for="adicional">
		<g:message code="direccion.adicional.label" default="Adicional" />
		
	</label>
	<g:textField name="adicional" value="${direccionInstance?.adicional}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'calle', 'error')} ">
	<label for="calle">
		<g:message code="direccion.calle.label" default="Calle" />
		
	</label>
	<g:textField name="calle" value="${direccionInstance?.calle}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'ciudad', 'error')} ">
	<label for="ciudad">
		<g:message code="direccion.ciudad.label" default="Ciudad" />
		
	</label>
	<g:textField name="ciudad" value="${direccionInstance?.ciudad}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'codigoPostal', 'error')} ">
	<label for="codigoPostal">
		<g:message code="direccion.codigoPostal.label" default="Codigo Postal" />
		
	</label>
	<g:textField name="codigoPostal" value="${direccionInstance?.codigoPostal}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="direccion.numero.label" default="Numero" />
		
	</label>
	<g:textField name="numero" value="${direccionInstance?.numero}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'provincia', 'error')} required">
	<label for="provincia">
		<g:message code="direccion.provincia.label" default="Provincia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="provincia" name="provincia.id" from="${ar.org.scouts.sifs.Provincia.list()}" optionKey="id" required="" value="${direccionInstance?.provincia?.id}" class="many-to-one"/>

</div>

