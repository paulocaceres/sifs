<%@ page import="ar.org.scouts.sifs.Curso" %>



<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nivel', 'error')} ">
	<label for="nivel">
		<g:message code="curso.nivel.label" default="Nivel" />
		
	</label>
	<g:select id="nivel" name="nivel.id" from="${ar.org.scouts.sifs.Nivel.list()}" optionKey="id" value="${cursoInstance?.nivel?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="curso.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${cursoInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'contenidos', 'error')} ">
	<label for="contenidos">
		<g:message code="curso.contenidos.label" default="Contenidos" />
		
	</label>
	<g:select name="contenidos" from="${ar.org.scouts.sifs.Contenido.list()}" multiple="multiple" optionKey="id" size="5" value="${cursoInstance?.contenidos*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'dictados', 'error')} ">
	<label for="dictados">
		<g:message code="curso.dictados.label" default="Dictados" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cursoInstance?.dictados?}" var="d">
    <li><g:link controller="dictado" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="dictado" action="create" params="['curso.id': cursoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'dictado.label', default: 'Dictado')])}</g:link>
</li>
</ul>


</div>

