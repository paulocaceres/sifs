<%@ page import="ar.org.scouts.sifs.Titulo" %>



<div class="fieldcontain ${hasErrors(bean: tituloInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="titulo.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="70" required="" value="${tituloInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: distritoInstance, field: 'cursos', 'error')} ">
	<label for="grupos">
		<g:message code="distrito.cursos.label" default="Grupos" />
		
	</label>
	<g:select name="cursos" from="${ar.org.scouts.sifs.Curso.list()}" multiple="multiple" optionKey="id" size="5" value="${distritoInstance?.cursos*.id}" class="many-to-many"/>

</div>
