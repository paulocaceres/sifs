<%@ page import="ar.org.scouts.sifs.Curso" %>

		<div id="tabs" style="max-width:900px; left:140px ;position:relative">
		  <ul>
			<li><a href="#tabs-2">
				  <g:message code="curso.correlativas.label2" default="Cursos Disponibles" />
				</a>
			</li>
		  </ul>
		  <div id="tabs-2" style="font-size:14px;">
		  	   	 
		  	 	<div id="accordion">
		  	 	  <g:each in="${inscripcionInstanceList}" status="i" var="cursoInstance">
		  	 	  	   
				  		<h3 style="font-size:11px;">
				  			 <g:fieldValue bean="${cursoInstance}" field="nombre"/>
				  		</h3>
				  		<div style="font-size:12px;">
				  			<g:radioGroup name="dictadoGroup${cursoInstance.id}" 
				  				labels="${cursoInstance.dictados}" 
				  				values="${cursoInstance.dictados.id}">
				  			<g:if test="${it.label.cupo != 0 && it.label.fecha >= new Date()+1 && 
								  			usuarioId != it.label.formador?.id && it.label.status?.nombre != 'CERRADO' &&
								  			!usuarioLogueado.dictadosAprobados?.contains(it.label.id) && 
								  			!usuarioLogueado.dictadosAnotados?.contains(it.label.id)}">	
    							${it.radio}
                                   	<label class="long">
    									<strong><g:formatDate format="dd/MM/yyyy" date="${it.label.fecha}" /> - ${it.label.nombre}, &nbsp;${it.label.zona?.nombre} - ${it.label.formador?.nombre}&nbsp;${it.label.formador?.apellido}</strong>	
    								</label><br/>
    							</g:if>
							</g:radioGroup>
				  	         <g:hiddenField name="cursosDisponiblesIds" value="${cursoInstance.id}"/>
				    	</div>
				    
				  </g:each>  
				</div>

			
		  </div>
		</div>

