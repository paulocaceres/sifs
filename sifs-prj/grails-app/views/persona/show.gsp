<%@ page import="ar.org.scouts.sifs.Persona" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
			<g:set var="entityName" value="${message(code: 'persona.label', default: 'Persona')}" />
			<title>
<%--				<g:message code="default.show.label" args="[entityName]" />--%>
				Mi Perfil
			</title>
	</head>
	<body>
		<a href="#show-persona" class="skip" tabindex="-1">
			<g:message code="default.link.skip.label" default="Skip to content&hellip;" />
		</a>
		<div class="nav" role="navigation">
			<ul>
				<li>
					<a class="home" href="${createLink(uri: '/')}">
						<g:message code="default.home.label" />
					</a>
				</li>
				<sec:ifAnyGranted roles="ROLE_ADMIN">
					<li>
						<g:link class="list" action="index">
							<g:message code="default.list.label" args="[entityName]" />
						</g:link>
					</li>
				<li>
					<g:link class="create" action="create">
						<g:message code="default.new.label" args="[entityName]" />
					</g:link>
				</li>
				</sec:ifAnyGranted>
			</ul>
		</div>
		<div id="show-persona" class="content scaffold-show" role="main">
			<h1>
<%--				<g:message code="default.show.label" args="[entityName]" />--%>
					Mi Perfil
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list persona">

				<g:if test="${personaInstance?.documentoNumero}">
					<li class="fieldcontain">
						<span id="documentoNumero-label" class="property-label">
							<g:message code="persona.documentoNumero.label" default="Documento Numero" />
						</span>
						<span class="property-value" aria-labelledby="documentoNumero-label">
							<g:fieldValue bean="${personaInstance}" field="documentoNumero" />
						</span>
					</li>
				</g:if>

				<g:if test="${personaInstance?.nombre}">
					<li class="fieldcontain">
						<span id="nombre-label" class="property-label">
							<g:message code="persona.nombre.label" default="Nombre" />
						</span>
						<span class="property-value" aria-labelledby="nombre-label">
							<g:fieldValue bean="${personaInstance}" field="nombre" />
						</span>
					</li>
				</g:if>

				<g:if test="${personaInstance?.apellido}">
					<li class="fieldcontain">
						<span id="apellido-label" class="property-label">
							<g:message code="persona.apellido.label" default="Apellido" />
						</span>
						<span class="property-value" aria-labelledby="apellido-label">
							<g:fieldValue bean="${personaInstance}" field="apellido" />
						</span>
					</li>
				</g:if>

<%--				<g:if test="${personaInstance?.mail}">--%>
<%--					<li class="fieldcontain">--%>
<%--						<span id="mail-label" class="property-label">--%>
<%--							<g:message code="persona.mail.label" default="Mail" />--%>
<%--						</span>--%>
<%--						<span class="property-value" aria-labelledby="mail-label">--%>
<%--							<g:fieldValue bean="${personaInstance}" field="mail" />--%>
<%--						</span>--%>
<%--					</li>--%>
<%--				</g:if>--%>

<%--				<g:if test="${personaInstance?.direccion}">--%>
<%--					<li class="fieldcontain">--%>
<%--						<span id="direccion-label" class="property-label">--%>
<%--							<g:message code="persona.direccion.label" default="Direccion" />--%>
<%--						</span>--%>
<%--						<span class="property-value" aria-labelledby="direccion-label">--%>
<%--							${personaInstance?.direccion?.calle} ${personaInstance?.direccion?.numero}--%>
<%--							<g:if test="${personaInstance?.direccion?.adicional != null}">${personaInstance?.direccion?.adicional}</g:if> --%>
<%--							<br/>--%>
<%--							${personaInstance?.direccion?.codigoPostal} ${personaInstance?.direccion?.ciudad} --%>
<%--							<br/>--%>
<%--							${personaInstance?.direccion?.provincia}--%>
<%--						</span>--%>
<%--					</li>--%>
<%--				</g:if>--%>
<%--				<g:if test="${personaInstance?.telefono}">--%>
<%--					<li class="fieldcontain">--%>
<%--						<span id="telefono-label" class="property-label">--%>
<%--							<g:message code="persona.telefono.label" default="Telefono" />--%>
<%--						</span>--%>
<%--						<span class="property-value" aria-labelledby="telefono-label">--%>
<%--							<g:fieldValue bean="${personaInstance}" field="telefono" />--%>
<%--						</span>--%>
<%--					</li>--%>
<%--				</g:if>--%>

