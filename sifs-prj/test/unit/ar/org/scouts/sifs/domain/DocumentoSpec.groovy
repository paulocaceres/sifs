package ar.org.scouts.sifs.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

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
		def unDocumento = new Documento(tipo: 'unTipo',
										descripcion: 'unaDescripcion')
		
		assertEquals 'unTipo, unaDescripcion', unDocumento.toString()
    }

}
