<%@ page import="ar.org.scouts.sifs.Plan" %>


<div class="fieldcontain ${hasErrors(bean: planInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="plan.nombre.label" default="Nombre" />
	</label>
	<g:textField name="nombre" value="${planInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: planInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="plan.descripcion.label" default="Descripcion" />
	</label>
	<g:textField name="descripcion" value="${planInstance?.descripcion}"/>
</div>

<%--<div class="fieldcontain ${hasErrors(bean: planInstance, field: 'validez', 'error')} required">--%>
<%--	<label for="validez">--%>
<%--		<g:message code="plan.validez.label" default="Validez" />--%>
<%--		<span class="required-indicator">*</span>--%>
<%--	</label>--%>
<%--	<g:datePicker name="validez" precision="day"  value="${planInstance?.validez}"  />--%>
<%----%>
<%--</div>--%>