<sec:ifAnyGranted roles="ROLE_ADMIN">

				<g:if test="${personaInstance?.zona}">
					<li class="fieldcontain">
						<span id="zona-label" class="property-label">
							<g:message code="persona.zona.label" default="Zona" />
						</span>
						<span class="property-value" aria-labelledby="zona-label">
							${personaInstance?.zona?.encodeAsHTML()}
						</span>
					</li>
				</g:if>

				<g:if test="${personaInstance?.distrito}">
					<li class="fieldcontain">
						<span id="distrito-label" class="property-label">
							<g:message code="persona.distrito.label" default="Distrito" />
						</span>
						<span class="property-value" aria-labelledby="distrito-label">
							${personaInstance?.distrito?.encodeAsHTML()}
						</span>
					</li>
				</g:if>

				<g:if test="${personaInstance?.grupo}">
					<li class="fieldcontain">
						<span id="grupo-label" class="property-label">
							<g:message code="persona.grupo.label" default="Grupo" />
						</span>
						<span class="property-value" aria-labelledby="grupo-label">
							${personaInstance?.grupo?.encodeAsHTML()}
						</span>
					</li>
				</g:if>

				<g:if test="${personaInstance?.supervisor}">
					<li class="fieldcontain">
						<span id="supervisor-label" class="property-label">
							<g:message code="persona.supervisor.label" default="Supervisor" />
						</span>
						<span class="property-value" aria-labelledby="supervisor-label">
							${personaInstance?.supervisor?.encodeAsHTML()}
						</span>
					</li>
				</g:if>

<%----%>
<%--				<g:if test="${personaInstance?.password}">--%>
<%--					<li class="fieldcontain">--%>
<%--						<span id="password-label" class="property-label">--%>
<%--							<g:message code="persona.password.label" default="Password" />--%>
<%--						</span>--%>
<%--						<span class="property-value" aria-labelledby="password-label">--%>
<%--							<g:fieldValue bean="${personaInstance}" field="password" />--%>
<%--						</span>--%>
<%--					</li>--%>
<%--				</g:if>--%>
<%----%>
<%----%>

				<g:if test="${personaInstance?.accountExpired}">
					<li class="fieldcontain">
						<span id="accountExpired-label" class="property-label">
							<g:message code="persona.accountExpired.label" default="Expirado" />
						</span>

						<span class="property-value" aria-labelledby="accountExpired-label">
							<g:formatBoolean boolean="${personaInstance?.accountExpired}" />
						</span>

					</li>
				</g:if>

				<g:if test="${personaInstance?.accountLocked}">
					<li class="fieldcontain">
						<span id="accountLocked-label" class="property-label">
							<g:message code="persona.accountLocked.label" default="Bloqueado" />
						</span>

						<span class="property-value" aria-labelledby="accountLocked-label">
							<g:formatBoolean boolean="${personaInstance?.accountLocked}"/>
						</span>

					</li>
				</g:if>

				<g:if test="${personaInstance?.enabled}">
					<li class="fieldcontain">
						<span id="enabled-label" class="property-label">
							<g:message code="persona.enabled.label" default="Activo" />
						</span>

						<span class="property-value" aria-labelledby="enabled-label">
							<g:formatBoolean boolean="${personaInstance?.enabled}" />
						</span>

					</li>
				</g:if>

				<g:if test="${personaInstance?.passwordExpired}">
					<li class="fieldcontain">
						<span id="passwordExpired-label" class="property-label">
							<g:message code="persona.passwordExpired.label" default="Password expirado" />
						</span>

						<span class="property-value" aria-labelledby="passwordExpired-label">
							<g:formatBoolean boolean="${personaInstance?.passwordExpired}" />
						</span>

					</li>
				</g:if>
</sec:ifAnyGranted>				

			</ol>

			<g:form url="[resource:personaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${personaInstance}">
						<g:message code="default.button.edit.label" default="Editar" />
					</g:link>
<%--					<sec:ifAnyGranted roles="ROLE_ADMIN">--%>
<%--						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--%>
<%--					</sec:ifAnyGranted>--%>
				</fieldset>
			</g:form>

		</div>
	</body>
</html>
