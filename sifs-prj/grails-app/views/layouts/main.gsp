<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="Grails" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.ico')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'menu.css')}"
	type="text/css">
<script src="/sifs-prj/static/js/jquery.js" type="text/javascript"></script>
<script src="/sifs-prj/static/js/application.js" type="text/javascript"></script>
<script type="text/javascript" src="/sifs-prj/static/js/menu.js"></script>

<g:layoutHead />

<!--  g:javascript library="jquery" plugin="jquery"/>
		<script type="text/javascript">jQuery.noConflict();</script>
		<g:javascript library="application"/>
		<script type="text/javascript" src="../js/menu.js"></script>
		-->
<r:layoutResources />
</head>
<body>

	<!-- ===================================================================================== -->
	<div id="menu">
		<sec:ifLoggedIn>
			<ul class="menu">
		</sec:ifLoggedIn>
		<sec:ifLoggedIn>
			<li><a href="/sifs-prj/home" class="parent"><span>Inicio</span></a></li>
		</sec:ifLoggedIn>
		<sec:ifAllGranted roles="ROLE_CURSANTE">
			<li><a href="#" class="parent"><span>Cursante</span></a>
				<div>
					<ul>
						<li><a href="${createLink(controller: 'persona', action: 'show')}"><span>Mi
									Perfil</span></a></li>
						<li><a href="${createLink(controller: 'inscripcionCurso')}"><span>Inscripcion
									a Cursos</span></a></li>
						<li><a href="#"><span>Habilitaciones</span></a></li>
						<li><a
							href="${createLink(controller: 'cursosAprobados', action: 'anotados')}"><span>Cursos
									Anotados</span></a></li>
						<li><a href="${createLink(controller: 'cursosAprobados')}"><span>Cursos
									Aprobados</span></a></li>
					</ul>
				</div></li>
		</sec:ifAllGranted>
		<sec:ifAllGranted roles="ROLE_SUPERVISOR">
			<li><a href="#" class="parent"><span>Supervisor</span></a>
				<div>
					<ul>
						<%--						<li><a href="#"><span>Aprobacion Perfiles</span></a></li>--%>
												<li><a href="#"><span>Mis Supervisados</span></a></li>
