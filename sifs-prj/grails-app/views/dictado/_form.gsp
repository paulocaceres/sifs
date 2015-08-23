<%@ page import="ar.org.scouts.sifs.Dictado" %>

<div id="tabs" style="max-width:900px; left:140px ;position:relative">
  		<ul>
    		<li><a href="#tabs-1">Datos de Formador</a></li>
    		<li><a href="#tabs-2">Datos de Curso</a></li>

  		</ul>

<g:set var="userId" value="${sec.loggedInUserInfo(field: 'id')}" />

<div id="tabs-1" style="font-size:14px;">	
	<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'formador', 'error')} ">
	        <label for="formador">
	                <g:message code="dictado.formador.label" default="Formador Responsable" />
	
	        </label>
	        <g:select id="formador" name="formador.id" from="${ar.org.scouts.sifs.Persona.listarPorRol('ROLE_FORMADOR')}" optionKey="id" value="${dictadoInstance?.formador?.id}" class="many-to-one" noSelection="['null': '']"/>
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'zona', 'error')} ">
		<label for="zona">
			<g:message code="dictado.zona.label" default="Zona" />
			
		</label>
		<g:select id="zona" name="zona.id" from="${ar.org.scouts.sifs.Zona.listarPorPersona(userId)}" optionKey="id" value="${dictadoInstance?.zona?.id}" class="many-to-one" noSelection="['null': '']"/>
	
	</div>
</div>
	
<div id="tabs-2" style="font-size:14px;">	
	<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'curso', 'error')} ">
		<label for="curso">
			<g:message code="dictado.curso.label" default="Curso" />
			
		</label>
		<g:select id="curso" name="curso.id" from="${ar.org.scouts.sifs.Curso.list()}" optionKey="id" value="${dictadoInstance?.curso?.id}" class="many-to-one" noSelection="['null': '']"/>
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'fecha', 'error')} ">
		<label for="fecha">
			<g:message code="dictado.fecha.label" default="Fecha" />
			
		</label>
		<g:datePicker name="fecha" precision="day"  value="${dictadoInstance?.fecha}" default="none" noSelection="['': '']" />
	
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'nombre', 'error')} ">
		<label for="nombre">
			<g:message code="dictado.nombre.label" default="Nombre" />
			
		</label>
		<g:field name="nombre" type="text" size="20" value="${dictadoInstance.nombre}"/>
	
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'cupo', 'error')} ">
		<label for="cupo">
			<g:message code="dictado.cupo.label" default="Cupo" />
			
		</label>
		<g:field name="cupo" type="number" value="${dictadoInstance.cupo}" min="0" max="30"/>
	
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'recursos', 'error')} ">
		<label for="recursos">
			<g:message code="dictado.recursos.label" default="Recursos" />		
		</label>
		<g:select name="recursos" from="${ar.org.scouts.sifs.Recurso.list()}" multiple="multiple" optionKey="id" size="5" value="${dictadoInstance?.recursos*.id}" class="many-to-many"/>
	
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: dictadoInstance, field: 'status', 'error')} ">
	        <label for="status">
	                <g:message code="dictado.status.label" default="Estado" />
	
	        </label>
	        <g:select id="status" name="status.id" from="${ar.org.scouts.sifs.DictadoStatus.findByNombre('ABIERTO')}" optionKey="id" value="${dictadoInstance?.status?.id}" class="many-to-one" noSelection="['null': '']"/>
	</div>
</div>
</div>