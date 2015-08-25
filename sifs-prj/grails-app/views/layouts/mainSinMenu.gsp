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
