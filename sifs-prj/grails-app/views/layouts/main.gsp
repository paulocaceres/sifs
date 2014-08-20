<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'menu.css')}" type="text/css">
		<script type="text/javascript" src="../js/menu.js"></script>
		<g:layoutHead/>
		<g:javascript library="application"/>	
		<g:javascript library="jquery"/>
		<r:layoutResources />
	</head>
	<body>
	
	<!-- ===================================================================================== -->
	<div id="menu">
		<ul class="menu">
			<li><a href="#" class="parent"><span>Home</span></a>
				<div><ul>
					<li><a href=""><span>Sub Item 1</span></a></li>
					<li><a href="#"><span>Sub Item 2</span></a></li>
					<li><a href="#"><span>Sub Item 3</span></a></li>
				</ul></div>
			</li>
			<li><a href="#" class="parent"><span>Product Info</span></a>
				<div><ul>
					<li><a href="#"><span>Sub Item 1</span></a></li>
					<li><a href="#"><span>Sub Item 2</span></a></li>
					<li><a href="#"><span>Sub Item 3</span></a></li>
					<li><a href="#"><span>Sub Item 4</span></a></li>
				</ul></div>
			</li>
			<li><a href="#" class="parent"><span>Help</span></a></li>
			<li><a href="#"><span>Contacts</span></a></li>
			<sec:ifLoggedIn>
				<li class="last"><a href="${createLink(controller: 'logout')}"><span>Logout</span></a></li>
			</sec:ifLoggedIn>
		</ul>
	</div>	
	
<!-- ===================================================================================== -->		
	
	<table>
		<tr bgcolor="#ABBF78">
			<td>
				<div>
					<img src="${resource(dir: 'images', file: 'Scout-logo.PNG')}" alt="SIFS"/>
					<img src="${resource(dir: 'images', file: 'Sifs-logo.PNG')}" alt="SIFS"/>
				</div>
			</td>
	</tr>
</table>	
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<r:layoutResources />
	</body>
</html>
