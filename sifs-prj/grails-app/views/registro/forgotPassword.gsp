<html>

<head>
<title><g:message code='spring.security.ui.forgotPassword.title'/></title>
	<meta name="layout" content="mainSinMenu"/>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'spring-security-ui.css')}" type="text/css">
	<script src="/sifs-prj/static/js/jquery.js" type="text/javascript" ></script>
	<script src="/sifs-prj/static/js/jquery-ui.js" type="text/javascript" ></script>
	
</head>

<body>

<p/>

<s2ui:form width='400' height='300' elementId='forgotPasswordFormContainer'
           titleCode='spring.security.ui.forgotPassword.header' center='true'>

	<g:form action='forgotPassword' name="forgotPasswordForm" autocomplete='off'>
	
	<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
	</g:if>

	<g:if test='${emailSent}'>
	<br/>
		<g:message code='spring.security.ui.forgotPassword.sent'/>
	</g:if>
	<g:else>
		<br/>
		<h4><g:message code='spring.security.ui.forgotPassword.description'/></h4>
	
		<table>
			<tr>
				<td><label for="username"><g:message code='spring.security.ui.forgotPassword.username'/></label></td>
				<td><g:textField name="username" size="8" /></td>
			</tr>
		</table>
	
		<s2ui:submitButton elementId='reset' form='forgotPasswordForm' messageCode='spring.security.ui.forgotPassword.submit'/>
	</g:else>

	</g:form>
</s2ui:form>

<script>
$(document).ready(function() {
	$('#username').focus();
});
</script>

</body>
</html>
