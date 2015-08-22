<%@ page import="ar.org.scouts.sifs.Titulo" %>

<div class="fieldcontain ${hasErrors(bean: tituloInstance, field: 'plan', 'error')} required">
	<label for="plan">
		<g:message code="titulo.plan.label" default="Plan" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="Plan" name="plan.id"  required="" from="${ar.org.scouts.sifs.Plan.list()}" optionKey="id" value="${tituloInstance?.plan?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tituloInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="titulo.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" size="20" maxlength="20" required="" value="${tituloInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: distritoInstance, field: 'cursos', 'error')} ">
	<label for="grupos">
		<g:message code="distrito.cursos.label" default="Cursos" />
		
	</label>
	<g:select name="cursos" from="${ar.org.scouts.sifs.Curso.list()}" multiple="multiple" optionKey="id" size="5" value="${distritoInstance?.cursos*.id}" class="many-to-many"/>

</div>
