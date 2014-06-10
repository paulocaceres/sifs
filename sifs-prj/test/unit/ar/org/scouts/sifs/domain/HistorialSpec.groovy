package ar.org.scouts.sifs.domain



import grails.test.mixin.*
import grails.test.mixin.TestFor
import spock.lang.Specification



/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Historial)
class HistorialSpec {


	def setup() {
	}


	def cleanup() {
	}


	void testToString() {
		
		def unHistorial = new Historial(persona:	new Persona(	zona: 				new Zona(nombre: 'historial.persona.zona.nombre'),
																	superior: 			null,
																	documentoNumero: 	'historial.persona.documentoNumero',
																	nombre: 			'historial.persona.nombre',
																	apellido: 			'historial.persona.apellido',
																	mail: 				'historial.persona.mail',
																	direccion: 			new Direccion(	calle: 			'historial.persona.direccion.calle', 
																										numero: 		'historial.persona.direccion.numero', 
																										adicional: 		'historial.persona.direccion.adicional', 
																										codigoPostal: 	'historial.persona.direccion.codigoPostal', 
																										ciudad: 		'historial.persona.direccion.ciudad', 
																										provincia: 		new Provincia(descripcion: 'historial.persona.direccion.provincia.descripcion')),
																	bloqueado: 			false),
										curso:			new Curso(	plan: 			new Plan(	nombre: 		'historial.curso.plan.nombre',
																								descripcion: 	'historial.curso.plan.descripcion',
																								validez: 		Date.parse('dd/MM/yyyy', '31/12/2014')),
																	zona: 			new Zona(nombre: 'historial.curso.zona.nombre'),
																	recurso: 		new Recurso(	nombre: 	'historial.curso.recurso.nombre',
																									cantidad: 	1,
																									direccion: 	new Direccion(	calle: 			'historial.curso.recurso.direccion.calle',
																																numero: 		'historial.curso.recurso.direccion.numero',
																																adicional: 		'historial.curso.recurso.direccion.adicional',
																																codigoPostal: 	'historial.curso.recurso.direccion.codigoPostal',
																																ciudad: 		'historial.curso.recurso.direccion.ciudad',
																																provincia: 		new Provincia(descripcion: 'historial.curso.recurso.direccion.provincia.descripcion'))),
																	contenido: 		new Contenido(	nombre: 		'historial.curso.contenido.nombre',
																									descripcion: 	'historial.curso.contenido.descripcion'),
																	correlativas: 	1,
																	inscripto: 		new Persona(zona: 				new Zona(nombre: 'historial.curso.persona.zona.nombre'),
																								superior: 			null,
																								documentoNumero: 	'historial.persona.documentoNumero',
																								nombre: 			'historial.persona.nombre',
																								apellido: 			'historial.persona.apellido',
																								mail: 				'historial.persona.mail',
																								direccion: 			new Direccion(	calle: 			'historial.persona.direccion.calle', 
																																	numero: 		'historial.persona.direccion.numero', 
																																	adicional: 		'historial.persona.direccion.adicional', 
																																	codigoPostal: 	'historial.persona.direccion.codigoPostal', 
																																	ciudad: 		'historial.persona.direccion.ciudad', 
																																	provincia: 		new Provincia(descripcion: 'historial.persona.direccion.provincia.descripcion')),
																								bloqueado: 			false),
																	nivel: 			new Nivel(	nombre: 	'historial.curso.nivel.nombre',
																								nivelCol: 	'historial.curso.nivel.nivelCol'),
																	nombre: 		'historial.curso.nombre',
																	fecha: 			Date.parse('dd/MM/yyyy', '31/12/2014'),
																	cupo: 			10),
										fechaAprobacion: 	Date.parse('dd/MM/yyyy', '31/12/2014'),
										calificacion: 		1)

		assertEquals 	'historial.persona.zona.nombre, ' +
						'null, ' +
						'historial.persona.documentoNumero, ' +
						'historial.persona.nombre, ' +
						'historial.persona.apellido, ' +
						'historial.persona.mail, ' +
						'historial.persona.direccion.calle, ' +
						'historial.persona.direccion.numero, ' +
						'historial.persona.direccion.adicional, ' +
						'historial.persona.direccion.codigoPostal, ' +
						'historial.persona.direccion.ciudad, ' +
						'historial.persona.direccion.provincia.descripcion, ' +
						'false, ' +
						'historial.curso.plan.nombre, ' +
						'historial.curso.plan.descripcion, ' +
						'31/12/2014, ' +
						'historial.curso.zona.nombre, ' +
						'historial.curso.recurso.nombre, ' +
						'1, ' +
						'historial.curso.recurso.direccion.calle, ' +
						'historial.curso.recurso.direccion.numero, ' +
						'historial.curso.recurso.direccion.adicional, ' +
						'historial.curso.recurso.direccion.codigoPostal, ' +
						'historial.curso.recurso.direccion.ciudad, ' +
						'historial.curso.recurso.direccion.provincia.descripcion, ' +
						'historial.curso.contenido.nombre, ' +
						'historial.curso.contenido.descripcion, ' +
						'1, ' +
						'historial.curso.persona.zona.nombre, ' +
						'null, ' +
						'historial.persona.documentoNumero, ' +
						'historial.persona.nombre, ' +
						'historial.persona.apellido, ' +
						'historial.persona.mail, ' +
						'historial.persona.direccion.calle, ' +
						'historial.persona.direccion.numero, ' +
						'historial.persona.direccion.adicional, ' +
						'historial.persona.direccion.codigoPostal, ' +
						'historial.persona.direccion.ciudad, ' +
						'historial.persona.direccion.provincia.descripcion, ' +
						'false, ' +
						'historial.curso.nivel.nombre, ' +
						'historial.curso.nivel.nivelCol, ' +
						'historial.curso.nombre, ' +
						'31/12/2014, ' +
						'10, ' +
						'31/12/2014, ' +
						'1', unHistorial.toString()
    }


}
