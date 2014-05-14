package sifs.prj

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Persona)
class PersonaSpec {

    def setup() {
    }

    def cleanup() {
    }

    void testToString() {
		def unaZona = new Zona(nombre: 'unNombre')
		def unaProvincia = new Provincia(descripcion: 'unaDescripcion')
		
		def unaPersona = new Persona(	zona: unaZona,
										superior: null,
										documentoNumero: 'unDocumentoNumero',
										nombre: 'unNombre',
										apellido: 'unApellido',
										mail: 'unMail',
										direccion: 'unaDireccion',
										provincia: unaProvincia,
										bloqueado: false)
		
		assertEquals 'unNombre, null, unDocumentoNumero, unNombre, unApellido, unMail, unaDireccion, unaDescripcion, false', unaPersona.toString()
    }

}
