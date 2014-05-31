<%@ page import="ar.org.scouts.sifs.domain.Historial" %>



<div class="fieldcontain ${hasErrors(bean: historialInstance, field: 'calificacion', 'error')} required">
	<label for="calificacion">
		<g:message code="historial.calificacion.label" default="Calificacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="calificacion" type="number" value="${historialInstance.calificacion}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: historialInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="historial.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${ar.org.scouts.sifs.domain.Curso.list()}" optionKey="id" required="" value="${historialInstance?.curso?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: historialInstance, field: 'fechaAprobacion', 'error')} required">
	<label for="fechaAprobacion">
		<g:message code="historial.fechaAprobacion.label" default="Fecha Aprobacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAprobacion" precision="day"  value="${historialInstance?.fechaAprobacion}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: historialInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="historial.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" from="${ar.org.scouts.sifs.domain.Persona.list()}" optionKey="id" required="" value="${historialInstance?.persona?.id}" class="many-to-one"/>

</div>

