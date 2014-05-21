package sifs.prj

import com.sifs.prj.Provincia;

import grails.test.mixin.TestFor
import spock.lang.Specification

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
		def unaProvincia = new Provincia(descripcion: 'unaDescripcion')
		
		assertEquals 'unaDescripcion', unaProvincia.toString()
    }

}
