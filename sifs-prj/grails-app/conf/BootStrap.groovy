import ar.org.scouts.sifs.Curso
import ar.org.scouts.sifs.Direccion
import ar.org.scouts.sifs.Distrito
import ar.org.scouts.sifs.Grupo
import ar.org.scouts.sifs.Nivel
import ar.org.scouts.sifs.Persona
import ar.org.scouts.sifs.Provincia
import ar.org.scouts.sifs.Zona
import ar.org.scouts.sifs.security.PersonaRol
import ar.org.scouts.sifs.security.Rol

class BootStrap {

	transient springSecurityService

	def init = { servletContext ->

		if (!Persona.count()) {
			
			def unaProvincia = new Provincia(descripcion: 'persona.direccion.provincia.descripcion')
			unaProvincia.save(flush: true, insert: true)
			
			def unaDireccion = new Direccion(	calle: 'persona.direccion.calle', 
														numero: 'persona.direccion.numero', 
														adicional: 'persona.direccion.adicional', 
														codigoPostal: 'persona.direccion.codigoPostal', 
														ciudad: 'persona.direccion.ciudad', 
														provincia: unaProvincia)
			unaDireccion.save(flush: true, insert: true)
						
			def password = 'password'
			def user = new Persona(	documentoNumero: 	'25227067',
											password: 			password,
											nombre: 			'persona.nombre',
											apellido: 			'persona.apellido',
											mail: 				'persona.mail',
											direccion: 			unaDireccion,
											//zona: 				new Zona(nombre: 'persona.zona.nombre'),
											//superior: 			null,
											enabled: 			true, 
											accountExpired: 	false, 
											accountLocked: 		false, 
											passwordExpired: 	false)
				.save(flush: true, insert: true)
			
			def cursante = new Persona(	documentoNumero: 	'11111111',
					password: 			password,
					nombre: 			'Cursante',
					apellido: 			'Apellido',
					mail: 				'XXX@gmail.com',
					direccion: 			unaDireccion,
					enabled: 			true,
					accountExpired: 	false,
					accountLocked: 		false,
					passwordExpired: 	false)
			.save(flush: true, insert: true)

			def supervisor = new Persona(	documentoNumero: 	'22222222',
				password: 			password,
				nombre: 			'Supervisor',
				apellido: 			'Apellido',
				mail: 				'XXX@gmail.com',
				direccion: 			unaDireccion,
				enabled: 			true,
				accountExpired: 	false,
				accountLocked: 		false,
				passwordExpired: 	false)
			.save(flush: true, insert: true)

			def formador = new Persona(	documentoNumero: 	'33333333',
				password: 			password,
				nombre: 			'Formador',
				apellido: 			'Apellido',
				mail: 				'XXX@gmail.com',
				direccion: 			unaDireccion,
				enabled: 			true,
				accountExpired: 	false,
				accountLocked: 		false,
				passwordExpired: 	false)
			.save(flush: true, insert: true)
			
			def dnf = new Persona(	documentoNumero: 	'44444444',
				password: 			password,
				nombre: 			'Dir. Nac. Formacion',
				apellido: 			'Apellido',
				mail: 				'XXX@gmail.com',
				direccion: 			unaDireccion,
				enabled: 			true,
				accountExpired: 	false,
				accountLocked: 		false,
				passwordExpired: 	false)
			.save(flush: true, insert: true)
			
			def dnra = new Persona(	documentoNumero: 	'55555555',
				password: 			password,
				nombre: 			'Dir. Nac. Recursos',
				apellido: 			'Apellido',
				mail: 				'XXX@gmail.com',
				direccion: 			unaDireccion,
				enabled: 			true,
				accountExpired: 	false,
				accountLocked: 		false,
				passwordExpired: 	false)
			.save(flush: true, insert: true)
			
			def rol  = new Rol(authority: 'ROLE_ADMIN').save(flush: true, insert: true)
			def rol1 = new Rol(authority: 'ROLE_CURSANTE').save(flush: true, insert: true)
			def rol2 = new Rol(authority: 'ROLE_SUPERVISOR').save(flush: true, insert: true)
			def rol3 = new Rol(authority: 'ROLE_FORMADOR').save(flush: true, insert: true)
			def rol4 = new Rol(authority: 'ROLE_DNF').save(flush: true, insert: true)
			def rol5 = new Rol(authority: 'ROLE_DNRA').save(flush: true, insert: true)
				
			/*create the first user role map*/
			PersonaRol.create(user, rol, true)
			PersonaRol.create(cursante, rol1, true)
			PersonaRol.create(supervisor, rol2, true)
			PersonaRol.create(formador, rol3, true)
			PersonaRol.create(dnf, rol4, true)
			PersonaRol.create(dnra, rol5, true)
			
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
		
//		Zona zona
//		zona = new Zona(nombre: 'Zona01')
//		zona.addToDistritos(new Distrito(nombre: 'Distrito01a'))
//		zona.addToDistritos(new Distrito(nombre: 'Distrito01b'))
//		zona.addToDistritos(new Distrito(nombre: 'Distrito01c'))
//		zona.save()
//		
//		zona = new Zona(nombre: 'Zona02')
//		zona.addToDistritos(new Distrito(nombre: 'Distrito02a'))
//		zona.addToDistritos(new Distrito(nombre: 'Distrito02b'))
//		zona.addToDistritos(new Distrito(nombre: 'Distrito02c'))
//		zona.save()
		

		Grupo grupo01 = new Grupo(nombre: 'Grupo01')
		grupo01.save()
		Grupo grupo02 = new Grupo(nombre: 'Grupo02')
		grupo02.save()
		Grupo grupo03 = new Grupo(nombre: 'Grupo03')
		grupo03.save()
		Grupo grupo04 = new Grupo(nombre: 'Grupo04')
		grupo04.save()
		Grupo grupo05 = new Grupo(nombre: 'Grupo05')
		grupo05.save()
		Grupo grupo06 = new Grupo(nombre: 'Grupo06')
		grupo06.save()
		Grupo grupo07 = new Grupo(nombre: 'Grupo07')
		grupo07.save()
		Grupo grupo08 = new Grupo(nombre: 'Grupo08')
		grupo08.save()
		Grupo grupo09 = new Grupo(nombre: 'Grupo09')
		grupo09.save()
		Grupo grupo10 = new Grupo(nombre: 'Grupo10')
		grupo10.save()
		Grupo grupo11 = new Grupo(nombre: 'Grupo11')
		grupo11.save()
		Grupo grupo12 = new Grupo(nombre: 'Grupo12')
		grupo12.save()
		Grupo grupo13 = new Grupo(nombre: 'Grupo13')
		grupo13.save()
		Grupo grupo14 = new Grupo(nombre: 'Grupo14')
		grupo14.save()
		Grupo grupo15 = new Grupo(nombre: 'Grupo15')
		grupo15.save()
		Grupo grupo16 = new Grupo(nombre: 'Grupo16')
		grupo16.save()
		Grupo grupo17 = new Grupo(nombre: 'Grupo17')
		grupo17.save()
		Grupo grupo18 = new Grupo(nombre: 'Grupo18')
		grupo18.save()
		Grupo grupo19 = new Grupo(nombre: 'Grupo19')
		grupo19.save()
		Grupo grupo20 = new Grupo(nombre: 'Grupo20')
		grupo20.save()
		Grupo grupo21 = new Grupo(nombre: 'Grupo21')
		grupo21.save()
		Grupo grupo22 = new Grupo(nombre: 'Grupo22')
		grupo22.save()
		Grupo grupo23 = new Grupo(nombre: 'Grupo23')
		grupo23.save()
		Grupo grupo24 = new Grupo(nombre: 'Grupo24')
		grupo24.save()
		Grupo grupo25 = new Grupo(nombre: 'Grupo25')
		grupo25.save()
		Grupo grupo26 = new Grupo(nombre: 'Grupo26')
		grupo26.save()
		Grupo grupo27 = new Grupo(nombre: 'Grupo27')
		grupo27.save()
		
		Distrito distrito01 = new Distrito(nombre: 'Distrito01')
		distrito01.addToGrupos(grupo01)
		distrito01.addToGrupos(grupo02)
		distrito01.addToGrupos(grupo03)
		distrito01.save()
		
		
		Distrito distrito02 = new Distrito(nombre: 'Distrito02')
		distrito02.addToGrupos(grupo04)
		distrito02.addToGrupos(grupo05)
		distrito02.addToGrupos(grupo06)
		distrito02.save()
		
		Distrito distrito03 = new Distrito(nombre: 'Distrito03')
		distrito03.addToGrupos(grupo07)
		distrito03.addToGrupos(grupo08)
		distrito03.addToGrupos(grupo09)
		distrito03.save()
		
		Distrito distrito04 = new Distrito(nombre: 'Distrito04')
		distrito04.addToGrupos(grupo10)
		distrito04.addToGrupos(grupo11)
		distrito04.addToGrupos(grupo12)
		distrito04.save()
		
		Distrito distrito05 = new Distrito(nombre: 'Distrito05')
		distrito05.addToGrupos(grupo13)
		distrito05.addToGrupos(grupo14)
		distrito05.addToGrupos(grupo15)
		distrito05.save()
		
		Distrito distrito06 = new Distrito(nombre: 'Distrito06')
		distrito06.addToGrupos(grupo16)
		distrito06.addToGrupos(grupo17)
		distrito06.addToGrupos(grupo18)
		distrito06.save()
		
		Distrito distrito07 = new Distrito(nombre: 'Distrito07')
		distrito07.addToGrupos(grupo19)
		distrito07.addToGrupos(grupo20)
		distrito07.addToGrupos(grupo21)
		distrito07.save()
		
		Distrito distrito08 = new Distrito(nombre: 'Distrito08')
		distrito08.addToGrupos(grupo22)
		distrito08.addToGrupos(grupo23)
		distrito08.addToGrupos(grupo24)
		distrito08.save()
		
		Distrito distrito09 = new Distrito(nombre: 'Distrito09')
		distrito09.addToGrupos(grupo25)
		distrito09.addToGrupos(grupo26)
		distrito09.addToGrupos(grupo27)
		distrito09.save()
		

		Zona zona01 = new Zona(nombre: 'Zona01')
		zona01.addToDistritos(distrito01)
		zona01.addToDistritos(distrito02)
		zona01.addToDistritos(distrito03)
		zona01.save()
		
		Zona zona02 = new Zona(nombre: 'Zona02')
		zona02.addToDistritos(distrito04)
		zona02.addToDistritos(distrito05)
		zona02.addToDistritos(distrito06)
		zona02.save()
		
		Zona zona03 = new Zona(nombre: 'Zona03')
		zona03.addToDistritos(distrito07)
		zona03.addToDistritos(distrito08)
		zona03.addToDistritos(distrito09)
		zona03.save()
		
		Nivel nivel1 = new Nivel(nombre: 'Zonal')
		nivel1.save(flush: true, insert: true)
		
		Nivel nivel2 = new Nivel(nombre: 'Nacional')
		nivel2.save(flush: true, insert: true)
		
		Curso curso1 = new Curso(nombre:'Modulo 1 A', descripcion:'Modulo 1 A', nivel: nivel1).save(flush: true, insert: true)
		
		Curso curso2 = new Curso(nombre:'Modulo 1 B', descripcion:'Modulo 1 B', nivel: nivel1).save(flush: true, insert: true)
		
		Curso curso3 = new Curso(nombre:'Modulo 2', descripcion:'Modulo 2', nivel: nivel1).save(flush: true, insert: true)
		
		Curso curso4 = new Curso(nombre:'Modulo 3', descripcion:'Modulo 3', nivel: nivel1).save(flush: true, insert: true)
		
		Curso curso5 = new Curso(nombre:'Modulo 4', descripcion:'Modulo 4', nivel: nivel2).save(flush: true, insert: true)
		
		Curso curso6 = new Curso(nombre:'Modulo 5', descripcion:'Modulo 5', nivel: nivel2).save(flush: true, insert: true)
		
		Curso curso7 = new Curso(nombre:'Modulo 6 G', descripcion:'Modulo 6 G', nivel: nivel2).save(flush: true, insert: true)
		
		Curso curso8 = new Curso(nombre:'Modulo 6 E', descripcion:'Modulo 6 E', nivel: nivel2).save(flush: true, insert: true)
	
		
	}


	def destroy = {
	}


}
