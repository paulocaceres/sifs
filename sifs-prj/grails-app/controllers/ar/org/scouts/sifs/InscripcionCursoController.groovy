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
		def inscripcionPlanCursoResultList = null
		def resultList = []
		
		if(persona != null){
			if(persona.dictadosAprobados?.size() == 0 && persona.dictadosAnotados?.size() == 0) {
				def criteria = PlanCurso.createCriteria()
				inscripcionPlanCursoResultList = criteria.list {
					sizeEq("correlativas", 0)
					//ne("cupo", 0)
					//ge("fecha", new Date().clearTime()+1)
				}
				inscripcionPlanCursoResultList.each {
					resultList.add(it.curso)
				}
				inscripcionCursoInstanceList = resultList
			} else {
			 	def ofertaCursos = []
				def idsCursosTentativos = []
				def dictadosAprobadosIds = getDictadosAprobadosAnotadosIds(persona) 
				def c = Dictado.createCriteria();
				def listaDictados = c.list {
						not {'in'("id",dictadosAprobadosIds)}	
						ne("cupo", 0)
						ge("fecha", new Date().clearTime()+1)
				}
				listaDictados?.each() {
					idsCursosTentativos.add(it.curso.id)
					//if(it.correlativas?.size() == 0 || it.correlativas.containsAll(persona.cursosAprobados)) {
						//ofertaCursos.add(it)
					//}	
				}
				def criteria = PlanCurso.createCriteria()
				def temporalPlanCursoResultList = criteria.list {
					'in' ("curso.id", idsCursosTentativos)
					//ne("cupo", 0)
					//ge("fecha", new Date().clearTime()+1)
				}
				//TODO: Verificar la correlatividad en forma fuerte
				
				temporalPlanCursoResultList.each {
					ofertaCursos.add(it.curso)
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
	
	def getDictadosAprobadosAnotadosIds(Persona p) {
		def ids = []
		p.dictadosAprobados.each() {
			ids.add(it.id)
		}
		p.dictadosAnotados.each() {
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
