package ar.org.scouts.sifs.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Plan)
class PlanSpec {

    def setup() {
    }

    def cleanup() {
    }

    void testToString() {
		def unPlan = new Plan(	nombre: 'unNombre',
								descripcion: 'unaDescripcion',
								validez: Date.parse('dd/MM/yyyy', '31/12/2014'))
		
		assertEquals 'unNombre, unaDescripcion, 31/12/2014', unPlan.toString()
    }

}
