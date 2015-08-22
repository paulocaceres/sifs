package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import ar.org.scouts.sifs.Provincia;
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class ProvinciaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Provincia.list(params), model:[provinciaInstanceCount: Provincia.count()]
    }

    def show(Provincia provinciaInstance) {
        respond provinciaInstance
    }

    def create() {
        respond new Provincia(params)
    }

    @Transactional
    def save(Provincia provinciaInstance) {
        if (provinciaInstance == null) {
            notFound()
            return
        }

        if (provinciaInstance.hasErrors()) {
            respond provinciaInstance.errors, view:'create'
            return
        }

        provinciaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'provinciaInstance.label', default: 'Provincia'), provinciaInstance.id])
                redirect provinciaInstance
            }
            '*' { respond provinciaInstance, [status: CREATED] }
        }
    }

    def edit(Provincia provinciaInstance) {
        respond provinciaInstance
    }

    @Transactional
    def update(Provincia provinciaInstance) {
        if (provinciaInstance == null) {
            notFound()
            return
        }

        if (provinciaInstance.hasErrors()) {
            respond provinciaInstance.errors, view:'edit'
            return
        }

        provinciaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Provincia.label', default: 'Provincia'), provinciaInstance.id])
                redirect provinciaInstance
            }
            '*'{ respond provinciaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Provincia provinciaInstance) {

        if (provinciaInstance == null) {
            notFound()
            return
        }

        provinciaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Provincia.label', default: 'Provincia'), provinciaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'provinciaInstance.label', default: 'Provincia'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
