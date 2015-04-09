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
					//direccion: 			unaDireccion,
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

			Persona formador1 = new Persona(	documentoNumero: 	'33333333',
				password: 			password,
				nombre: 			'Carlos',
				apellido: 			'Tevez',
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
			PersonaRol.create(formador1, rol3, true)
			PersonaRol.create(dnf, rol4, true)
			PersonaRol.create(dnra, rol5, true)
			
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
		
//		Zona zona
//		zona = new Zona(nombre: 'Zona01', numero:1)
//		zona.addToDistritos(new Distrito(nombre: 'Distrito01a'))
//		zona.addToDistritos(new Distrito(nombre: 'Distrito01b'))
//		zona.addToDistritos(new Distrito(nombre: 'Distrito01c'))
//		zona.save()
//		
//		zona = new Zona(nombre: 'Zona02', numero:2)
//		zona.addToDistritos(new Distrito(nombre: 'Distrito02a'))
//		zona.addToDistritos(new Distrito(nombre: 'Distrito02b'))
//		zona.addToDistritos(new Distrito(nombre: 'Distrito02c'))
//		zona.save()
		

		Grupo grupo01 = new Grupo(nombre: 'Grupo01', numero:1)
		grupo01.save()
		Grupo grupo02 = new Grupo(nombre: 'Grupo02', numero:2)
		grupo02.save()
		Grupo grupo03 = new Grupo(nombre: 'Grupo03', numero:3)
		grupo03.save()
		Grupo grupo04 = new Grupo(nombre: 'Grupo04', numero:4)
		grupo04.save()
		Grupo grupo05 = new Grupo(nombre: 'Grupo05', numero:5)
		grupo05.save()
		Grupo grupo06 = new Grupo(nombre: 'Grupo06', numero:6)
		grupo06.save()
		Grupo grupo07 = new Grupo(nombre: 'Grupo07', numero:7)
		grupo07.save()
		Grupo grupo08 = new Grupo(nombre: 'Grupo08', numero:8)
		grupo08.save()
		Grupo grupo09 = new Grupo(nombre: 'Grupo09', numero:9)
		grupo09.save()
		Grupo grupo10 = new Grupo(nombre: 'Grupo10', numero:10)
		grupo10.save()
		Grupo grupo11 = new Grupo(nombre: 'Grupo11', numero:11)
		grupo11.save()
		Grupo grupo12 = new Grupo(nombre: 'Grupo12', numero:12)
		grupo12.save()
		Grupo grupo13 = new Grupo(nombre: 'Grupo13', numero:13)
		grupo13.save()
		Grupo grupo14 = new Grupo(nombre: 'Grupo14', numero:14)
		grupo14.save()
		Grupo grupo15 = new Grupo(nombre: 'Grupo15', numero:15)
		grupo15.save()
		Grupo grupo16 = new Grupo(nombre: 'Grupo16', numero:16)
		grupo16.save()
		Grupo grupo17 = new Grupo(nombre: 'Grupo17', numero:17)
		grupo17.save()
		Grupo grupo18 = new Grupo(nombre: 'Grupo18', numero:18)
		grupo18.save()
		Grupo grupo19 = new Grupo(nombre: 'Grupo19', numero:19)
		grupo19.save()
		Grupo grupo20 = new Grupo(nombre: 'Grupo20', numero:20)
		grupo20.save()
		Grupo grupo21 = new Grupo(nombre: 'Grupo21', numero:21)
		grupo21.save()
		Grupo grupo22 = new Grupo(nombre: 'Grupo22', numero:22)
		grupo22.save()
		Grupo grupo23 = new Grupo(nombre: 'Grupo23', numero:23)
		grupo23.save()
		Grupo grupo24 = new Grupo(nombre: 'Grupo24', numero:24)
		grupo24.save()
		Grupo grupo25 = new Grupo(nombre: 'Grupo25', numero:25)
		grupo25.save()
		Grupo grupo26 = new Grupo(nombre: 'Grupo26', numero:26)
		grupo26.save()
		Grupo grupo27 = new Grupo(nombre: 'Grupo27', numero:27)
		grupo27.save()
		Grupo grupo28 = new Grupo(nombre: 'Grupo28', numero:28)
		grupo28.save()
		Grupo grupo29 = new Grupo(nombre: 'Grupo29', numero:29)
		grupo29.save()

		
		
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
		

		Zona zona01 = new Zona(nombre: 'Zona01', numero:1)
		zona01.addToDistritos(distrito01)
		zona01.addToDistritos(distrito02)
		zona01.addToDistritos(distrito03)
		zona01.save()
		
		Zona zona02 = new Zona(nombre: 'Zona02', numero:2)
		zona02.addToDistritos(distrito04)
		zona02.addToDistritos(distrito05)
		zona02.addToDistritos(distrito06)
		zona02.save()
		
		Zona zona03 = new Zona(nombre: 'Zona03', numero:3)
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
		
		
		Plan plan1 = new Plan(nombre: 'plan1', descripcion: 'plan1', validez: new Date());
		plan1.save(flush: true, insert: true);
		Plan plan2 = new Plan(nombre: 'plan2', descripcion: 'plan2', validez: new Date());
		plan2.save(flush: true, insert: true);

		PlanCurso planCurso1 = new PlanCurso(plan: plan1, curso: curso1); 
		planCurso1.save(flush: true, insert: true);
		planCurso1.addToCorrelativas(curso2);
		
		PlanCurso planCurso2 = new PlanCurso(plan: plan2, curso: curso2);
		planCurso2.save(flush: true, insert: true);
		
		PlanCurso planCurso3 = new PlanCurso(plan: plan2, curso: curso3);
		planCurso3.save(flush: true, insert: true);
		
		PlanCurso planCurso4 = new PlanCurso(plan: plan2, curso: curso4);
		planCurso4.save(flush: true, insert: true);
		
		PlanCurso planCurso5 = new PlanCurso(plan: plan2, curso: curso5);
		planCurso5.save(flush: true, insert: true);
		
		PlanCurso planCurso6 = new PlanCurso(plan: plan2, curso: curso6);
		planCurso6.save(flush: true, insert: true);
		
		PlanCurso planCurso7 = new PlanCurso(plan: plan2, curso: curso7);
		planCurso7.save(flush: true, insert: true);
		
		Recurso r1 = new Recurso(nombre: "Proyector", cantidad:1).save(flush:true, insert:true);
		Recurso r2 = new Recurso(nombre: "Aula 100", cantidad:1).save(flush:true, insert:true);
		Recurso r3 = new Recurso(nombre: "Aula 200", cantidad:1).save(flush:true, insert:true);
		Recurso r4 = new Recurso(nombre: "Aula 300", cantidad:1).save(flush:true, insert:true);
		
		Persona forma = Persona.findByNombre('Carlos');
		Dictado dictado1 = new Dictado(nombre: "Jueves 9hs", formador: forma, zona: zona01, curso: curso2, fecha: new Date()+10, cupo: 10);
		dictado1.addToRecursos(r1)
		dictado1.save(flush: true, insert: true);
		
		Dictado dictado2 = new Dictado(nombre: "Miercoles 15hs", formador: forma, zona: zona02, curso: curso2, fecha: new Date()+5, cupo: 0);
		dictado2.addToRecursos(r2)
		dictado2.save(flush: true, insert: true);
		
		Dictado dictado3 = new Dictado(nombre: "Sabados 10hs", formador: forma, zona: zona01, curso: curso3, fecha: new Date(), cupo: 5);
		dictado3.addToRecursos(r3)
		dictado3.save(flush: true, insert: true);
		
		Dictado dictado4 = new Dictado(nombre: "Viernes 19hs", formador: forma, zona: zona03, curso: curso3, fecha: new Date()+10, cupo: 7);
		dictado4.addToRecursos(r4)
		dictado4.save(flush: true, insert: true);
		
	}


	def destroy = {
	}


}
