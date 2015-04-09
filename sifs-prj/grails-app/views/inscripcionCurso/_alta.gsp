<%@ page import="ar.org.scouts.sifs.Curso" %>

		<div id="tabs">
		  <ul>
			<li><a href="#tabs-2">
				  <g:message code="curso.correlativas.label2" default="Cursos Disponibles" />
				</a>
			</li>
		  </ul>
		  <div id="tabs-2">
		  	   	 
		  	 	<div id="accordion">
		  	 	  <g:each in="${inscripcionInstanceList}" status="i" var="cursoInstance">
		  	 	  	
				  		<h3 style="font-size:11px;">
				  			 <g:fieldValue bean="${cursoInstance}" field="nombre"/>
				  		</h3>
				  		<div style="font-size:12px;">
				  			<g:radioGroup name="dictadosGroup" 
				  				labels="${cursoInstance.dictados}" 
				  				values="${cursoInstance.dictados.id}">
    						${it.radio}
    						<label class="long">
    							<strong>${it.label.nombre}, &nbsp;${it.label.zona?.nombre} - ${it.label.formador?.nombre}&nbsp;${it.label.formador?.apellido}</strong>
    						</label><br/>
							</g:radioGroup>
				  	
				    	</div>
				    
				  </g:each>  
				</div>

			
		  </div>
		</div>

