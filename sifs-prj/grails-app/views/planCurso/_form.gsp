<%@ page import="ar.org.scouts.sifs.PlanCurso" %>



<div class="fieldcontain ${hasErrors(bean: planCursoInstance, field: 'plan', 'error')} required">
	<label for="plan">
		<g:message code="planCurso.plan.label" default="Plan" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="plan" name="plan.id" from="${planCursoInstance.plan}" optionKey="id" required="" value="${planCursoInstance?.plan?.id}" class="many-to-one" disabled="${actionName=='edit'}" />

</div>

<div class="fieldcontain ${hasErrors(bean: planCursoInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="planCurso.curso.label" default="Curso" /><span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${ar.org.scouts.sifs.Curso.list()}" optionKey="id" required="" value="${planCursoInstance?.curso?.id}" class="many-to-one" disabled="${actionName=='edit'}" />

</div>

<g:if test="${actionName=='edit'}">

<div class="fieldcontain ${hasErrors(bean: planCursoInstance, field: 'correlativas', 'error')} ">
	<label for="correlativas">
		<g:message code="planCurso.correlativas.label" default="Correlativas" />
		
	</label>
	<g:select name="correlativas" from="${ar.org.scouts.sifs.Curso.list()}" multiple="multiple" optionKey="id" size="5" value="${planCursoInstance?.correlativas*.id}" class="many-to-many"/>

</div>

</g:if>
