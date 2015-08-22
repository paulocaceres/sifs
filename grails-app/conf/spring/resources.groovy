//import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

beans = {
    userDetailsService(ar.org.scouts.sifs.SifsUserDetailsService) {
        //sessionFactory = ref('sessionFactory')
        //transactionManager = ref('transactionManager')
		grailsApplication = ref('grailsApplication')
    }

    saltSource(ar.org.scouts.sifs.SifsSaltSource) {
		def conf = SpringSecurityUtils.securityConfig
        userPropertyToUse = conf.dao.reflectionSaltSourceProperty
    }
}