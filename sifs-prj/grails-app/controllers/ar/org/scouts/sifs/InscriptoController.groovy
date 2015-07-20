package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import ar.org.scouts.sifs.Inscripto;
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class InscriptoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Inscripto.list(params), model:[inscriptoInstanceCount: Inscripto.count()]
    }

    def show(Inscripto inscriptoInstance) {
        respond inscriptoInstance
    }

    def create() {
        respond new Inscripto(params)
    }

    @Transactional
    def save(Inscripto inscriptoInstance) {
        if (inscriptoInstance == null) {
            notFound()
            return
        }

        if (inscriptoInstance.hasErrors()) {
            respond inscriptoInstance.errors, view:'create'
            return
        }

        inscriptoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'inscriptoInstance.label', default: 'Inscripto'), inscriptoInstance.personaId])
                redirect inscriptoInstance
            }
            '*' { respond inscriptoInstance, [status: CREATED] }
        }
    }

    def edit(Inscripto inscriptoInstance) {
        respond inscriptoInstance
    }

    @Transactional
    def update(Inscripto inscriptoInstance) {
        if (inscriptoInstance == null) {
            notFound()
            return
        }

        if (inscriptoInstance.hasErrors()) {
            respond inscriptoInstance.errors, view:'edit'
            return
        }

        inscriptoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Inscripto.label', default: 'Inscripto'), inscriptoInstance.id])
                redirect inscriptoInstance
            }
            '*'{ respond inscriptoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Inscripto inscriptoInstance) {

        if (inscriptoInstance == null) {
            notFound()
            return
        }

        inscriptoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Inscripto.label', default: 'Inscripto'), inscriptoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'inscriptoInstance.label', default: 'Inscripto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
