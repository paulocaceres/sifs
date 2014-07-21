import ar.org.scouts.sifs.Provincia
import ar.org.scouts.sifs.Zona
import ar.org.scouts.sifs.security.SifsRole
import ar.org.scouts.sifs.security.SifsUser
import ar.org.scouts.sifs.security.SifsUserSifsRole

class BootStrap {

	transient springSecurityService

	def init = { servletContext ->

		if (!SifsUser.count()) {
			/*The default password is 'password'*/
			def password = 'password'
			def user = new SifsUser(username: 'paulito', password: password, enabled: true, accountExpired: false , accountLocked: false, passwordExpired: false)
				.save(flush: true, insert: true)
			def role = new SifsRole(authority: 'ROLE_USER')
				.save(flush: true, insert: true)

			/*create the first user role map*/
			SifsUserSifsRole.create user , role , true
		}
		
		def provincia = new Provincia(descripcion: 'Buenos Aires').save()

		def zona = new Zona(nombre: '3').save()
		
	}

	def destroy = {
	}

}
