package ar.org.scouts.sifs



import grails.test.mixin.TestFor



/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Curso)
class CursoSpec {


	def setup() {
	}


	def cleanup() {
	}


	void testToString() {

		def unCurso = new Curso(plan: 			new Plan(	nombre: 		'curso.plan.nombre', 
															descripcion: 	'curso.plan.descripcion', 
															validez: 		Date.parse('dd/MM/yyyy', '31/12/2014')),
								zona: 			new Zona(nombre: 'curso.zona.nombre'),
								recurso: 		new Recurso(	nombre: 	'curso.recurso.nombre', 
																cantidad: 	1, 
																direccion: 	new Direccion(	calle: 			'curso.recurso.direccion.calle', 
																							numero: 		'curso.recurso.direccion.numero', 
																							adicional: 		'curso.recurso.direccion.adicional', 
																							codigoPostal: 	'curso.recurso.direccion.codigoPostal', 
																							ciudad: 		'curso.recurso.direccion.ciudad', 
																							provincia: 		new Provincia(descripcion: 'curso.recurso.direccion.provincia.descripcion'))),
								contenido: 		new Contenido(	nombre: 		'curso.contenido.nombre', 
																descripcion: 	'curso.contenido.descripcion'),
								correlativas: 	1,
								inscripto: 		new Persona(zona: 				new Zona(nombre: 'curso.inscripto.zona.nombre'), 
															superior: 			null, 
															documentoNumero: 	'curso.inscripto.documentoNumero', 
															nombre: 			'curso.inscripto.nombre', 
															apellido: 			'curso.inscripto.apellido', 
															mail: 				'curso.inscripto.mail', 
															direccion: 			new Direccion(	calle: 			'curso.inscripto.direccion.calle', 
																								numero: 		'curso.inscripto.direccion.numero', 
																								adicional: 		'curso.inscripto.direccion.adicional', 
																								codigoPostal: 	'curso.inscripto.direccion.codigoPostal', 
																								ciudad: 		'curso.inscripto.direccion.ciudad', 
																								provincia: 		new Provincia(descripcion: 'curso.inscripto.direccion.provincia.descripcion')), 
															bloqueado: 			false),
								nivel: 			new Nivel(	nombre: 	'curso.nivel.nombre', 
															nivelCol: 	'curso.nivel.nivelCol'),
								nombre: 		'curso.nombre',
								fecha: 			Date.parse('dd/MM/yyyy', '31/12/2014'),
								cupo: 			10)



		assertEquals 	'curso.plan.nombre, ' +
						'curso.plan.descripcion, ' +
						'31/12/2014, ' +
						'curso.zona.nombre, ' +
						'curso.recurso.nombre, ' +
						'1, ' +
						'curso.recurso.direccion.calle, ' +
						'curso.recurso.direccion.numero, ' +
						'curso.recurso.direccion.adicional, ' +
						'curso.recurso.direccion.codigoPostal, ' +
						'curso.recurso.direccion.ciudad, ' +
						'curso.recurso.direccion.provincia.descripcion, ' +
						'curso.contenido.nombre, ' +
						'curso.contenido.descripcion, ' +
						'1, ' +
						'curso.inscripto.zona.nombre, ' +
						'null, ' +
						'curso.inscripto.documentoNumero, ' +
						'curso.inscripto.nombre, ' +
						'curso.inscripto.apellido, ' +
						'curso.inscripto.mail, ' +
						'curso.inscripto.direccion.calle, ' +
						'curso.inscripto.direccion.numero, ' +
						'curso.inscripto.direccion.adicional, ' +
						'curso.inscripto.direccion.codigoPostal, ' +
						'curso.inscripto.direccion.ciudad, ' +
						'curso.inscripto.direccion.provincia.descripcion, ' +
						'false, ' +
						'curso.nivel.nombre, ' +
						'curso.nivel.nivelCol, ' +
						'curso.nombre, ' +
						'31/12/2014, ' +
						'10', unCurso.toString()
	}


}
