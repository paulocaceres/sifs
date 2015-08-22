package ar.org.scouts.sifs



import grails.test.mixin.*



/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Zona)
class ZonaSpec {


	def setup() {
	}


	def cleanup() {
	}


	void testToString() {
		def unaZona = new Zona(nombre: 'zona.nombre')

		assertEquals 'zona.nombre', unaZona.toString()
	}


}