<%--						<li><a href="${createLink(controller: 'cursosAprobados')}"><span>Mis--%>
<%--									Cursos Aprobados</span></a></li>--%>
					</ul>
				</div></li>
		</sec:ifAllGranted>
		<sec:ifAllGranted roles="ROLE_FORMADOR">
			<li><a href="#" class="parent"><span>Formador</span></a>
				<div>
					<ul>
						<%--						<li><a href="${createLink(controller: 'recurso')}"><span>Recursos</span></a></li>--%>
						<%--						<li><a href="#"><span>Validar Inscripciones</span></a></li>--%>
						<li><a href="${createLink(controller: 'dictado')}"><span>Dictados</span></a></li>
						<li><a href="${createLink(controller: 'aprobacionCurso')}"><span>Aprobacion
									Cursos</span></a></li>
					</ul>
				</div></li>
		</sec:ifAllGranted>
		<%--			<sec:ifAllGranted roles="ROLE_DNF">--%>
		<%--				<li><a href="#" class="parent"><span>Director Nac. Formacion</span></a>--%>
		<%--					<div><ul>--%>
		<%--						<li><a href="${createLink(controller: 'curso')}"><span>Cursos</span></a></li>--%>
		<%--						<li><a href="${createLink(controller: 'plan')}"><span>Planes</span></a></li>--%>
		<%--						<li><a href="${createLink(controller: 'titulo')}"><span>Titulos</span></a></li>--%>
		<%--						<li><a href="#"><span>Contenidos</span></a></li>--%>
		<%--					</ul></div>--%>
		<%--				</li>--%>
		<%--			</sec:ifAllGranted>--%>
		<%--			<sec:ifAllGranted roles="ROLE_DNRA">--%>
		<%--				<li><a href="#" class="parent"><span>Seguridad</span></a>--%>
		<%--					<div><ul>--%>
		<%--						<li><a href="#"><span>Esquema Bloqueo de Usuarios</span></a></li>--%>
		<%--						<li><a href="#"><span>Autorizaciones</span></a></li>--%>
		<%--					</ul></div>--%>
		<%--				</li>--%>
		<%--			</sec:ifAllGranted>--%>
		<sec:ifAllGranted roles="ROLE_ADMIN">
			<li><a href="#" class="parent"><span>Administradores</span></a>
				<div>
					<ul>
						<li><a href="#" class="parent"><span>Cursante</span></a>
							<div>
								<ul>
									
									<li><a
										href="${createLink(controller: 'inscripcionCurso')}"><span>Inscripcion
												a Cursos</span></a></li>
									<li><a href="#"><span>Habilitaciones</span></a></li>
									<li><a
										href="${createLink(controller: 'cursosAprobados', action: 'anotados')}"><span>Cursos
												Anotados</span></a></li>
									<li><a href="${createLink(controller: 'cursosAprobados')}"><span>Cursos
												Aprobados</span></a></li>
								</ul>
							</div></li>

						<li><a href="#" class="parent"><span>Supervisor</span></a>
							<div>
								<ul>
									<li><a href="#"><span>Aprobacion Perfiles</span></a></li>
									<li><a href="#"><span>Bloquear Usuarios</span></a></li>
									<li><a href="${createLink(controller: 'cursosAprobados')}"><span>Mis
												Cursos Aprobados</span></a></li>
								</ul>
							</div></li>

						<li><a href="#" class="parent"><span>Formador</span></a>
							<div>
								<ul>
									<%--								<li><a href="${createLink(controller: 'recurso')}"><span>Recursos</span></a></li>--%>
									<li><a href="${createLink(controller: 'dictado')}"><span>Dictados</span></a></li>
									<li><a href="${createLink(controller: 'aprobacionCurso')}"><span>Aprobacion
												Cursos</span></a></li>
								</ul>
							</div></li>

						<%--						<li><a href="#" class="parent"><span>Seguridad</span></a>--%>
						<%--							<div><ul>--%>
						<%--								<li><a href="#"><span>Bloqueo de Usuarios</span></a></li>--%>
						<%--								<li><a href="#"><span>Autorizaciones</span></a></li>--%>
						<%--							</ul></div>--%>
						<%--						</li>--%>

						<li><a href="#" class="parent"><span>ABM</span></a>
							<div>
								<ul>
								    <li><a href="${createLink(controller: 'persona')}"><span>Perfil de Usuarios</span></a></li>
									<li><a href="${createLink(controller: 'titulo')}"><span>Titulos</span></a></li>
									<li><a href="${createLink(controller: 'curso')}"><span>Cursos</span></a></li>
<%--									<li><a href="${createLink(controller: 'dictado')}"><span>Dictados</span></a></li>--%>
									<li><a href="${createLink(controller: 'plan')}"><span>Planes</span></a></li>
<%--								<li><a href="#"><span>Contenidos</span></a></li>--%>
									<li><a href="${createLink(controller: 'zona')}"><span>Zonas</span></a></li>
									<li><a href="${createLink(controller: 'distrito')}"><span>Distritos</span></a></li>
									<li><a href="${createLink(controller: 'grupo')}"><span>Grupos</span></a></li>
<%--									<li><a href="${createLink(controller: 'nivel')}"><span>Niveles</span></a></li>--%>
								</ul>
							</div></li>
					</ul>
				</div></li>
		</sec:ifAllGranted>
		<sec:ifLoggedIn>

			<%--			<li><a href="#" class="parent"><span>Ayuda</span></a></li>--%>
			<li><a href="${createLink(controller: 'contactForm')}"><span>Contacto</span></a></li>
			<li class="last"><a href="${createLink(controller: 'logout')}"><span>Salir</span></a></li>
		</sec:ifLoggedIn>
		<sec:ifLoggedIn>
		</ul>
		</sec:ifLoggedIn>
	</div>

	<!-- ===================================================================================== -->

	<table>
		<tr bgcolor="#ABBF78">
			<td height=50px>
				<div>
					<sec:ifNotLoggedIn>
						<img src="${resource(dir: 'images', file: 'SIFS-AZUL2.PNG')}"
							alt="SIFS" />
					</sec:ifNotLoggedIn>
				</div>
			</td>
		</tr>
	</table>
	<g:layoutBody />
	<div class="footer" role="contentinfo"></div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<r:layoutResources />
</body>
</html>
