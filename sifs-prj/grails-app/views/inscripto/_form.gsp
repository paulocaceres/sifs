<%@ page import="ar.org.scouts.sifs.domain.Inscripto" %>



<div class="fieldcontain ${hasErrors(bean: inscriptoInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="inscripto.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${ar.org.scouts.sifs.domain.Curso.list()}" optionKey="id" required="" value="${inscriptoInstance?.curso?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: inscriptoInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="inscripto.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${inscriptoInstance?.fecha}"  />

</div>

