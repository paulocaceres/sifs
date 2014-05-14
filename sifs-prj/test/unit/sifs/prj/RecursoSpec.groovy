package sifs.prj

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Recurso)
class RecursoSpec {

    def setup() {
    }

    def cleanup() {
    }

    void testToString() {
		def unRecurso = new Recurso(nombre: 'unNombre',
									cantidad: 1,
									direccion: 'unaDireccion')
		
		assertEquals 'unNombre, 1, unaDireccion', unRecurso.toString()
    }

}
