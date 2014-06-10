package ar.org.scouts.sifs.domain



import grails.test.mixin.*



/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Persona)
class PersonaSpec {


	def setup() {
	}


	def cleanup() {
	}


	void testToString() {

		def unaPersona = new Persona(	zona: 				new Zona(nombre: 'persona.zona.nombre'),
										superior: 			null,
										documentoNumero: 	'persona.documentoNumero',
										nombre: 			'persona.nombre',
										apellido: 			'persona.apellido',
										mail: 				'persona.mail',
										direccion: 			new Direccion(	calle: 'persona.direccion.calle', 
																			numero: 'persona.direccion.numero', 
																			adicional: 'persona.direccion.adicional', 
																			codigoPostal: 'persona.direccion.codigoPostal', 
																			ciudad: 'persona.direccion.ciudad', 
																			provincia: new Provincia(descripcion: 'persona.direccion.provincia.descripcion')),
										bloqueado: 			false)

		assertEquals 	'persona.zona.nombre, ' +
						'null, ' +
						'persona.documentoNumero, ' +
						'persona.nombre, ' +
						'persona.apellido, ' +
						'persona.mail, ' +
						'persona.direccion.calle, ' +
						'persona.direccion.numero, ' +
						'persona.direccion.adicional, ' +
						'persona.direccion.codigoPostal, ' +
						'persona.direccion.ciudad, ' +
						'persona.direccion.provincia.descripcion, ' +
						'false', unaPersona.toString()
	}


}
