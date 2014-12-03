package ar.org.scouts.sifs

import static org.springframework.http.HttpStatus.*
//import org.springframework.web.servlet.m

import javax.transaction.UserTransaction;

import ar.org.scouts.sifs.Provincia;
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured
import ar.org.scouts.sifs.Curso;
import ar.org.scouts.sifs.Persona;

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class InscripcionCursoController {
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	transient springSecurityService

    def index() { 
		
		def persona = Persona.get(springSecurityService.currentUser.id);
		def inscripcionCursoInstanceList = null
		
		if(persona != null){
			if(persona.cursosAprobados?.size() == 0 && persona.cursosAnotados?.size() == 0) {
				def criteria = Curso.createCriteria()
				inscripcionCursoInstanceList = criteria.list {
					sizeEq("correlativas", 0)
				}
				
			} else {
			 	def ofertaCursos = []
				def cursosAprobadosIds = getCursosAprobadosAnotadosIds(persona) 
				def c = Curso.createCriteria();
				def listaCursos = c.list {
						not {'in'("id",cursosAprobadosIds)}	
				}
				listaCursos?.each() {
					if(it.correlativas?.size() == 0 || it.correlativas.containsAll(persona.cursosAprobados)) {
						ofertaCursos.add(it)
					}	
				}
				inscripcionCursoInstanceList = ofertaCursos
			}
			return [inscripcionInstanceList : inscripcionCursoInstanceList]
		} else {
		    redirect(uri:"500")
		}
	}
	
	@Transactional
	def save() {
		def persona = Persona.get(springSecurityService.currentUser.id);
		def cursosSeleccionadosInstance = null
		def successInscripcionMessage = null
		
		if (persona != null) {
			def cursoIds = params.list('cursosAnotadosIds')
			if(cursoIds.size() > 0) {
				cursosSeleccionadosInstance = cursoIds.collect { Curso.get(it) }
				cursosSeleccionadosInstance.each() {
					persona.addToCursosAnotados(it)
				}
				persona.save flush:true
				successInscripcionMessage = message(code: 'default.inscripcionCursoSuccess.message')
			} else {
				notSelected()
				return
			}
		}
		
		return [cursosInstanceList : cursosSeleccionadosInstance, customMessage: successInscripcionMessage, status: CREATED]
	}
	
	def getCursosAprobadosAnotadosIds(Persona p) {
		def ids = []
		p.cursosAprobados.each() {
			ids.add(it.id)
		}
		p.cursosAnotados.each() {
			ids.add(it.id)
		}
		return ids
	}
	
	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'cursoInstance.label', default: 'Curso'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
	
	protected void notSelected() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.inscripcion.not.selected.message')
				redirect action: "index", method: "GET"
			}
			'*'{ render status: ACCEPTED }
		}
	}
	
	
}
