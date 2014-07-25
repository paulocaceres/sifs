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
		
		new Provincia(descripcion: 'Buenos Aires').save()
		new Provincia(descripcion: 'Catamarca').save()
		new Provincia(descripcion: 'Chaco').save()
		new Provincia(descripcion: 'Chubut').save()
		new Provincia(descripcion: 'Corrientes').save()
		new Provincia(descripcion: 'C�rdoba').save()
		new Provincia(descripcion: 'Entre R�os').save()
		new Provincia(descripcion: 'Formosa').save()
		new Provincia(descripcion: 'Jujuy').save()
		new Provincia(descripcion: 'La Pampa').save()
		new Provincia(descripcion: 'La Rioja').save()
		new Provincia(descripcion: 'Mendoza').save()
		new Provincia(descripcion: 'Misiones').save()
		new Provincia(descripcion: 'Neuqu�n').save()
		new Provincia(descripcion: 'R�o Negro').save()
		new Provincia(descripcion: 'Salta').save()
		new Provincia(descripcion: 'San Juan').save()
		new Provincia(descripcion: 'San Luis').save()
		new Provincia(descripcion: 'Santa Cruz').save()
		new Provincia(descripcion: 'Santa Fe').save()
		new Provincia(descripcion: 'Santiago del Estero').save()
		new Provincia(descripcion: 'Tierra del Fuego, Ant�rtida e Islas del Atl�ntico Sur').save()
		new Provincia(descripcion: 'Tucum�n').save()
		
		new Zona(nombre: 'I').save()
		new Zona(nombre: 'II').save()
		new Zona(nombre: 'III').save()
		new Zona(nombre: 'IV').save()
		new Zona(nombre: 'V').save()
		new Zona(nombre: 'VI').save()
		new Zona(nombre: 'VII').save()
		new Zona(nombre: 'VIII').save()
		new Zona(nombre: 'IX').save()
		new Zona(nombre: 'X').save()
		
	}

	def destroy = {
	}

}
