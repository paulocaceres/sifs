/**
 * 
 */
package ar.org.scouts.sifs

import org.springframework.security.authentication.dao.ReflectionSaltSource
import org.springframework.security.core.userdetails.UserDetails

/**
 * @author Pcaceres
 *
 */
class SifsSaltSource extends ReflectionSaltSource {
	
	Object getSalt(UserDetails user) {
		user[userPropertyToUse]
	}

}
