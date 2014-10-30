<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bienvenido al Sitema Integral de Formacion Scout - SIFS</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<div id="page-body" role="main">
			<h1>Bienvenido al Sistema Integral de Formacion Scout - SIFS</h1>
			<p>Con esta herramineta ud. podra centralizar y mejorar el actual proceso de administración e inscripción de 
			   los cursos de capacitación que son exigidos en la formación de líderes. Optimizar la seguridad, los tiempos y 
			   la asignación de recursos de la organización involucrados en los planes de capacitación.
			   Disponer y adecuar los planes de formación requeridos para las funciones actuales y futuras.
			 </p>
			
			
			<div id="controller-list" role="navigation">
				<h2>Operaciones disponibles:</h2>
				<ul>
					<li><a href="${createLink(controller: 'curso')}"><span>Cursos</span></a></li>
					<li><a href="${createLink(controller: 'distrito')}"><span>Distritos</span></a></li>
					<li><a href="${createLink(controller: 'zona')}"><span>Zonas</span></a></li>
					<li><a href="${createLink(controller: 'grupo')}"><span>Grupos</span></a></li>
					<li><a href="${createLink(controller: 'contenido')}"><span>Contenidos</span></a></li>
					<li><a href="${createLink(controller: 'recurso')}"><span>Recursos</span></a></li>
					<li><a href="${createLink(controller: 'persona')}"><span>Personas</span></a></li>
				</ul>
			</div>
		</div>
	</body>
</html>
