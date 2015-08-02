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
		def cursante_formador = null;
				
		if (!Persona.count()) {
			
			def unaProvincia = new Provincia(descripcion: 'Uruguay')
			unaProvincia.save(flush: true, insert: true)
			
			def unaDireccion = new Direccion(	calle: 'persona.direccion.calle', 
												numero: 'persona.direccion.numero', 
												adicional: 'persona.direccion.adicional', 
												codigoPostal: 'persona.direccion.codigoPostal', 
												ciudad: 'persona.direccion.ciudad', 
												provincia: unaProvincia)
			unaDireccion.save(flush: true, insert: true)
						
			Zona unaZona = new Zona(nombre: 'unaZona', numero:'99');
			unaZona.save();
	
			Distrito unDistrito = new Distrito(nombre: 'unDistrito', zona: unaZona);
			unDistrito.save();
			
			Grupo unGrupo = new Grupo(nombre: 'unGrupo', numero:'99', distrito: unDistrito)
			unGrupo.save();
		
			def admin = new Persona(	documentoNumero: 	'25227067',
										password: 			password,
										nombre: 			'Esteban',
										apellido: 			'Gomez',
										mail: 				'persona@mail.com',
										direccion: 			unaDireccion,
										//zona: 				new Zona(nombre: 'persona.zona.nombre'),
										//superior: 			null,
										enabled: 			true, 
										accountExpired: 	false, 
										accountLocked: 		false, 
										passwordExpired: 	false)
				.save(flush: true, insert: true)
			
			def cursante1 = new Persona(	documentoNumero: 	'11111111',
											password: 			password,
											nombre: 			'Pablo',
											apellido: 			'Perez',
											mail: 				'dal2409@gmail.com',
											//direccion: 			unaDireccion,
											enabled: 			true,
											accountExpired: 	false,
											accountLocked: 		false,
											passwordExpired: 	false)
			.save(flush: true, insert: true)
			
			def cursante2 = new Persona(	documentoNumero: 	'11111112',
				password: 			password,
				nombre: 			'Damian',
				apellido: 			'Yahoo',
				mail: 				'damian2409@yahoo.com.ar',
				//direccion: 			unaDireccion,
				enabled: 			true,
				accountExpired: 	false,
				accountLocked: 		false,
				passwordExpired: 	false)
			.save(flush: true, insert: true)

			def cursante_supervisor = new Persona(	documentoNumero: 	'22222222',
				password: 			password,
				nombre: 			'Damian',
				apellido: 			'Vera',
				mail: 				'XXX@gmail.com',
				direccion: 			unaDireccion,
				zona:				unaZona,
				distrito:			unDistrito,
				grupo:				unGrupo,
				enabled: 			true,
				accountExpired: 	false,
				accountLocked: 		false,
				passwordExpired: 	false)
			.save(flush: true, insert: true)

			cursante_formador = new Persona(	documentoNumero: 	'33333333',
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
				nombre: 			'Jose',
				apellido: 			'Maria',
				mail: 				'XXX@gmail.com',
				direccion: 			unaDireccion,
				enabled: 			true,
				accountExpired: 	false,
				accountLocked: 		false,
				passwordExpired: 	false)
			.save(flush: true, insert: true)
			
			def dnra = new Persona(	documentoNumero: 	'55555555',
				password: 			password,
				nombre: 			'Pedro',
				apellido: 			'Ortiz',
				mail: 				'XXX@gmail.com',
				direccion: 			unaDireccion,
				enabled: 			true,
				accountExpired: 	false,
				accountLocked: 		false,
				passwordExpired: 	false)
			.save(flush: true, insert: true)
			
			def rolAdmin  = new Rol(authority: 'ROLE_ADMIN').save(flush: true, insert: true)
			rolCursante = new Rol(authority: 'ROLE_CURSANTE').save(flush: true, insert: true)
			def rolSupervisor = new Rol(authority: 'ROLE_SUPERVISOR').save(flush: true, insert: true)
			def rolFormador = new Rol(authority: 'ROLE_FORMADOR').save(flush: true, insert: true)
			def rolDNF = new Rol(authority: 'ROLE_DNF').save(flush: true, insert: true)
			def rolDNRA = new Rol(authority: 'ROLE_DNRA').save(flush: true, insert: true)
				
			/*create the first user role map*/
			PersonaRol.create(admin, rolAdmin, true)
			PersonaRol.create(cursante1, rolCursante, true)
			PersonaRol.create(cursante2, rolCursante, true)
			PersonaRol.create(cursante_supervisor, rolCursante, true)
			PersonaRol.create(cursante_supervisor, rolSupervisor, true)
			PersonaRol.create(cursante_formador, rolCursante, true)
			PersonaRol.create(cursante_formador, rolFormador, true)
			PersonaRol.create(dnf, rolDNF, true)
			PersonaRol.create(dnra, rolDNRA, true)
			
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
		

		
		Zona zona01 = new Zona(nombre: 'Zona01', numero:1);
		zona01.save();
		
		Zona zona02 = new Zona(nombre: 'Zona02', numero:2);
		zona02.save();
		
		Zona zona03 = new Zona(nombre: 'Zona03', numero:3);
		zona03.save();
		

		Distrito distrito01 = new Distrito(nombre: 'Distrito01', zona: zona01);
		distrito01.save();
		
		Distrito distrito02 = new Distrito(nombre: 'Distrito02', zona: zona01);
		distrito02.save();
		
		Distrito distrito03 = new Distrito(nombre: 'Distrito03', zona: zona01);
		distrito03.save();
		
		Distrito distrito04 = new Distrito(nombre: 'Distrito04', zona: zona02);
		distrito04.save();
		
		Distrito distrito05 = new Distrito(nombre: 'Distrito05', zona: zona02);
		distrito05.save();
		
		Distrito distrito06 = new Distrito(nombre: 'Distrito06', zona: zona02);
		distrito06.save();
		
		Distrito distrito07 = new Distrito(nombre: 'Distrito07', zona: zona03);
		distrito07.save();
		
		Distrito distrito08 = new Distrito(nombre: 'Distrito08', zona: zona03);
		distrito08.save();
		
		Distrito distrito09 = new Distrito(nombre: 'Distrito09', zona: zona03);
		distrito09.save();
		

		Grupo grupo01 = new Grupo(nombre: 'Grupo01', numero:1, distrito: distrito01)
		grupo01.save()
		Grupo grupo02 = new Grupo(nombre: 'Grupo02', numero:2, distrito: distrito01)
		grupo02.save()
		Grupo grupo03 = new Grupo(nombre: 'Grupo03', numero:3, distrito: distrito01)
		grupo03.save()
		Grupo grupo04 = new Grupo(nombre: 'Grupo04', numero:4, distrito: distrito02)
		grupo04.save()
		Grupo grupo05 = new Grupo(nombre: 'Grupo05', numero:5, distrito: distrito02)
		grupo05.save()
		Grupo grupo06 = new Grupo(nombre: 'Grupo06', numero:6, distrito: distrito02)
		grupo06.save()
		Grupo grupo07 = new Grupo(nombre: 'Grupo07', numero:7, distrito: distrito03)
		grupo07.save()
		Grupo grupo08 = new Grupo(nombre: 'Grupo08', numero:8, distrito: distrito03)
		grupo08.save()
		Grupo grupo09 = new Grupo(nombre: 'Grupo09', numero:9, distrito: distrito03)
		grupo09.save()
		Grupo grupo10 = new Grupo(nombre: 'Grupo10', numero:10, distrito: distrito04)
		grupo10.save()
		Grupo grupo11 = new Grupo(nombre: 'Grupo11', numero:11, distrito: distrito04)
		grupo11.save()
		Grupo grupo12 = new Grupo(nombre: 'Grupo12', numero:12, distrito: distrito04)
		grupo12.save()
		Grupo grupo13 = new Grupo(nombre: 'Grupo13', numero:13, distrito: distrito05)
		grupo13.save()
		Grupo grupo14 = new Grupo(nombre: 'Grupo14', numero:14, distrito: distrito05)
		grupo14.save()
		Grupo grupo15 = new Grupo(nombre: 'Grupo15', numero:15, distrito: distrito05)
		grupo15.save()
		Grupo grupo16 = new Grupo(nombre: 'Grupo16', numero:16, distrito: distrito06)
		grupo16.save()
		Grupo grupo17 = new Grupo(nombre: 'Grupo17', numero:17, distrito: distrito06)
		grupo17.save()
		Grupo grupo18 = new Grupo(nombre: 'Grupo18', numero:18, distrito: distrito06)
		grupo18.save()
		Grupo grupo19 = new Grupo(nombre: 'Grupo19', numero:19, distrito: distrito07)
		grupo19.save()
		Grupo grupo20 = new Grupo(nombre: 'Grupo20', numero:20, distrito: distrito07)
		grupo20.save()
		Grupo grupo21 = new Grupo(nombre: 'Grupo21', numero:21, distrito: distrito07)
		grupo21.save()
		Grupo grupo22 = new Grupo(nombre: 'Grupo22', numero:22, distrito: distrito08)
		grupo22.save()
		Grupo grupo23 = new Grupo(nombre: 'Grupo23', numero:23, distrito: distrito08)
		grupo23.save()
		Grupo grupo24 = new Grupo(nombre: 'Grupo24', numero:24, distrito: distrito08)
		grupo24.save()
		Grupo grupo25 = new Grupo(nombre: 'Grupo25', numero:25, distrito: distrito09)
		grupo25.save()
		Grupo grupo26 = new Grupo(nombre: 'Grupo26', numero:26, distrito: distrito09)
		grupo26.save()
		Grupo grupo27 = new Grupo(nombre: 'Grupo27', numero:27, distrito: distrito09)
		grupo27.save()


		def persona01 = new Persona(documentoNumero: '00000001', nombre: 'nombre01', apellido: 'apellido01', mail: 'mail@mail.com', password: password, grupo: grupo01);
		persona01.save();
		def persona02 = new Persona(documentoNumero: '00000002', nombre: 'nombre02', apellido: 'apellido02', mail: 'mail@mail.com', password: password, grupo: grupo01);
		persona02.save();
		def persona03 = new Persona(documentoNumero: '00000003', nombre: 'nombre03', apellido: 'apellido03', mail: 'mail@mail.com', password: password, grupo: grupo01);
		persona03.save();
		def persona04 = new Persona(documentoNumero: '00000004', nombre: 'nombre04', apellido: 'apellido04', mail: 'mail@mail.com', password: password, grupo: grupo02);
		persona04.save();
		def persona05 = new Persona(documentoNumero: '00000005', nombre: 'nombre05', apellido: 'apellido05', mail: 'mail@mail.com', password: password, grupo: grupo02);
		persona05.save();
		def persona06 = new Persona(documentoNumero: '00000006', nombre: 'nombre06', apellido: 'apellido06', mail: 'mail@mail.com', password: password, grupo: grupo02);
		persona06.save();
		def persona07 = new Persona(documentoNumero: '00000007', nombre: 'nombre07', apellido: 'apellido07', mail: 'mail@mail.com', password: password, grupo: grupo03);
		persona07.save();
		def persona08 = new Persona(documentoNumero: '00000008', nombre: 'nombre08', apellido: 'apellido08', mail: 'mail@mail.com', password: password, grupo: grupo03);
		persona08.save();
		def persona09 = new Persona(documentoNumero: '00000009', nombre: 'nombre09', apellido: 'apellido09', mail: 'mail@mail.com', password: password, grupo: grupo03);
		persona09.save();
		def persona10 = new Persona(documentoNumero: '00000010', nombre: 'nombre10', apellido: 'apellido10', mail: 'mail@mail.com', password: password, grupo: grupo04);
		persona10.save();
		def persona11 = new Persona(documentoNumero: '00000011', nombre: 'nombre11', apellido: 'apellido11', mail: 'mail@mail.com', password: password, grupo: grupo04);
		persona11.save();
		def persona12 = new Persona(documentoNumero: '00000012', nombre: 'nombre12', apellido: 'apellido12', mail: 'mail@mail.com', password: password, grupo: grupo04);
		persona12.save();
		def persona13 = new Persona(documentoNumero: '00000013', nombre: 'nombre13', apellido: 'apellido13', mail: 'mail@mail.com', password: password, grupo: grupo05);
		persona13.save();
		def persona14 = new Persona(documentoNumero: '00000014', nombre: 'nombre14', apellido: 'apellido14', mail: 'mail@mail.com', password: password, grupo: grupo05);
		persona14.save();
		def persona15 = new Persona(documentoNumero: '00000015', nombre: 'nombre15', apellido: 'apellido15', mail: 'mail@mail.com', password: password, grupo: grupo05);
		persona15.save();
		def persona16 = new Persona(documentoNumero: '00000016', nombre: 'nombre16', apellido: 'apellido16', mail: 'mail@mail.com', password: password, grupo: grupo06);
		persona16.save();
		def persona17 = new Persona(documentoNumero: '00000017', nombre: 'nombre17', apellido: 'apellido17', mail: 'mail@mail.com', password: password, grupo: grupo06);
		persona17.save();
		def persona18 = new Persona(documentoNumero: '00000018', nombre: 'nombre18', apellido: 'apellido18', mail: 'mail@mail.com', password: password, grupo: grupo06);
		persona18.save();
		def persona19 = new Persona(documentoNumero: '00000019', nombre: 'nombre19', apellido: 'apellido19', mail: 'mail@mail.com', password: password, grupo: grupo07);
		persona19.save();
		def persona20 = new Persona(documentoNumero: '00000020', nombre: 'nombre20', apellido: 'apellido20', mail: 'mail@mail.com', password: password, grupo: grupo07);
		persona20.save();
		def persona21 = new Persona(documentoNumero: '00000021', nombre: 'nombre21', apellido: 'apellido21', mail: 'mail@mail.com', password: password, grupo: grupo07);
		persona21.save();
		def persona22 = new Persona(documentoNumero: '00000022', nombre: 'nombre22', apellido: 'apellido22', mail: 'mail@mail.com', password: password, grupo: grupo08);
		persona22.save();
		def persona23 = new Persona(documentoNumero: '00000023', nombre: 'nombre23', apellido: 'apellido23', mail: 'mail@mail.com', password: password, grupo: grupo08);
		persona23.save();
		def persona24 = new Persona(documentoNumero: '00000024', nombre: 'nombre24', apellido: 'apellido24', mail: 'mail@mail.com', password: password, grupo: grupo08);
		persona24.save();
		def persona25 = new Persona(documentoNumero: '00000025', nombre: 'nombre25', apellido: 'apellido25', mail: 'mail@mail.com', password: password, grupo: grupo09);
		persona25.save();
		def persona26 = new Persona(documentoNumero: '00000026', nombre: 'nombre26', apellido: 'apellido26', mail: 'mail@mail.com', password: password, grupo: grupo09);
		persona26.save();
		def persona27 = new Persona(documentoNumero: '00000027', nombre: 'nombre27', apellido: 'apellido27', mail: 'mail@mail.com', password: password, grupo: grupo09);
		persona27.save();
		def persona28 = new Persona(documentoNumero: '00000028', nombre: 'nombre28', apellido: 'apellido28', mail: 'mail@mail.com', password: password, grupo: grupo10);
		persona28.save();
		def persona29 = new Persona(documentoNumero: '00000029', nombre: 'nombre29', apellido: 'apellido29', mail: 'mail@mail.com', password: password, grupo: grupo10);
		persona29.save();
		def persona30 = new Persona(documentoNumero: '00000030', nombre: 'nombre30', apellido: 'apellido30', mail: 'mail@mail.com', password: password, grupo: grupo10);
		persona30.save();
		def persona31 = new Persona(documentoNumero: '00000031', nombre: 'nombre31', apellido: 'apellido31', mail: 'mail@mail.com', password: password, grupo: grupo11);
		persona31.save();
		def persona32 = new Persona(documentoNumero: '00000032', nombre: 'nombre32', apellido: 'apellido32', mail: 'mail@mail.com', password: password, grupo: grupo11);
		persona32.save();
		def persona33 = new Persona(documentoNumero: '00000033', nombre: 'nombre33', apellido: 'apellido33', mail: 'mail@mail.com', password: password, grupo: grupo11);
		persona33.save();
		def persona34 = new Persona(documentoNumero: '00000034', nombre: 'nombre34', apellido: 'apellido34', mail: 'mail@mail.com', password: password, grupo: grupo12);
		persona34.save();
		def persona35 = new Persona(documentoNumero: '00000035', nombre: 'nombre35', apellido: 'apellido35', mail: 'mail@mail.com', password: password, grupo: grupo12);
		persona35.save();
		def persona36 = new Persona(documentoNumero: '00000036', nombre: 'nombre36', apellido: 'apellido36', mail: 'mail@mail.com', password: password, grupo: grupo12);
		persona36.save();
		def persona37 = new Persona(documentoNumero: '00000037', nombre: 'nombre37', apellido: 'apellido37', mail: 'mail@mail.com', password: password, grupo: grupo13);
		persona37.save();
		def persona38 = new Persona(documentoNumero: '00000038', nombre: 'nombre38', apellido: 'apellido38', mail: 'mail@mail.com', password: password, grupo: grupo13);
		persona38.save();
		def persona39 = new Persona(documentoNumero: '00000039', nombre: 'nombre39', apellido: 'apellido39', mail: 'mail@mail.com', password: password, grupo: grupo13);
		persona39.save();
		def persona40 = new Persona(documentoNumero: '00000040', nombre: 'nombre40', apellido: 'apellido40', mail: 'mail@mail.com', password: password, grupo: grupo14);
		persona40.save();
		def persona41 = new Persona(documentoNumero: '00000041', nombre: 'nombre41', apellido: 'apellido41', mail: 'mail@mail.com', password: password, grupo: grupo14);
		persona41.save();
		def persona42 = new Persona(documentoNumero: '00000042', nombre: 'nombre42', apellido: 'apellido42', mail: 'mail@mail.com', password: password, grupo: grupo14);
		persona42.save();
		def persona43 = new Persona(documentoNumero: '00000043', nombre: 'nombre43', apellido: 'apellido43', mail: 'mail@mail.com', password: password, grupo: grupo15);
		persona43.save();
		def persona44 = new Persona(documentoNumero: '00000044', nombre: 'nombre44', apellido: 'apellido44', mail: 'mail@mail.com', password: password, grupo: grupo15);
		persona44.save();
		def persona45 = new Persona(documentoNumero: '00000045', nombre: 'nombre45', apellido: 'apellido45', mail: 'mail@mail.com', password: password, grupo: grupo15);
		persona45.save();
		def persona46 = new Persona(documentoNumero: '00000046', nombre: 'nombre46', apellido: 'apellido46', mail: 'mail@mail.com', password: password, grupo: grupo16);
		persona46.save();
		def persona47 = new Persona(documentoNumero: '00000047', nombre: 'nombre47', apellido: 'apellido47', mail: 'mail@mail.com', password: password, grupo: grupo16);
		persona47.save();
		def persona48 = new Persona(documentoNumero: '00000048', nombre: 'nombre48', apellido: 'apellido48', mail: 'mail@mail.com', password: password, grupo: grupo16);
		persona48.save();
		def persona49 = new Persona(documentoNumero: '00000049', nombre: 'nombre49', apellido: 'apellido49', mail: 'mail@mail.com', password: password, grupo: grupo17);
		persona49.save();
		def persona50 = new Persona(documentoNumero: '00000050', nombre: 'nombre50', apellido: 'apellido50', mail: 'mail@mail.com', password: password, grupo: grupo17);
		persona50.save();
		def persona51 = new Persona(documentoNumero: '00000051', nombre: 'nombre51', apellido: 'apellido51', mail: 'mail@mail.com', password: password, grupo: grupo17);
		persona51.save();
		def persona52 = new Persona(documentoNumero: '00000052', nombre: 'nombre52', apellido: 'apellido52', mail: 'mail@mail.com', password: password, grupo: grupo18);
		persona52.save();
		def persona53 = new Persona(documentoNumero: '00000053', nombre: 'nombre53', apellido: 'apellido53', mail: 'mail@mail.com', password: password, grupo: grupo18);
		persona53.save();
		def persona54 = new Persona(documentoNumero: '00000054', nombre: 'nombre54', apellido: 'apellido54', mail: 'mail@mail.com', password: password, grupo: grupo18);
		persona54.save();
		def persona55 = new Persona(documentoNumero: '00000055', nombre: 'nombre55', apellido: 'apellido55', mail: 'mail@mail.com', password: password, grupo: grupo19);
		persona55.save();
		def persona56 = new Persona(documentoNumero: '00000056', nombre: 'nombre56', apellido: 'apellido56', mail: 'mail@mail.com', password: password, grupo: grupo19);
		persona56.save();
		def persona57 = new Persona(documentoNumero: '00000057', nombre: 'nombre57', apellido: 'apellido57', mail: 'mail@mail.com', password: password, grupo: grupo19);
		persona57.save();
		def persona58 = new Persona(documentoNumero: '00000058', nombre: 'nombre58', apellido: 'apellido58', mail: 'mail@mail.com', password: password, grupo: grupo20);
		persona58.save();
		def persona59 = new Persona(documentoNumero: '00000059', nombre: 'nombre59', apellido: 'apellido59', mail: 'mail@mail.com', password: password, grupo: grupo20);
		persona59.save();
		def persona60 = new Persona(documentoNumero: '00000060', nombre: 'nombre60', apellido: 'apellido60', mail: 'mail@mail.com', password: password, grupo: grupo20);
		persona60.save();
		def persona61 = new Persona(documentoNumero: '00000061', nombre: 'nombre61', apellido: 'apellido61', mail: 'mail@mail.com', password: password, grupo: grupo21);
		persona61.save();
		def persona62 = new Persona(documentoNumero: '00000062', nombre: 'nombre62', apellido: 'apellido62', mail: 'mail@mail.com', password: password, grupo: grupo21);
		persona62.save();
		def persona63 = new Persona(documentoNumero: '00000063', nombre: 'nombre63', apellido: 'apellido63', mail: 'mail@mail.com', password: password, grupo: grupo21);
		persona63.save();
		def persona64 = new Persona(documentoNumero: '00000064', nombre: 'nombre64', apellido: 'apellido64', mail: 'mail@mail.com', password: password, grupo: grupo22);
		persona64.save();
		def persona65 = new Persona(documentoNumero: '00000065', nombre: 'nombre65', apellido: 'apellido65', mail: 'mail@mail.com', password: password, grupo: grupo22);
		persona65.save();
		def persona66 = new Persona(documentoNumero: '00000066', nombre: 'nombre66', apellido: 'apellido66', mail: 'mail@mail.com', password: password, grupo: grupo22);
		persona66.save();
		def persona67 = new Persona(documentoNumero: '00000067', nombre: 'nombre67', apellido: 'apellido67', mail: 'mail@mail.com', password: password, grupo: grupo23);
		persona67.save();
		def persona68 = new Persona(documentoNumero: '00000068', nombre: 'nombre68', apellido: 'apellido68', mail: 'mail@mail.com', password: password, grupo: grupo23);
		persona68.save();
		def persona69 = new Persona(documentoNumero: '00000069', nombre: 'nombre69', apellido: 'apellido69', mail: 'mail@mail.com', password: password, grupo: grupo23);
		persona69.save();
		def persona70 = new Persona(documentoNumero: '00000070', nombre: 'nombre70', apellido: 'apellido70', mail: 'mail@mail.com', password: password, grupo: grupo24);
		persona70.save();
		def persona71 = new Persona(documentoNumero: '00000071', nombre: 'nombre71', apellido: 'apellido71', mail: 'mail@mail.com', password: password, grupo: grupo24);
		persona71.save();
		def persona72 = new Persona(documentoNumero: '00000072', nombre: 'nombre72', apellido: 'apellido72', mail: 'mail@mail.com', password: password, grupo: grupo24);
		persona72.save();
		def persona73 = new Persona(documentoNumero: '00000073', nombre: 'nombre73', apellido: 'apellido73', mail: 'mail@mail.com', password: password, grupo: grupo25);
		persona73.save();
		def persona74 = new Persona(documentoNumero: '00000074', nombre: 'nombre74', apellido: 'apellido74', mail: 'mail@mail.com', password: password, grupo: grupo25);
		persona74.save();
		def persona75 = new Persona(documentoNumero: '00000075', nombre: 'nombre75', apellido: 'apellido75', mail: 'mail@mail.com', password: password, grupo: grupo25);
		persona75.save();
		def persona76 = new Persona(documentoNumero: '00000076', nombre: 'nombre76', apellido: 'apellido76', mail: 'mail@mail.com', password: password, grupo: grupo26);
		persona76.save();
		def persona77 = new Persona(documentoNumero: '00000077', nombre: 'nombre77', apellido: 'apellido77', mail: 'mail@mail.com', password: password, grupo: grupo26);
		persona77.save();
		def persona78 = new Persona(documentoNumero: '00000078', nombre: 'nombre78', apellido: 'apellido78', mail: 'mail@mail.com', password: password, grupo: grupo26);
		persona78.save();
		def persona79 = new Persona(documentoNumero: '00000079', nombre: 'nombre79', apellido: 'apellido79', mail: 'mail@mail.com', password: password, grupo: grupo27);
		persona79.save();
		def persona80 = new Persona(documentoNumero: '00000080', nombre: 'nombre80', apellido: 'apellido80', mail: 'mail@mail.com', password: password, grupo: grupo27);
		persona80.save();
		def persona81 = new Persona(documentoNumero: '00000081', nombre: 'nombre81', apellido: 'apellido81', mail: 'mail@mail.com', password: password, grupo: grupo27);
		persona81.save();


		PersonaRol.create(persona81, rolCursante, true)

		
		
		cursante_formador.zona = zona01;
		cursante_formador.save();
		
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
		
		DictadoStatus abierto = new DictadoStatus(nombre:"ABIERTO");
		abierto.save(flush: true, insert: true);
		DictadoStatus cerrado = new DictadoStatus(nombre:"CERRADO");
		cerrado.save(flush: true, insert: true);
		
		Persona forma = Persona.findByNombre('Carlos');
		Dictado dictado1 = new Dictado(nombre: "Jueves 9hs", formador: forma, zona: zona01, curso: curso2, fecha: new Date()+10, cupo: 10, status: abierto);
		dictado1.addToRecursos(r1)
		dictado1.save(flush: true, insert: true);
		
		Dictado dictado2 = new Dictado(nombre: "Miercoles 15hs", formador: forma, zona: zona02, curso: curso2, fecha: new Date()+5, cupo: 0, status: abierto);
		dictado2.addToRecursos(r2)
		dictado2.save(flush: true, insert: true);
		
		Dictado dictado3 = new Dictado(nombre: "Sabados 10hs", formador: forma, zona: zona01, curso: curso3, fecha: new Date(), cupo: 5, status: abierto);
		dictado3.addToRecursos(r3)
		dictado3.save(flush: true, insert: true);
		
		Dictado dictado4 = new Dictado(nombre: "Viernes 19hs", formador: forma, zona: zona03, curso: curso3, fecha: new Date()+10, cupo: 7, status: abierto);
		dictado4.addToRecursos(r4)
		dictado4.save(flush: true, insert: true);
		
		Calificacion aprobado = new Calificacion(nombre: 'APROBADO')
		aprobado.save(flush: true, insert: true)
		
		Calificacion desaprobado = new Calificacion(nombre: 'DESAPROBADO')
		desaprobado.save(flush: true, insert: true)
		
	}


	def destroy = {
	}


}
