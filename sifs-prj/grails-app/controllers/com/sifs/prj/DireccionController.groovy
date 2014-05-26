package com.sifs.prj



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class DireccionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Direccion.list(params), model:[direccionInstanceCount: Direccion.count()]
    }

    def show(Direccion direccionInstance) {
        respond direccionInstance
    }

    def create() {
        respond new Direccion(params)
    }

    @Transactional
    def save(Direccion direccionInstance) {
        if (direccionInstance == null) {
            notFound()
            return
        }

        if (direccionInstance.hasErrors()) {
            respond direccionInstance.errors, view:'create'
            return
        }

        direccionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'direccionInstance.label', default: 'Direccion'), direccionInstance.id])
                redirect direccionInstance
            }
            '*' { respond direccionInstance, [status: CREATED] }
        }
    }

    def edit(Direccion direccionInstance) {
        respond direccionInstance
    }

    @Transactional
    def update(Direccion direccionInstance) {
        if (direccionInstance == null) {
            notFound()
            return
        }

        if (direccionInstance.hasErrors()) {
            respond direccionInstance.errors, view:'edit'
            return
        }

        direccionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Direccion.label', default: 'Direccion'), direccionInstance.id])
                redirect direccionInstance
            }
            '*'{ respond direccionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Direccion direccionInstance) {

        if (direccionInstance == null) {
            notFound()
            return
        }

        direccionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Direccion.label', default: 'Direccion'), direccionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'direccionInstance.label', default: 'Direccion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
