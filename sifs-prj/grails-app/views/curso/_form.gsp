<%@ page import="ar.org.scouts.sifs.Curso" %>



<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'contenido', 'error')} required">
	<label for="contenido">
		<g:message code="curso.contenido.label" default="Contenido" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="contenido" name="contenido.id" from="${ar.org.scouts.sifs.Contenido.list()}" optionKey="id" required="" value="${cursoInstance?.contenido?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'correlativas', 'error')} required">
	<label for="correlativas">
		<g:message code="curso.correlativas.label" default="Correlativas" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="correlativas" type="number" value="${cursoInstance.correlativas}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'cupo', 'error')} required">
	<label for="cupo">
		<g:message code="curso.cupo.label" default="Cupo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cupo" type="number" value="${cursoInstance.cupo}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="curso.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${cursoInstance?.fecha}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'inscripto', 'error')} ">
	<label for="inscripto">
		<g:message code="curso.inscripto.label" default="Inscripto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="inscripto" name="inscripto.id" from="${ar.org.scouts.sifs.Persona.list()}" optionKey="id" value="${cursoInstance?.inscripto?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nivel', 'error')} required">
	<label for="nivel">
		<g:message code="curso.nivel.label" default="Nivel" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="nivel" name="nivel.id" from="${ar.org.scouts.sifs.Nivel.list()}" optionKey="id" required="" value="${cursoInstance?.nivel?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="curso.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${cursoInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'plan', 'error')} required">
	<label for="plan">
		<g:message code="curso.plan.label" default="Plan" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="plan" name="plan.id" from="${ar.org.scouts.sifs.Plan.list()}" optionKey="id" required="" value="${cursoInstance?.plan?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'recurso', 'error')} required">
	<label for="recurso">
		<g:message code="curso.recurso.label" default="Recurso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="recurso" name="recurso.id" from="${ar.org.scouts.sifs.Recurso.list()}" optionKey="id" required="" value="${cursoInstance?.recurso?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'zona', 'error')} required">
	<label for="zona">
		<g:message code="curso.zona.label" default="Zona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="zona" name="zona.id" from="${ar.org.scouts.sifs.Zona.list()}" optionKey="id" required="" value="${cursoInstance?.zona?.id}" class="many-to-one"/>

</div>

