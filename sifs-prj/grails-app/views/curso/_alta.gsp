<%@ page import="ar.org.scouts.sifs.Curso" %>


<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="curso.nombre.label" default="Nombre" />
	</label>
	<g:textField name="nombre" value="${cursoInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nivel', 'error')} required">
	<label for="nivel">
		<g:message code="curso.nivel.label" default="Nivel" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="nivel" name="nivel.id" from="${ar.org.scouts.sifs.Nivel.list()}" optionKey="id" required="" value="${cursoInstance?.nivel?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="curso.descripcion.label" default="Descripcion" />
	</label>
	<g:textField name="descripcion" value="${cursoInstance?.descripcion}"/>

</div>

		<div id="tabs">
		  <ul>
		    <li><a href="#tabs-1">
				  <g:message code="curso.correlativas.label" default="Correlativas" />
				</a>
			</li>
		  </ul>
		  <div id="tabs-1">
		  	 <div style="font-size:11px;">
				<g:checkBoxList name="cursosIds" from="${Curso.list()}" value="${cursoInstance?.correlativas?.collect{it.id}}" optionKey="id"/>
			 </div>
		  </div>
		</div>

