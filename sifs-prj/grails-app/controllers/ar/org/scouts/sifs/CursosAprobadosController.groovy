package ar.org.scouts.sifs

import grails.plugins.springsecurity.Secured
import static org.springframework.http.HttpStatus.*
import ar.org.scouts.sifs.Persona;

@Secured(['IS_AUTHENTICATED_FULLY'])
class CursosAprobadosController {
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	transient springSecurityService

    def index() { 
		def persona = Persona.get(springSecurityService.currentUser.id);
		def dictadoList = []
		if(persona != null && persona.dictadosAprobados?.size() > 0){
			persona.dictadosAprobados.each() {
				dictadoList.add(Dictado.get(it))
			}
			return [dictadoInstanceList : dictadoList]
		} else {
			render view:'notfound', model:[message: message(code: 'default.cursosAprobados.not.found.message')]
		}
	}
	
	def anotados() {
		def persona = Persona.get(springSecurityService.currentUser.id);
		def dictadoList = []
		if(persona != null && persona.dictadosAnotados?.size() > 0){
			persona.dictadosAnotados.each() {
				dictadoList.add(Dictado.get(it))
			}
			return [dictadoInstanceList : dictadoList]
		} else {
			render view:'notfound', model:[message: message(code: 'default.cursosAnotados.not.found.message')]
		}
	}
	
}
