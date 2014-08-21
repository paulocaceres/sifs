package ar.org.scouts.sifs

import grails.converters.JSON

class DistritoController {

    def index() { }
	
	
	def ajaxGetGrupos() {
		def distrito = Distrito.get(params.id)
		render distrito?.grupos as JSON
	}


}
