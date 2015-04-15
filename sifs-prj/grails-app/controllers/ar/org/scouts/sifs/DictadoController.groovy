package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class DictadoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Dictado.list(params), model:[dictadoInstanceCount: Dictado.count()]
    }

    def show(Dictado dictadoInstance) {
        respond dictadoInstance
    }

    def create() {
        respond new Dictado(params)
    }

    @Transactional
    def save(Dictado dictadoInstance) {
        if (dictadoInstance == null) {
            notFound()
            return
        }

        if (dictadoInstance.hasErrors()) {
            respond dictadoInstance.errors, view:'create'
            return
        }

        dictadoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dictadoInstance.label', default: 'Dictado'), dictadoInstance.id])
                redirect dictadoInstance
            }
            '*' { respond dictadoInstance, [status: CREATED] }
        }
    }

    def edit(Dictado dictadoInstance) {
        respond dictadoInstance
    }

    @Transactional
    def update(Dictado dictadoInstance) {
        if (dictadoInstance == null) {
            notFound()
            return
        }

        if (dictadoInstance.hasErrors()) {
            respond dictadoInstance.errors, view:'edit'
            return
        }

        dictadoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Dictado.label', default: 'Dictado'), dictadoInstance.id])
                redirect dictadoInstance
            }
            '*'{ respond dictadoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Dictado dictadoInstance) {

        if (dictadoInstance == null) {
            notFound()
            return
        }

        dictadoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Dictado.label', default: 'Dictado'), dictadoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dictadoInstance.label', default: 'Dictado'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
