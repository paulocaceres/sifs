package sifs.prj

import com.sifs.prj.Contenido;
import com.sifs.prj.Curso;
import com.sifs.prj.Inscripto;
import com.sifs.prj.Nivel;
import com.sifs.prj.Persona;
import com.sifs.prj.Plan;
import com.sifs.prj.Provincia;
import com.sifs.prj.Zona;

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Inscripto)
class InscriptoSpec {

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
		
		def unInscripto = new Inscripto(curso: unCurso,
										fecha: Date.parse('dd/MM/yyyy', '31/12/2014'))
		
		assertEquals 'unNombre, unaDescripcion, 31/12/2014, unNombre, unNombre, unaDescripcion, 1, unNombre, null, unDocumentoNumero, unNombre, unApellido, unMail, unaDireccion, unaDescripcion, false, unNombre, unNivelCol, unNombre, 31/12/2014, 10, 31/12/2014', unInscripto.toString()
    }

}
