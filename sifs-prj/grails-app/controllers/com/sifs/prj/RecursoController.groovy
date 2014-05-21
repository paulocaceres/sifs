package com.sifs.prj



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RecursoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Recurso.list(params), model:[recursoInstanceCount: Recurso.count()]
    }

    def show(Recurso recursoInstance) {
        respond recursoInstance
    }

    def create() {
        respond new Recurso(params)
    }

    @Transactional
    def save(Recurso recursoInstance) {
        if (recursoInstance == null) {
            notFound()
            return
        }

        if (recursoInstance.hasErrors()) {
            respond recursoInstance.errors, view:'create'
            return
        }

        recursoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'recursoInstance.label', default: 'Recurso'), recursoInstance.id])
                redirect recursoInstance
            }
            '*' { respond recursoInstance, [status: CREATED] }
        }
    }

    def edit(Recurso recursoInstance) {
        respond recursoInstance
    }

    @Transactional
    def update(Recurso recursoInstance) {
        if (recursoInstance == null) {
            notFound()
            return
        }

        if (recursoInstance.hasErrors()) {
            respond recursoInstance.errors, view:'edit'
            return
        }

        recursoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Recurso.label', default: 'Recurso'), recursoInstance.id])
                redirect recursoInstance
            }
            '*'{ respond recursoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Recurso recursoInstance) {

        if (recursoInstance == null) {
            notFound()
            return
        }

        recursoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Recurso.label', default: 'Recurso'), recursoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'recursoInstance.label', default: 'Recurso'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
