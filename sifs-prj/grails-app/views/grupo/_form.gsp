<%@ page import="ar.org.scouts.sifs.Grupo" %>



<div class="fieldcontain ${hasErrors(bean: distritoInstance, field: 'zona', 'error')} required">
	<label for="zona">
		<g:message code="distrito.zona.label" default="Zona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="zona" name="zona.id" from="${ar.org.scouts.sifs.Zona.list(sort:'numero')}" optionKey="id" noSelection="['null': '']" required="" value="${grupoInstance?.distrito?.zona?.id}" class="many-to-one" />
</div>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'distrito', 'error')} required">
	<label for="distrito">
		<g:message code="grupo.distrito.label" default="Distrito" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="distrito" name="distrito.id" from="${ar.org.scouts.sifs.Distrito.list(sort:'nombre')}" optionKey="id" noSelection="['null': '']" required="" value="${grupoInstance?.distrito?.id}" class="many-to-one" />
</div>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="grupo.numero.label" default="Numero" />
	</label>
	<g:textField name="numero" maxlength="4" pattern="${grupoInstance.constraints.numero.matches}" value="${grupoInstance?.numero}" />
</div>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="grupo.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" size="20" required="" value="${grupoInstance?.nombre}" />
</div>


<script>


	function jsonZonaDistritoSuccess(html) {
		var options = null;

		$('#zona').val(html.zonaSelected);
		
		options = ' ';
		options += '<option value="null"> </option>';
		for (var i = 0; i < html.distritos.length; i++) {
			options += '<option value="' + html.distritos[i].id + '">' + html.distritos[i].nombre + '</option>';
		}                    
		$('#distrito').html(options);
		$('#distrito').val(html.distritoSelected);
		
		options = ' ';
		options += '<option value="null"> </option>';
		for (var i = 0; i < html.grupos.length; i++) {
			options += '<option value="' + html.grupos[i].id + '">' + html.grupos[i].nombre + '</option>';
		}                    
		$('#grupo').html(options);
		$('#grupo').val(html.grupoSelected);
		
		options = ' ';
		options += '<option value="null"> </option>';
		for (var i = 0; i < html.supervisores.length; i++) {
			options += '<option value="' + html.supervisores[i].id + '">' + html.supervisores[i].apellido + ', ' + html.supervisores[i].nombre + '</option>';
		}                    
		$('#supervisor').html(options);
		$('#supervisor').val(html.supervisorSelected);
	}
	

	$(document).ready(function() {


		$("#zona").change(function() {
			$.ajax({
				url: "${g.createLink(controller:'grupo',action:'jsonZonaDistrito')}",
				data: { znParam: this.value, dstrtParam: null },
				cache: false,
				success: function(html) {
					jsonZonaDistritoSuccess(html);
				}
			});
		});


		$("#distrito").change(function() {
			$.ajax({
				url: "${g.createLink(controller:'grupo',action:'jsonZonaDistrito')}",
				data: { znParam: $('#zona').val(), dstrtParam: this.value },
				cache: false,
				success: function(html) {
					jsonZonaDistritoSuccess(html);
				}
			});
		});


	});
	

</script>
