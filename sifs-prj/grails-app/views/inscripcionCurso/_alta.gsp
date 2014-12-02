<%@ page import="ar.org.scouts.sifs.Curso" %>

		<div id="tabs">
		  <ul>
		    <li><a href="#tabs-1">
				  <g:message code="curso.correlativas.label" default="Cursos Disponibles" />
				</a>
			</li>
			<li><a href="#tabs-2">
				  <g:message code="curso.correlativas.label2" default="Cursos Disponibles II" />
				</a>
			</li>
		  </ul>
		  <div id="tabs-1">
		  	 <div style="font-size:11px;">
				<g:each var="c" in="${inscripcionInstanceList}" >
					<g:checkBox name="cursoRaw[${c.id}]" value="${c.id}" checked="false"/>&nbsp ${c.nombre}<br/>
				</g:each> 
			 </div>
		  </div>
		  <div id="tabs-2">
		  	 <div style="font-size:11px;">
				<g:checkBoxList name="cursosAnotadosIds" 
							    from="${inscripcionInstanceList}" 
							    optionKey="id"/>
			 </div>
		  </div>
		</div>

