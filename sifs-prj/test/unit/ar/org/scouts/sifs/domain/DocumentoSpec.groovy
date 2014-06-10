package ar.org.scouts.sifs.domain



import grails.test.mixin.*



/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Documento)
class DocumentoSpec {


	def setup() {
	}


	def cleanup() {
	}


	void testToString() {
		def unDocumento = new Documento(tipo: 			'documento.tipo',
										descripcion: 	'documento.descripcion')

		assertEquals 	'documento.tipo, ' +
						'documento.descripcion', unDocumento.toString()
	}


}
