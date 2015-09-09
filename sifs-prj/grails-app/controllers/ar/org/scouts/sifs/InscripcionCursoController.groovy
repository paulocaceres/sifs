package ar.org.scouts.sifs

import static org.springframework.http.HttpStatus.*
//import org.springframework.web.servlet.m

import javax.transaction.UserTransaction;

import ar.org.scouts.sifs.Dictado;
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured
import ar.org.scouts.sifs.Curso;
import ar.org.scouts.sifs.Persona;
import ar.org.scouts.sifs.PlanCurso;

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
				}
				inscripcionPlanCursoResultList.each { planCurso ->
					if(planCurso.curso != null && !planCurso.curso.dictados.isEmpty()) {
						//TODO: Ver este each() anidado
						planCurso.curso.dictados.each {
							if(persona.id != it.formador.id && !it.status?.nombre == 'CERRADO' && 
								!resultList?.contains(planCurso.curso) ) {
									resultList.add(planCurso.curso)
							}
						}
					}	
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
						status {
							ne("nombre", 'CERRADO')
						}
				}
				listaDictados?.each() {
					if(persona.id != it.formador.id && !idsCursosTentativos?.contains(it.curso.id)) {
						idsCursosTentativos.add(it.curso.id)
					}
					//if(it.correlativas?.size() == 0 || it.correlativas.containsAll(persona.cursosAprobados)) {
						//ofertaCursos.add(it)
					//}	
				}
				def criteria = PlanCurso.createCriteria()
				def temporalPlanCursoResultList = criteria.list {
					'in' ("curso.id", idsCursosTentativos)
				}
				//TODO: Verificar la correlatividad en forma fuerte
				
				temporalPlanCursoResultList.each {
					ofertaCursos.add(it.curso)
				}
				inscripcionCursoInstanceList = ofertaCursos
			}
			return [inscripcionInstanceList : inscripcionCursoInstanceList, usuarioLogueado:persona]
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
			def cursoIds = params.list("cursosDisponiblesIds")
			def dictadoIds = []
			if(cursoIds?.size() > 0) {
				cursoIds.each {
					if(params.get('dictadoGroup' + it) != null) {
						dictadoIds.add(params.get('dictadoGroup' + it))
					}					
				}
				if(dictadoIds?.size() > 0) {
					cursosSeleccionadosInstance = dictadoIds.collect { Dictado.get(it) }
					cursosSeleccionadosInstance.each() {
						persona.addToDictadosAnotados(it.id)
						Inscripto alumno = new Inscripto()
						alumno.personaId = persona.id
						alumno.nombre = persona.nombre
						alumno.apellido = persona.apellido
						alumno.documento = persona.documentoNumero
						alumno.nota = null
						alumno.dictado = it
						alumno.save flush:true	
						it.addToInscriptos(alumno)
						it.cupo = it.cupo - 1 
						it.save flush:true
					}
					persona.save flush:true
					successInscripcionMessage = message(code: 'default.inscripcionCursoSuccess.message')
				}
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
			ids.add(it)
		}
		p.dictadosAnotados.each() {
			ids.add(it)
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
