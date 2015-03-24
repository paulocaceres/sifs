package ar.org.scouts.sifs

import static org.springframework.http.HttpStatus.*
import ar.org.scouts.sifs.Historial;
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class HistorialController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
		params.listaPersonas = Persona.list();
		params.listaCursos = Curso.list();
		respond new Historial(params)
    }

    def show(Historial historialInstance) {
        respond historialInstance
    }

	def list() {
		respond Historial.list(), model:[historialInstanceCount: Historial.count()]
	}
	
//    def create() {
//        respond new Historial(params)
//    }

    @Transactional
    def save(Historial historialInstance) {
        if (historialInstance == null) {
            notFound()
            return
        }

        if (historialInstance.hasErrors()) {
            respond historialInstance.errors, view:'create'
            return
        }

        historialInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'historialInstance.label', default: 'Historial'), historialInstance.id])
                redirect historialInstance
            }
            '*' { respond historialInstance, [status: CREATED] }
        }
    }

    def edit(Historial historialInstance) {
        respond historialInstance
    }

    @Transactional
    def update(Historial historialInstance) {
        if (historialInstance == null) {
            notFound()
            return
        }

        if (historialInstance.hasErrors()) {
            respond historialInstance.errors, view:'edit'
            return
        }

        historialInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Historial.label', default: 'Historial'), historialInstance.id])
                redirect historialInstance
            }
            '*'{ respond historialInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Historial historialInstance) {

        if (historialInstance == null) {
            notFound()
            return
        }

        historialInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Historial.label', default: 'Historial'), historialInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'historialInstance.label', default: 'Historial'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
