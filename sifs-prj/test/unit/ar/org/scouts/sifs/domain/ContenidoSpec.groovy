package ar.org.scouts.sifs.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

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
		def unContenido = new Contenido(nombre: 'unNombre',
										descripcion: 'unaDescripcion')
		
		assertEquals 'unNombre, unaDescripcion', unContenido.toString()
    }

}
