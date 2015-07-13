package ar.org.scouts.sifs

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import groovy.text.SimpleTemplateEngine

class RegistroController {
	static allowedMethods = [register: 'POST']
	
	def mailService
	def messageSource
	def springSecurityService

    def index() { }
	
	def forgotPassword() {
		
				if (!request.post) {
					// show the form
					return
				}
		
				String username = params.username
				if (!username) {
					flash.error = message(code: 'spring.security.ui.forgotPassword.username.missing')
					redirect action: 'forgotPassword'
					return
				}
	
				String usernameFieldName = SpringSecurityUtils.securityConfig.userLookup.usernamePropertyName
				def user = lookupUserClass().findWhere((usernameFieldName): username)
				if (!user) {
					flash.error = message(code: 'spring.security.ui.forgotPassword.user.notFound')
					redirect action: 'forgotPassword'
					return
				}
		
				def registrationCode = new CodigoRegistro(username: user."$usernameFieldName")
				registrationCode.save(flush: true)
		
				String url = generateLink('resetPassword', [t: registrationCode.token])
		
				def conf = SpringSecurityUtils.securityConfig
				def body = conf.ui.forgotPassword.emailBody
				if (body.contains('$')) {
					body = evaluate(body, [user: user, url: url])
				}
				mailService.sendMail {
					to user.mail
					from conf.ui.forgotPassword.emailFrom
					subject conf.ui.forgotPassword.emailSubject
					html body.toString()
				}
		
				[emailSent: true]
			}
	
	def resetPassword(ResetPasswordCommand command) {
		
				String token = params.t
		
				def registrationCode = token ? CodigoRegistro.findByToken(token) : null
				if (!registrationCode) {
					flash.error = message(code: 'spring.security.ui.resetPassword.badCode')
					redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
					return
				}
		
				if (!request.post) {
					return [token: token, command: new ResetPasswordCommand()]
				}
		
				command.username = registrationCode.username
				command.validate()
		
				if (command.hasErrors()) {
					return [token: token, command: command]
				}
		
				//String salt = saltSource instanceof NullSaltSource ? null : registrationCode.username
				CodigoRegistro.withTransaction { status ->
					String usernameFieldName = SpringSecurityUtils.securityConfig.userLookup.usernamePropertyName
					def user = lookupUserClass().findWhere((usernameFieldName): registrationCode.username)
					user.password = command.password //springSecurityService.encodePassword(command.password)
					user.save()
					registrationCode.delete()
				}
		
				springSecurityService.reauthenticate registrationCode.username
		
				//flash.message = message(code: 'spring.security.ui.resetPassword.success')
		
				def conf = SpringSecurityUtils.securityConfig
				String postResetUrl = conf.ui.register.postResetUrl ?: conf.successHandler.defaultTargetUrl
				[resetSuccess: true, nextUrl: postResetUrl]
				//redirect uri: postResetUrl
			}
	
	
	protected String generateLink(String action, linkParams) {
		createLink(base: "$request.scheme://$request.serverName:$request.serverPort$request.contextPath",
				controller: 'registro', action: action,
				params: linkParams)
	}
	
	
	protected String evaluate(s, binding) {
		new SimpleTemplateEngine().createTemplate(s).make(binding)
	}

	static final passwordValidator = { String password, command ->
		if (command.username && command.username.equals(password)) {
			return 'command.password.error.username'
		}

		if (!checkPasswordMinLength(password, command) ||
			!checkPasswordMaxLength(password, command) ||
			!checkPasswordRegex(password, command)) {
			return 'command.password.error.strength'
		}
	}

	static boolean checkPasswordMinLength(String password, command) {
		int minLength = 8
		password && password.length() >= minLength
	}

	static boolean checkPasswordMaxLength(String password, command) {
		int maxLength = 64
		password && password.length() <= maxLength
	}

	static boolean checkPasswordRegex(String password, command) {
		String passValidationRegex = '^.*(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$'
		password && password.matches(passValidationRegex)
	}

	static final password2Validator = { value, command ->
		if (command.password != command.password2) {
			return 'command.password2.error.mismatch'
		}
	}
	
	protected String lookupUserClassName() {
		SpringSecurityUtils.securityConfig.userLookup.userDomainClassName
	}

	protected Class<?> lookupUserClass() {
		grailsApplication.getDomainClass(lookupUserClassName()).clazz
	}
}

class ResetPasswordCommand {
	String username
	String password
	String password2

	static constraints = {
		password blank: false, validator: RegistroController.passwordValidator
		password2 validator: RegistroController.password2Validator
	}
}
