package ar.org.scouts.sifs

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Nivel)
class NivelSpec {

    def setup() {
    }

    def cleanup() {
    }

    void testToString() {
		def unNivel = new Nivel(nombre: 'nivel.nombre',
								nivelCol: 'nivel.nivelCol')
		
		assertEquals 	'nivel.nombre, ' +
						'nivel.nivelCol', unNivel.toString()
    }

}
