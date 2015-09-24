<!DOCTYPE html>
<sec:ifNotLoggedIn>
	${response.sendRedirect('/sifs-prj/login')}
</sec:ifNotLoggedIn>
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
				display: inline; /*   +++ float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
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
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			body {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	font-style: normal;
	font-weight: bold;
	background-color: #FFF;
	color: #000;
}
#destacado #H1 {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size: 36px;
	font-weight: 800;
	font-variant: normal;
	font-style: oblique;
	color: #00C;
	margin-right: auto;
	margin-left: auto;
	margin-top: 0px;
	margin-bottom: 0px;
	position: relative;
}
#contenido #BP {
	clip: rect(auto,auto,auto,auto);
}



#primero {
	width: 960px;
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
}
#primero #cabecera {
	font-family: Georgia, "Times New Roman", Times, serif;
	with:41%;
	font-size: 48px;
	font-weight: bolder;
	text-decoration: overline;
	text-transform: capitalize;
	border-top-style: double;
	border-right-style: double;
	border-bottom-style: double;
	border-left-style: double;
		</style>
	</head>
	<body>
<div id="primero">
  <div id="cabecera">
    <p align="center"><img src="images/scout3.png" alt="" name="scout" width="26%" height="21%" id="scout" /></p>
  </div>
  <div id="destacado">
    <p id="H1">Sistema Integrado de Formación Scout</p>
  </div>
  <div id="contenido">
    <p>&quot;Ningún hombre puede ser llamado educado, si no tiene una buena voluntad, un deseo y una capacidad entrenada para hacer su parte en el trabajo del mundo&quot;</p>
  <p id="BP" align="right">B.P.</p>
 
  </div>
  <div id="pie">
    <p>S.I.F.S.</p>
    <p>Todos los Derechos Reservados 2015</p>
  </div>
</div>
</body>
</html>