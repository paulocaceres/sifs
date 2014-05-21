package sifs.prj

import com.sifs.prj.Nivel;

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
		def unNivel = new Nivel(nombre: 'unNombre',
								nivelCol: 'unNivelCol')
		
		assertEquals 'unNombre, unNivelCol', unNivel.toString()
    }

}
