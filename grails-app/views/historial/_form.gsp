<%@ page import="ar.org.scouts.sifs.Historial" %>
<%@ page import="ar.org.scouts.sifs.Persona" %>
<%@ page import="ar.org.scouts.sifs.Curso" %>


<div class="fieldcontain ${hasErrors(bean: historialInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="historial.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" required=""
		noSelection="${['null':'Select One...']}"
		from="${Persona.list()}"
		optionKey="id" optionValue="${{it.nombre + ' ' + it.apellido}}"
		value="${historialInstance?.persona?.id}" />
</div>

<div class="fieldcontain ${hasErrors(bean: historialInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="historial.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" optionKey="id" required=""
		noSelection="${['null':'Select One...']}"
		from="${Curso.list()}"
		value="${historialInstance?.curso?.id}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: historialInstance, field: 'fechaAprobacion', 'error')} required">
	<label for="fechaAprobacion">
		<g:message code="historial.fechaAprobacion.label" default="Fecha Aprobacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAprobacion" precision="day"  value="" />

</div>
