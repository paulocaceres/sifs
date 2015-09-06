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
		
	}

//		def password = 'password'
//		def rolCursante = null // ROL CURSANTE
//
//				
//		if (!Persona.count()) {
//			
//
//		
//			def admin = new Persona(	documentoNumero: 	'23113867',
//										password: 			password,
//										nombre: 			'Esteban',
//										apellido: 			'Gomez',
//										mail: 				'persona@mail.com',
//										enabled: 			true, 
//										accountExpired: 	false, 
//										accountLocked: 		false, 
//										passwordExpired: 	false)
//				.save(flush: true, insert: true)
//
//
//			
//			def rolAdmin  = new Rol(authority: 'ROLE_ADMIN', name: 'Admin').save(flush: true, insert: true)
//			rolCursante = new Rol(authority: 'ROLE_CURSANTE', name: 'Cursante').save(flush: true, insert: true)
//			def rolSupervisor = new Rol(authority: 'ROLE_SUPERVISOR', name: 'Supervisor').save(flush: true, insert: true)
//			def rolFormador = new Rol(authority: 'ROLE_FORMADOR', name: 'Formador').save(flush: true, insert: true)
//			def roldisenador = new Rol(authority: 'ROLE_DISENADOR', name: 'Dise�ador').save(flush: true, insert: true)
//				
//			/*create the first user role map*/
//			PersonaRol.create(admin, rolAdmin, true)
//
//		}
//
//			Zona admin_zona = new Zona(nombre: 'Flores', numero:'2').save();
//		
//			Distrito admin_distrito = new Distrito(nombre: 'Distrito 1', zona: admin_zona).save();
//			Distrito admin_distrito1 = new Distrito(nombre: 'Distrito 2', zona: admin_zona).save();
//			Distrito admin_distrito2 = new Distrito(nombre: 'Distrito 3', zona: admin_zona).save();
//			
//			Zona admin_zona1 = new Zona(nombre: 'Centro', numero:'1').save();
//			
//				Distrito admin_distrito3 = new Distrito(nombre: 'Distrito 1', zona: admin_zona1).save();
//				Distrito admin_distrito4 = new Distrito(nombre: 'Distrito 2', zona: admin_zona1).save();
//				Distrito admin_distrito5 = new Distrito(nombre: 'Distrito 3', zona: admin_zona1).save();
//				Distrito admin_distrito8 = new Distrito(nombre: 'Distrito 4', zona: admin_zona1).save();
//				
//				Zona admin_zona2 = new Zona(nombre: 'Devoto', numero:'3').save();
//				
//					Distrito admin_distrito6 = new Distrito(nombre: 'Distrito 1', zona: admin_zona2).save();
//					Distrito admin_distrito7 = new Distrito(nombre: 'Distrito 2', zona: admin_zona2).save();
//			
//				
//		new Provincia(descripcion: 'CABA').save()
//		new Provincia(descripcion: 'Buenos Aires').save()
//		new Provincia(descripcion: 'Catamarca').save()
//		new Provincia(descripcion: 'Chaco').save()
//		new Provincia(descripcion: 'Chubut').save()
//		new Provincia(descripcion: 'Corrientes').save()
//		new Provincia(descripcion: 'Córdoba').save()
//		new Provincia(descripcion: 'Entre Ríos').save()
//		new Provincia(descripcion: 'Formosa').save()
//		new Provincia(descripcion: 'Jujuy').save()
//		new Provincia(descripcion: 'La Pampa').save()
//		new Provincia(descripcion: 'La Rioja').save()
//		new Provincia(descripcion: 'Mendoza').save()
//		new Provincia(descripcion: 'Misiones').save()
//		new Provincia(descripcion: 'Neuquén').save()
//		new Provincia(descripcion: 'Río Negro').save()
//		new Provincia(descripcion: 'Salta').save()
//		new Provincia(descripcion: 'San Juan').save()
//		new Provincia(descripcion: 'San Luis').save()
//		new Provincia(descripcion: 'Santa Cruz').save()
//		new Provincia(descripcion: 'Santa Fe').save()
//		new Provincia(descripcion: 'Santiago del Estero').save()
//		new Provincia(descripcion: 'Tierra del Fuego, Antártida e Islas del Atlántico Sur').save()
//		new Provincia(descripcion: 'Tucumán').save()
//		
//
//		
//		Nivel nivel1 = new Nivel(nombre: 'Zonal')
//		nivel1.save(flush: true, insert: true)
//		
//		Nivel nivel2 = new Nivel(nombre: 'Nacional')
//		nivel2.save(flush: true, insert: true)
//		
//		Curso curso1 = new Curso(nombre:'Modulo 1 A', descripcion:'Modulo 1 A', nivel: nivel1).save(flush: true, insert: true)
//		//
//		Curso curso2 = new Curso(nombre:'Modulo 1 B', descripcion:'Modulo 1 B', nivel: nivel1).save(flush: true, insert: true)
//		//
//		Curso curso3 = new Curso(nombre:'Modulo 2', descripcion:'Modulo 2', nivel: nivel1).save(flush: true, insert: true)
//		//
//		Curso curso4 = new Curso(nombre:'Modulo 3', descripcion:'Modulo 3', nivel: nivel1).save(flush: true, insert: true)
//		//
//		Curso curso5 = new Curso(nombre:'Modulo 4', descripcion:'Modulo 4', nivel: nivel2).save(flush: true, insert: true)
//		//
//		Curso curso6 = new Curso(nombre:'Modulo 5', descripcion:'Modulo 5', nivel: nivel2).save(flush: true, insert: true)
//		//
//		Curso curso7 = new Curso(nombre:'Modulo 6 G', descripcion:'Modulo 6 G', nivel: nivel2).save(flush: true, insert: true)
//		//
//		Curso curso8 = new Curso(nombre:'Modulo 6 E', descripcion:'Modulo 6 E', nivel: nivel2).save(flush: true, insert: true)
//		//
//		//
//		Plan plan1 = new Plan(nombre: 'plan1', descripcion: 'plan1', validez: new Date());
//		plan1.save(flush: true, insert: true);
//
//		PlanCurso planCurso1 = new PlanCurso(plan: plan1, curso: curso1);
//		planCurso1.save(flush: true, insert: true);
//		planCurso1.addToCorrelativas(curso2);
//		
//		PlanCurso planCurso2 = new PlanCurso(plan: plan1, curso: curso2);
//		planCurso2.save(flush: true, insert: true);
//		
//		PlanCurso planCurso3 = new PlanCurso(plan: plan1, curso: curso3);
//		planCurso3.save(flush: true, insert: true);
//		
//		PlanCurso planCurso4 = new PlanCurso(plan: plan1, curso: curso4);
//		planCurso4.save(flush: true, insert: true);
//		
//		PlanCurso planCurso5 = new PlanCurso(plan: plan1, curso: curso5);
//		planCurso5.save(flush: true, insert: true);
//		
//		PlanCurso planCurso6 = new PlanCurso(plan: plan1, curso: curso6);
//		planCurso6.save(flush: true, insert: true);
//		
//		PlanCurso planCurso7 = new PlanCurso(plan: plan1, curso: curso7);
//		planCurso7.save(flush: true, insert: true);
//		
//		PlanCurso planCurso8 = new PlanCurso(plan: plan1, curso: curso8);
//		planCurso8.save(flush: true, insert: true);
//		
//		
//		Recurso r1 = new Recurso(nombre: "Proyector", cantidad:1).save(flush:true, insert:true);
//		Recurso r2 = new Recurso(nombre: "Aula 100", cantidad:1).save(flush:true, insert:true);
//		Recurso r3 = new Recurso(nombre: "Aula 200", cantidad:1).save(flush:true, insert:true);
//		Recurso r4 = new Recurso(nombre: "Aula 300", cantidad:1).save(flush:true, insert:true);
//		
//		DictadoStatus abierto = new DictadoStatus(nombre:"ABIERTO");
//		abierto.save(flush: true, insert: true);
//		DictadoStatus cerrado = new DictadoStatus(nombre:"CERRADO");
//		cerrado.save(flush: true, insert: true);
//		
//		
//		Calificacion aprobado = new Calificacion(nombre: 'APROBADO')
//		aprobado.save(flush: true, insert: true)
//		
//		Calificacion desaprobado = new Calificacion(nombre: 'DESAPROBADO')
//		desaprobado.save(flush: true, insert: true)
//		
//	}


	def destroy = {
	}


}
