package ar.org.scouts.sifs

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Inscripto)
class InscriptoSpec {

    def setup() {
    }

    def cleanup() {
    }

    void testToString() {
		
		def unInscripto = new Inscripto(curso: new Curso(	plan: 			new Plan(	nombre: 		'inscripto.curso.plan.nombre', 
																						descripcion: 	'inscripto.curso.plan.descripcion', 
																						validez: 		Date.parse('dd/MM/yyyy', '31/12/2014')),
															zona: 			new Zona(nombre: 'inscripto.curso.zona.nombre'),
															recurso: 		new Recurso(	nombre: 	'inscripto.curso.recurso.nombre', 
																							cantidad: 	1, 
																							direccion: 	new Direccion(	calle: 			'inscripto.curso.recurso.direccion.calle', 
																														numero: 		'inscripto.curso.recurso.direccion.numero', 
																														adicional: 		'inscripto.curso.recurso.direccion.adicional', 
																														codigoPostal: 	'inscripto.curso.recurso.direccion.codigoPostal', 
																														ciudad: 		'inscripto.curso.recurso.direccion.ciudad', 
																														provincia: 		new Provincia(descripcion: 'inscripto.curso.recurso.direccion.provincia.descripcion'))),
															contenido: 		new Contenido(	nombre: 		'inscripto.curso.contenido.nombre', 
																							descripcion: 	'inscripto.curso.contenido.descripcion'),
															correlativas: 	1,
															inscripto: 		new Persona(zona: 				new Zona(nombre: 'inscripto.curso.inscripto.zona.nombre'), 
																						superior: 			null, 
																						documentoNumero: 	'inscripto.curso.inscripto.documentoNumero', 
																						nombre: 			'inscripto.curso.inscripto.nombre', 
																						apellido: 			'inscripto.curso.inscripto.apellido', 
																						mail: 				'inscripto.curso.inscripto.mail', 
																						direccion: 			new Direccion(	calle: 			'inscripto.curso.inscripto.direccion.calle', 
																															numero: 		'inscripto.curso.inscripto.direccion.numero', 
																															adicional: 		'inscripto.curso.inscripto.direccion.adicional', 
																															codigoPostal: 	'inscripto.curso.inscripto.direccion.codigoPostal', 
																															ciudad: 		'inscripto.curso.inscripto.direccion.ciudad', 
																															provincia: 		new Provincia(descripcion: 'inscripto.curso.inscripto.direccion.provincia.descripcion')), 
																						bloqueado: 			false),
															nivel: 			new Nivel(	nombre: 	'inscripto.curso.nivel.nombre', 
																						nivelCol: 	'inscripto.curso.nivel.nivelCol'),
															nombre: 		'inscripto.curso.nombre',
															fecha: 			Date.parse('dd/MM/yyyy', '31/12/2014'),
															cupo: 			10),
										fecha: Date.parse('dd/MM/yyyy', '31/12/2014'))
		
		assertEquals 	'inscripto.curso.plan.nombre, ' +
						'inscripto.curso.plan.descripcion, ' +
						'31/12/2014, ' +
						'inscripto.curso.zona.nombre, ' +
						'inscripto.curso.recurso.nombre, ' +
						'1, ' +
						'inscripto.curso.recurso.direccion.calle, ' +
						'inscripto.curso.recurso.direccion.numero, ' +
						'inscripto.curso.recurso.direccion.adicional, ' +
						'inscripto.curso.recurso.direccion.codigoPostal, ' +
						'inscripto.curso.recurso.direccion.ciudad, ' +
						'inscripto.curso.recurso.direccion.provincia.descripcion, ' +
						'inscripto.curso.contenido.nombre, ' +
						'inscripto.curso.contenido.descripcion, ' +
						'1, ' +
						'inscripto.curso.inscripto.zona.nombre, ' +
						'null, ' +
						'inscripto.curso.inscripto.documentoNumero, ' +
						'inscripto.curso.inscripto.nombre, ' +
						'inscripto.curso.inscripto.apellido, ' +
						'inscripto.curso.inscripto.mail, ' +
						'inscripto.curso.inscripto.direccion.calle, ' +
						'inscripto.curso.inscripto.direccion.numero, ' +
						'inscripto.curso.inscripto.direccion.adicional, ' +
						'inscripto.curso.inscripto.direccion.codigoPostal, ' +
						'inscripto.curso.inscripto.direccion.ciudad, ' +
						'inscripto.curso.inscripto.direccion.provincia.descripcion, ' +
						'false, ' +
						'inscripto.curso.nivel.nombre, ' +
						'inscripto.curso.nivel.nivelCol, ' +
						'inscripto.curso.nombre, ' +
						'31/12/2014, ' +
						'10, ' +
						'31/12/2014', unInscripto.toString()
    }

}
