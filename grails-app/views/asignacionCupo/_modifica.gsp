<%@ page import="ar.org.scouts.sifs.Curso" %>


<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nombre', 'error')}">
	<label for="nombre">
		<g:message code="curso.nombre.label" default="Nombre" />
	</label>
	<g:textField name="nombre" value="${cursoInstance?.nombre}" disabled="true"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="curso.descripcion.label" default="Descripcion" />
	</label>
	<g:textField name="descripcion" value="${cursoInstance?.descripcion}" disabled="true"/>

</div>


