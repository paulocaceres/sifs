<%@ page import="ar.org.scouts.sifs.Zona" %>



<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="zona.numero.label" default="Número" />
	</label>
	<g:textField name="numero" pattern="${zonaInstance.constraints.numero.matches}" value="${zonaInstance?.numero}" />
</div>

<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="zona.nombre.label" default="Nombre" />
	</label>
	<g:textField name="nombre" value="${zonaInstance?.nombre}" />
</div>

<div class="fieldcontain ${hasErrors(bean: zonaInstance, field: 'distritos', 'error')} ">
	<label for="distritos">
		<g:message code="zona.distritos.label" default="Distritos" />
	</label>
	<g:select name="distritos" from="${ar.org.scouts.sifs.Distrito.list()}" multiple="multiple" optionKey="id" size="5" value="${zonaInstance?.distritos*.id}" class="many-to-many" />
</div>
