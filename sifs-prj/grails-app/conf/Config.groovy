import java.awt.Font
import java.awt.Color

import com.octo.captcha.service.multitype.GenericManageableCaptchaService
import com.octo.captcha.engine.GenericCaptchaEngine
import com.octo.captcha.image.gimpy.GimpyFactory
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator
import com.octo.captcha.component.image.color.SingleColorGenerator
import com.octo.captcha.component.image.textpaster.NonLinearTextPaster

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.excludes = ['/WEB-INF/**']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
	
	mail {
		to = "sifs.paulo@gmail.com"
		host = "smtp.gmail.com"
		port = 465
		username = "sifs.paulo@gmail.com"
		password = "welcome90081"
		props = ["mail.smtp.auth":"true",
			"mail.smtp.socketFactory.port":"465",
			"mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			"mail.smtp.socketFactory.fallback":"false"]
	}
}

 
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

jcaptchas {
	captchaImage = new GenericManageableCaptchaService(
		new GenericCaptchaEngine(
			new GimpyFactory(
				new RandomWordGenerator(
					"abcdefghijklmnopqrstuvwxyz1234567890" // allowed characters
				),
				new ComposedWordToImage(
					new RandomFontGenerator(
						20, // min font size
						30, // max font size
						[new Font("Arial", 0, 10)] as Font[] // font type
					),
					new GradientBackgroundGenerator(
						140, // background width
						35, // background height
						new SingleColorGenerator(new Color(0, 60, 0)), // first background colour
						new SingleColorGenerator(new Color(20, 20, 20)) // second background colour
					),
					new NonLinearTextPaster(
						6, // minimal length of text
						6, // maximal length of text
						new Color(0, 255, 0) // text colour
					)
				)
			)
		),
		180, // minGuarantedStorageDelayInSeconds
		180000 // maxCaptchaStoreSize
	)
}

grails.views.javascript.library="jquery"

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.password.encodeHashAsBase64 = true
grails.plugins.springsecurity.userLookup.userDomainClassName = 'ar.org.scouts.sifs.Persona'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'ar.org.scouts.sifs.security.PersonaRol'
grails.plugins.springsecurity.userLookup.usernamePropertyName='documentoNumero'
grails.plugins.springsecurity.authority.className = 'ar.org.scouts.sifs.security.Rol'
grails.plugins.springsecurity.successHandler.defaultTargetUrl = '/home'
grails.plugins.springsecurity.dao.reflectionSaltSourceProperty = 'username'

grails.plugins.springsecurity.ui.encodePassword = false 
grails.plugins.springsecurity.ui.forgotPassword.emailBody = ''' Hola $user.nombre,<br/> <br/> Tu (o alguien pretendiendo ser tu) ha solicitado cambiar la contraseña.<br/><br/> Si tu no has hecho esta solicitud, por favor ignora este email; no se han hecho cambios.<br/><br/> Si tu hiciste esta solicitud, entonces presiona <a href="$url">aqui</a> para restablecer tu contraseña. '''
grails.plugins.springsecurity.ui.forgotPassword.emailFrom = 'do.not.reply@localhost'
grails.plugins.springsecurity.ui.forgotPassword.emailSubject = 'Restablecer Contraseña'	
grails.plugins.springsecurity.ui.forgotPassword.postResetUrl = '/home' // use defaultTargetUrl if not set
grails.plugins.springsecurity.ui.personaCreada.emailBody = ''' Hola $user.nombre,<br/> <br/> Tu cuenta de acceso a SIFS(Sistema Integral de Fomacion Scout) se ha creado con exito!<br/><br/> Tu contraseña para ingresar al sistema es $scoutpwd, recomendamos la cambies desde Olvide mi contraseña<br/><br/> Presiona <a href="$url">aqui</a> para ingresar al sistema.'''
grails.plugins.springsecurity.ui.personaCreada.emailFrom = 'do.not.reply@localhost'
grails.plugins.springsecurity.ui.personaCreada.emailSubject = 'Cuenta de acceso-SIFS'	

