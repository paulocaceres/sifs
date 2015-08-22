import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

beans = {
    userDetailsService(ar.org.scouts.sifs.SifsUserDetailsService) {
		grailsApplication = ref('grailsApplication')
    }

    saltSource(ar.org.scouts.sifs.SifsSaltSource) {
		def conf = SpringSecurityUtils.securityConfig
        userPropertyToUse = conf.dao.reflectionSaltSourceProperty
    }
}