package ar.org.scouts.sifs

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
		def unRecurso = new Recurso(nombre: 	'recurso.nombre',
									cantidad: 	1,
									direccion: 	new Direccion(	calle: 			'recurso.direccion.calle', 
																numero: 		'recurso.direccion.numero', 
																adicional: 		'recurso.direccion.adicional', 
																codigoPostal: 	'recurso.direccion.codigoPostal', 
																ciudad: 		'recurso.direccion.ciudad', 
																provincia: 		new Provincia(descripcion: 'recurso.direccion.provincia.descripcion')))
		
		assertEquals 	'recurso.nombre, ' +
						'1, ' +
						'recurso.direccion.calle, ' +
						'recurso.direccion.numero, ' +
						'recurso.direccion.adicional, ' +
						'recurso.direccion.codigoPostal, ' +
						'recurso.direccion.ciudad, ' +
						'recurso.direccion.provincia.descripcion', unRecurso.toString()
    }

}
