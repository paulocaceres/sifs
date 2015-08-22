/**
 * 
 */
package ar.org.scouts.sifs

import org.codehaus.groovy.grails.plugins.springsecurity.GormUserDetailsService
//org.codehaus.groovy.grails.plugins.springsecurity
import org.codehaus.groovy.grails.plugins.springsecurity.GormUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * @author Pcaceres
 *
 */
class SifsUserDetailsService extends GormUserDetailsService {
	
	protected UserDetails createUserDetails(user, Collection authorities) {
		new SifsUserDetails((GrailsUser) super.createUserDetails(user, authorities), user.documentoNumero)
	}

}
