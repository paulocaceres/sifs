import ar.org.scouts.sifs.Distrito
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
		new Provincia(descripcion: 'Córdoba').save()
		new Provincia(descripcion: 'Entre Ríos').save()
		new Provincia(descripcion: 'Formosa').save()
		new Provincia(descripcion: 'Jujuy').save()
		new Provincia(descripcion: 'La Pampa').save()
		new Provincia(descripcion: 'La Rioja').save()
		new Provincia(descripcion: 'Mendoza').save()
		new Provincia(descripcion: 'Misiones').save()
		new Provincia(descripcion: 'Neuquén').save()
		new Provincia(descripcion: 'Río Negro').save()
		new Provincia(descripcion: 'Salta').save()
		new Provincia(descripcion: 'San Juan').save()
		new Provincia(descripcion: 'San Luis').save()
		new Provincia(descripcion: 'Santa Cruz').save()
		new Provincia(descripcion: 'Santa Fe').save()
		new Provincia(descripcion: 'Santiago del Estero').save()
		new Provincia(descripcion: 'Tierra del Fuego, Antártida e Islas del Atlántico Sur').save()
		new Provincia(descripcion: 'Tucumán').save()
		
		Zona zona
		zona = new Zona(nombre: 'Zona01')
		zona.addToDistritos(new Distrito(nombre: 'Distrito01a'))
		zona.addToDistritos(new Distrito(nombre: 'Distrito01b'))
		zona.addToDistritos(new Distrito(nombre: 'Distrito01c'))
		zona.save()
		
		zona = new Zona(nombre: 'Zona02')
		zona.addToDistritos(new Distrito(nombre: 'Distrito02a'))
		zona.addToDistritos(new Distrito(nombre: 'Distrito02b'))
		zona.addToDistritos(new Distrito(nombre: 'Distrito02c'))
		zona.save()

	}

	def destroy = {
	}

}
