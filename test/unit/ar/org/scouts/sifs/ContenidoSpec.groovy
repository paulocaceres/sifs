package ar.org.scouts.sifs



import grails.test.mixin.*



/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Contenido)
class ContenidoSpec {


	def setup() {
	}


	def cleanup() {
	}


	void testToString() {
		def unContenido = new Contenido(nombre: 		'contenido.nombre',
										descripcion: 	'contenido.descripcion')

		assertEquals 	'contenido.nombre, ' + 
						'contenido.descripcion', unContenido.toString()
	}


}
