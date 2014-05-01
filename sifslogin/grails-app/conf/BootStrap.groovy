import com.sifs.SifsUser
import com.sifs.SifsUserSifsRole
import com.sifs.SifsRole

class BootStrap {

    transient springSecurityService
	
    def init = { servletContext ->
		
		if(!SifsUser.count()){
			/*The default password is 'password'*/
			def password = 'password'
			def user = new SifsUser(username : 'paulito', password:password,enabled:true,
				accountExpired : false , accountLocked : false ,passwordExpired : false).save(flush: true, insert: true)
			def role = new SifsRole(authority : 'ROLE_USER').save(flush: true, insert: true)
			
			/*create the first user role map*/
			SifsUserSifsRole.create user , role , true
		}
		
    }
    def destroy = {
    }
}
