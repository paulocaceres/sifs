package ar.org.scouts.sifs.domain



import grails.test.mixin.TestFor



/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Direccion)
class DireccionSpec {


	def setup() {
	}


	def cleanup() {
	}


	void testSomething() {
		def unaDireccion = new Direccion(calle: 			'direccion.calle', 
										numero: 		'direccion.numero', 
										adicional: 		'direccion.adicional', 
										codigoPostal: 	'direccion.codigoPostal', 
										ciudad: 		'direccion.ciudad', 
										provincia: 		new Provincia(descripcion: 'direccion.provincia.descripcion'))

		assertEquals 	'direccion.calle, ' +
						'direccion.numero, ' +
						'direccion.adicional, ' +
						'direccion.codigoPostal, ' +
						'direccion.ciudad, ' +
						'direccion.provincia.descripcion', unaDireccion.toString()
	}


}
