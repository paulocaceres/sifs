<%@ page import="ar.org.scouts.sifs.Curso" %>

		<div id="tabs">
		  <ul>
			<li><a href="#tabs-2">
				  <g:message code="curso.correlativas.label2" default="Cursos Disponibles" />
				</a>
			</li>
		  </ul>
		  <div id="tabs-2">
		  	 <div style="font-size:11px;">
		  	   	 
		  	 	<div id="accordion">
		  	 	  <g:each in="${inscripcionInstanceList}" status="i" var="cursoInstance">	
				  	<h3>${fieldValue(bean: cursoInstance, field: "nombre")}</h3>
				  	<div>
				  	  <ul>
				      	<li>Dictado La Quiaca</li>
				      	<li>Dictado Iglesia Gaudalupe</li>
				      	<li>Dictado San Felipe Neri</li>
				      </ul>
				    </div>
				  </g:each>  
				</div>

			 </div>
		  </div>
		</div>

