<html>

<head>
<title><g:message code='spring.security.ui.resetPassword.title'/></title>
<!--  meta name='layout' content='registro'/ -->
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'spring-security-ui.css')}" type="text/css">
	<script src="/sifs-prj/static/js/jquery.js" type="text/javascript" ></script>
	<script src="/sifs-prj/static/js/jquery-ui.js" type="text/javascript" ></script>
</head>

<body>

<p/>

<s2ui:form width='475' height='250' elementId='resetPasswordFormContainer'
           titleCode='spring.security.ui.resetPassword.header' center='true'>

	<g:form action='resetPassword' name='resetPasswordForm' autocomplete='off'>
	
	<g:if test='${resetSuccess}'>
		<br/>
			<g:message code='spring.security.ui.resetPassword.success'/>
		<br/>
		<br/>
		<g:link uri="${nextUrl}"  class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">Continuar</g:link>
	</g:if>
	<g:else>
		<g:hiddenField name='t' value='${token}'/>
		<div class="sign-in">
	
		<br/>
		<h4><g:message code='spring.security.ui.resetPassword.description'/></h4>
	
		<table>
			<s2ui:passwordFieldRow name='password' labelCode='resetPasswordCommand.password.label' bean="${command}"
	                             labelCodeDefault='Contraseña' value="${command?.password}"/>
	
			<s2ui:passwordFieldRow name='password2' labelCode='resetPasswordCommand.password2.label' bean="${command}"
	                             labelCodeDefault='Contraseña (Otra vez)' value="${command?.password2}"/>
		</table>
	
		<s2ui:submitButton elementId='reset' form='resetPasswordForm' messageCode='spring.security.ui.resetPassword.submit'/>
	
		</div>
	</g:else>	
	</g:form>
</s2ui:form>

<script>
$(document).ready(function() {
	$('#password').focus();
});
</script>

</body>
</html>
