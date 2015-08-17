import ar.org.scouts.sifs.Curso
import ar.org.scouts.sifs.Dictado
import ar.org.scouts.sifs.Direccion
import ar.org.scouts.sifs.Distrito
import ar.org.scouts.sifs.Grupo
import ar.org.scouts.sifs.Nivel
import ar.org.scouts.sifs.Persona
import ar.org.scouts.sifs.Plan
import ar.org.scouts.sifs.PlanCurso
import ar.org.scouts.sifs.Provincia
import ar.org.scouts.sifs.Zona
import ar.org.scouts.sifs.security.PersonaRol
import ar.org.scouts.sifs.security.Rol
import ar.org.scouts.sifs.Recurso
import ar.org.scouts.sifs.DictadoStatus
import ar.org.scouts.sifs.Calificacion

class BootStrap {

	transient springSecurityService

	def init = { servletContext ->

		def password = 'password'
		def rolCursante = null // ROL CURSANTE

				
		if (!Persona.count()) {
			
//
//						
//			Zona admin_zona = new Zona(nombre: 'admin_zona', numero:'1');
//			admin_zona.save();
//	
//			Distrito admin_distrito = new Distrito(nombre: 'admin_Distrito', zona: admin_zona);
//			admin_distrito.save();
//			
//			Grupo admin_Grupo = new Grupo(nombre: 'admin_Grupo', numero:'0', distrito: admin_distrito)
//			admin_Grupo.save();
		
			def admin = new Persona(	documentoNumero: 	'23113867',
										password: 			password,
										nombre: 			'Esteban',
										apellido: 			'Gomez',
										mail: 				'persona@mail.com',
										enabled: 			true, 
										accountExpired: 	false, 
										accountLocked: 		false, 
										passwordExpired: 	false)
				.save(flush: true, insert: true)


			
			def rolAdmin  = new Rol(authority: 'ROLE_ADMIN', name: 'Admin').save(flush: true, insert: true)
			rolCursante = new Rol(authority: 'ROLE_CURSANTE', name: 'Cursante').save(flush: true, insert: true)
			def rolSupervisor = new Rol(authority: 'ROLE_SUPERVISOR', name: 'Supervisor').save(flush: true, insert: true)
			def rolFormador = new Rol(authority: 'ROLE_FORMADOR', name: 'Formador').save(flush: true, insert: true)

				
			/*create the first user role map*/
			PersonaRol.create(admin, rolAdmin, true)

		}
//		
//
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
		

		
		Nivel nivel1 = new Nivel(nombre: 'Zonal')
		nivel1.save(flush: true, insert: true)
		
		Nivel nivel2 = new Nivel(nombre: 'Nacional')
		nivel2.save(flush: true, insert: true)
		
		
		Recurso r1 = new Recurso(nombre: "Proyector", cantidad:1).save(flush:true, insert:true);
		Recurso r2 = new Recurso(nombre: "Aula 100", cantidad:1).save(flush:true, insert:true);
		Recurso r3 = new Recurso(nombre: "Aula 200", cantidad:1).save(flush:true, insert:true);
		Recurso r4 = new Recurso(nombre: "Aula 300", cantidad:1).save(flush:true, insert:true);
		
		DictadoStatus abierto = new DictadoStatus(nombre:"ABIERTO");
		abierto.save(flush: true, insert: true);
		DictadoStatus cerrado = new DictadoStatus(nombre:"CERRADO");
		cerrado.save(flush: true, insert: true);
		
		
		Calificacion aprobado = new Calificacion(nombre: 'APROBADO')
		aprobado.save(flush: true, insert: true)
		
		Calificacion desaprobado = new Calificacion(nombre: 'DESAPROBADO')
		desaprobado.save(flush: true, insert: true)
		
	}


	def destroy = {
	}


}
