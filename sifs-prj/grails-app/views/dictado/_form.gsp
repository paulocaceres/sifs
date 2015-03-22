<%@ page import="ar.org.scouts.sifs.Dictado" %>



<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'zona', 'error')} ">
	<label for="zona">
		<g:message code="dictado.zona.label" default="Zona" />
		
	</label>
	<g:select id="zona" name="zona.id" from="${ar.org.scouts.sifs.Zona.list()}" optionKey="id" value="${dictadoInstance?.zona?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'fecha', 'error')} ">
	<label for="fecha">
		<g:message code="dictado.fecha.label" default="Fecha" />
		
	</label>
	<g:datePicker name="fecha" precision="day"  value="${dictadoInstance?.fecha}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'cupo', 'error')} ">
	<label for="cupo">
		<g:message code="dictado.cupo.label" default="Cupo" />
		
	</label>
	<g:field name="cupo" type="number" value="${dictadoInstance.cupo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'inscriptos', 'error')} ">
	<label for="inscriptos">
		<g:message code="dictado.inscriptos.label" default="Inscriptos" />
		
	</label>
	<g:select name="inscriptos" from="${ar.org.scouts.sifs.Persona.list()}" multiple="multiple" optionKey="id" size="5" value="${dictadoInstance?.inscriptos*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'recursos', 'error')} ">
	<label for="recursos">
		<g:message code="dictado.recursos.label" default="Recursos" />
		
	</label>
	<g:select name="recursos" from="${ar.org.scouts.sifs.Recurso.list()}" multiple="multiple" optionKey="id" size="5" value="${dictadoInstance?.recursos*.id}" class="many-to-many"/>

</div>

