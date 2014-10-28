<%@ page import="ar.org.scouts.sifs.Distrito" %>



<div class="fieldcontain ${hasErrors(bean: distritoInstance, field: 'grupos', 'error')} ">
	<label for="grupos">
		<g:message code="distrito.grupos.label" default="Grupos" />
		
	</label>
	<g:select name="grupos" from="${ar.org.scouts.sifs.Grupo.list()}" multiple="multiple" optionKey="id" size="5" value="${distritoInstance?.grupos*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: distritoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="distrito.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${distritoInstance?.nombre}"/>

</div>

