package ar.org.scouts.sifs

import javax.transaction.UserTransaction;

import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured
import ar.org.scouts.sifs.Curso;

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class AsignacionCupoController {
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	transient springSecurityService

    def index() {
		
		def asignacionCupoInstanceList = null
		def criteria = Curso.createCriteria()
		asignacionCupoInstanceList = criteria.list {
			ge("fecha", new Date().clearTime()+1)
		}	
		return [cursoInstanceList : asignacionCupoInstanceList]
	}
	
	def asignar(Curso cursoInstance) {
		respond cursoInstance
	}
}
