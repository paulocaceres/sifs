package ar.org.scouts.sifs

import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional;
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
			render view:'notfound', model:[message: message(code: 'default.cursosAprobados.not.found.message'), entityName: 'Dictados Aprobados']
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
			render view:'notfound', model:[message: message(code: 'default.cursosAnotados.not.found.message'), entityName: 'Dictados Anotados']
		}
	}
	
	@Transactional
	def desinscribir() {
		def dictadoIds = params.list("dictadoCheckBox")
		if(dictadoIds?.size() > 0) {
			def persona = Persona.get(springSecurityService.currentUser.id);
			def inscripto = Inscripto.findByPersonaId(persona.id)
			def dictadoList = dictadoIds.collect { Dictado.get(it) }
			dictadoList.each() {
				it.removeFromInscriptos(inscripto)
				inscripto.save flush:true
				it.cupo = it.cupo + 1
				it.save flush:true
				persona.removeFromDictadosAnotados(it.id)
				inscripto.delete flush:true
			}
			persona.save flush:true
			return [dictadoInstanceList : dictadoList,  successMessage : message(code: 'default.cursosAnotados.desanotar.success.message')]
		} else {
		    render view:'anotados', model:[messageNotSelected: message(code: 'default.cursosAnotados.desanotar.notselected.message')]
		}	
	}
	
}
