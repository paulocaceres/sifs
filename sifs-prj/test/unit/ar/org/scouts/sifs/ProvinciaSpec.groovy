package ar.org.scouts.sifs



import grails.test.mixin.*



/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Provincia)
class ProvinciaSpec {


	def setup() {
	}


	def cleanup() {
	}


	void testToString() {
		def unaProvincia = new Provincia(descripcion: 'provincia.descripcion')

		assertEquals 'provincia.descripcion', unaProvincia.toString()
	}


}
