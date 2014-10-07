package ar.org.scouts.sifs

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class DistritoController {

    def index() { }
	
	
	def ajaxGetGrupos() {
		def distrito = Distrito.get(params.id)
		render distrito?.grupos as JSON
	}


}
