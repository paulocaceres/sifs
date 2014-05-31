package ar.org.scouts.sifs.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Historial)
class HistorialSpec {

    def setup() {
    }

    def cleanup() {
    }

    void testToString() {
		def unPlan = new Plan(	nombre: 'unNombre',
								descripcion: 'unaDescripcion',
								validez: Date.parse('dd/MM/yyyy', '31/12/2014'))
		def unaZona = new Zona(nombre: 'unNombre')
		def unContenido = new Contenido(nombre: 'unNombre',
										descripcion: 'unaDescripcion')
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

		def unNivel = new Nivel(nombre: 'unNombre',
								nivelCol: 'unNivelCol')

		def unCurso = new Curso(plan: unPlan,
								zona: unaZona,
								contenido: unContenido,
								correlativas: 1,
								inscripto: unaPersona,
								nivel: unNivel,
								nombre: 'unNombre',
								fecha: Date.parse('dd/MM/yyyy', '31/12/2014'),
								cupo: 10)

		
		def unHistorial = new Historial(persona: unaPersona,
										curso: unCurso,
										fechaAprobacion: Date.parse('dd/MM/yyyy', '31/12/2014'),
										calificacion: 1)
		
		assertEquals 'unNombre, null, unDocumentoNumero, unNombre, unApellido, unMail, unaDireccion, unaDescripcion, false, unNombre, unaDescripcion, 31/12/2014, unNombre, unNombre, unaDescripcion, 1, unNombre, null, unDocumentoNumero, unNombre, unApellido, unMail, unaDireccion, unaDescripcion, false, unNombre, unNivelCol, unNombre, 31/12/2014, 10, 31/12/2014, 1', unHistorial.toString()
    }

}
