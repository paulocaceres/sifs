package com.sifs.prj



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class DocumentoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Documento.list(params), model:[documentoInstanceCount: Documento.count()]
    }

    def show(Documento documentoInstance) {
        respond documentoInstance
    }

    def create() {
        respond new Documento(params)
    }

    @Transactional
    def save(Documento documentoInstance) {
        if (documentoInstance == null) {
            notFound()
            return
        }

        if (documentoInstance.hasErrors()) {
            respond documentoInstance.errors, view:'create'
            return
        }

        documentoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'documentoInstance.label', default: 'Documento'), documentoInstance.id])
                redirect documentoInstance
            }
            '*' { respond documentoInstance, [status: CREATED] }
        }
    }

    def edit(Documento documentoInstance) {
        respond documentoInstance
    }

    @Transactional
    def update(Documento documentoInstance) {
        if (documentoInstance == null) {
            notFound()
            return
        }

        if (documentoInstance.hasErrors()) {
            respond documentoInstance.errors, view:'edit'
            return
        }

        documentoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Documento.label', default: 'Documento'), documentoInstance.id])
                redirect documentoInstance
            }
            '*'{ respond documentoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Documento documentoInstance) {

        if (documentoInstance == null) {
            notFound()
            return
        }

        documentoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Documento.label', default: 'Documento'), documentoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentoInstance.label', default: 'Documento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
