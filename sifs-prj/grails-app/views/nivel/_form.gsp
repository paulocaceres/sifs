<%@ page import="ar.org.scouts.sifs.Nivel" %>



<div class="fieldcontain ${hasErrors(bean: nivelInstance, field: 'nivelCol', 'error')} ">
	<label for="nivelCol">
		<g:message code="nivel.nivelCol.label" default="Nivel Col" />
		
	</label>
	<g:textField name="nivelCol"  value="${nivelInstance?.nivelCol}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: nivelInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="nivel.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" size="20" value="${nivelInstance?.nombre}"/>

</div>

