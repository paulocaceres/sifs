package ar.org.scouts.sifs.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

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
		def unaZona = new Zona(nombre: 'unNombre')
		
		assertEquals 'unNombre', unaZona.toString()
    }

}
