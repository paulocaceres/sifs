<%@ page import="ar.org.scouts.sifs.Plan" %>
<%@ page import="ar.org.scouts.sifs.Curso" %>


<div class="fieldcontain ${hasErrors(bean: planInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="plan.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${planInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: planInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="plan.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${planInstance?.nombre}"/>

</div>

<%--<div class="fieldcontain ${hasErrors(bean: planInstance, field: 'validez', 'error')} required">--%>
<%--	<label for="validez">--%>
<%--		<g:message code="plan.validez.label" default="Validez" />--%>
<%--		<span class="required-indicator">*</span>--%>
<%--	</label>--%>
<%--	<g:datePicker name="validez" precision="day"  value="${planInstance?.validez}"  />--%>
<%----%>
<%--</div>--%>

<div id="tabs">
  <ul>
    <li><a href="#tabs-1">
		  <g:message code="curso.correlativas.label" default="Cursos" />
		</a>
	</li>
  </ul>
  <div id="tabs-1">
  	 <div style="font-size:11px;">
		<g:checkBoxList name="cursosIds" from="${Curso.list()}" value="${planInstance?.cursos?.collect{it.id}}" optionKey="id"/>
	 </div>
  </div>
</div>

