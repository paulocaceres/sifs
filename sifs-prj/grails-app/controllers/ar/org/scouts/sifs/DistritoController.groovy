package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DistritoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Distrito.list(params), model:[distritoInstanceCount: Distrito.count()]
    }

    def show(Distrito distritoInstance) {
        respond distritoInstance
    }

    def create() {
        respond new Distrito(params)
    }

    @Transactional
    def save(Distrito distritoInstance) {
        if (distritoInstance == null) {
            notFound()
            return
        }

        if (distritoInstance.hasErrors()) {
            respond distritoInstance.errors, view:'create'
            return
        }

        distritoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'distritoInstance.label', default: 'Distrito'), distritoInstance.nombre])
                redirect distritoInstance
            }
            '*' { respond distritoInstance, [status: CREATED] }
        }
    }

    def edit(Distrito distritoInstance) {
        respond distritoInstance
    }

    @Transactional
    def update(Distrito distritoInstance) {
        if (distritoInstance == null) {
            notFound()
            return
        }

        if (distritoInstance.hasErrors()) {
            respond distritoInstance.errors, view:'edit'
            return
        }

        distritoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'distrito.label', default: 'Distrito'), distritoInstance.nombre])
                redirect distritoInstance
            }
            '*'{ respond distritoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Distrito distritoInstance) {

        if (distritoInstance == null) {
            notFound()
            return
        }

        distritoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'distrito.label', default: 'Distrito'), distritoInstance.nombre])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'distritoInstance.label', default: 'Distrito'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
