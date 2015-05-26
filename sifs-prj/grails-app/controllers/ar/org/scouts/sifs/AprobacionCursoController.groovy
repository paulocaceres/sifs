package ar.org.scouts.sifs

import grails.plugins.springsecurity.Secured
import static org.springframework.http.HttpStatus.*
import ar.org.scouts.sifs.Persona;

@Secured(['IS_AUTHENTICATED_FULLY'])
class AprobacionCursoController {
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	transient springSecurityService

    def index() { 
		def supervisor = Persona.get(springSecurityService.currentUser.id);
		
		if(supervisor != null){
			def criteria = Dictado.createCriteria()
			def listaDictados = criteria.list {
				and {
					eq("formador.id", supervisor.id)
					status {
						eq("nombre", 'ABIERTO')
					}
				}
			}
			if(!listaDictados.empty) {
				return [dictadoInstanceList : listaDictados]
			} else {
				render view:'notfound', model:[message: message(code: 'default.aprobacionCursos.not.found.message')]
			}
		} else {
		    redirect(uri:"500")
		}
	}
	
	def show(Dictado dictadoInstance) {
		if(dictadoInstance.inscriptos.size() > 0) {
			def personaList = dictadoInstance.inscriptos
			return [dictadoInstance : dictadoInstance, inscriptosInstanceList: personaList]
		} else {
			render view:'notfound', model:[message: message(code: 'default.aprobacionCursos.not.found.message')]
		}
	}

}
