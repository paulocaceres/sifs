package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GrupoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Grupo.list(params), model:[grupoInstanceCount: Grupo.count()]
    }

    def show(Grupo grupoInstance) {
        respond grupoInstance
    }

    def create() {
        respond new Grupo(params)
    }

    @Transactional
    def save(Grupo grupoInstance) {
        if (grupoInstance == null) {
            notFound()
            return
        }

        if (grupoInstance.hasErrors()) {
            respond grupoInstance.errors, view:'create'
            return
        }

        grupoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'grupoInstance.label', default: 'Grupo'), grupoInstance.numero])
                redirect grupoInstance
            }
            '*' { respond grupoInstance, [status: CREATED] }
        }
    }

    def edit(Grupo grupoInstance) {
        respond grupoInstance
    }

    @Transactional
    def update(Grupo grupoInstance) {
        if (grupoInstance == null) {
            notFound()
            return
        }

        if (grupoInstance.hasErrors()) {
            respond grupoInstance.errors, view:'edit'
            return
        }

        grupoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupoInstance.numero])
                redirect grupoInstance
            }
            '*'{ respond grupoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Grupo grupoInstance) {

        if (grupoInstance == null) {
            notFound()
            return
        }

        grupoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupoInstance.numero])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoInstance.label', default: 'Grupo'), params.numero])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
