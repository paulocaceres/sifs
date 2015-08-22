/**
 * 
 */
package ar.org.scouts.sifs

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

/**
 * @author Pcaceres
 *
 */
class SifsUserDetails extends GrailsUser {
	
	public final String salt
	
		SifsUserDetails(GrailsUser base, String salt) {
			super(base.username, base.password, base.enabled,
				base.accountNonExpired, base.credentialsNonExpired, base.accountNonLocked,
				base.authorities, base.id)
	
			this.salt = salt;
		}

}
