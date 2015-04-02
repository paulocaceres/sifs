package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TituloController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Titulo.list(params), model:[tituloInstanceCount: Titulo.count()]
    }

    def show(Titulo tituloInstance) {
        respond tituloInstance
    }

    def create() {
        respond new Titulo(params)
    }

    @Transactional
    def save(Titulo tituloInstance) {
        if (tituloInstance == null) {
            notFound()
            return
        }

        if (tituloInstance.hasErrors()) {
            respond tituloInstance.errors, view:'create'
            return
        }

        tituloInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tituloInstance.label', default: 'Titulo'), tituloInstance.id])
                redirect tituloInstance
            }
            '*' { respond tituloInstance, [status: CREATED] }
        }
    }

    def edit(Titulo tituloInstance) {
        respond tituloInstance
    }

    @Transactional
    def update(Titulo tituloInstance) {
        if (tituloInstance == null) {
            notFound()
            return
        }

        if (tituloInstance.hasErrors()) {
            respond tituloInstance.errors, view:'edit'
            return
        }

        tituloInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Titulo.label', default: 'Titulo'), tituloInstance.nombre])
                redirect tituloInstance
            }
            '*'{ respond tituloInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Titulo tituloInstance) {

        if (tituloInstance == null) {
            notFound()
            return
        }

        tituloInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Titulo.label', default: 'Titulo'), tituloInstance.nombre])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tituloInstance.label', default: 'Titulo'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
