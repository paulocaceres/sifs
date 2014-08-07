package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.converters.*
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class ZonaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Zona.list(params), model:[zonaInstanceCount: Zona.count()]
    }

    def show(Zona zonaInstance) {
        respond zonaInstance
    }

    def create() {
        respond new Zona(params)
    }

    @Transactional
    def save(Zona zonaInstance) {
        if (zonaInstance == null) {
            notFound()
            return
        }

        if (zonaInstance.hasErrors()) {
            respond zonaInstance.errors, view:'create'
            return
        }

        zonaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'zonaInstance.label', default: 'Zona'), zonaInstance.id])
                redirect zonaInstance
            }
            '*' { respond zonaInstance, [status: CREATED] }
        }
    }

    def edit(Zona zonaInstance) {
        respond zonaInstance
    }

    @Transactional
    def update(Zona zonaInstance) {
        if (zonaInstance == null) {
            notFound()
            return
        }

        if (zonaInstance.hasErrors()) {
            respond zonaInstance.errors, view:'edit'
            return
        }

        zonaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Zona.label', default: 'Zona'), zonaInstance.id])
                redirect zonaInstance
            }
            '*'{ respond zonaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Zona zonaInstance) {

        if (zonaInstance == null) {
            notFound()
            return
        }

        zonaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Zona.label', default: 'Zona'), zonaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'zonaInstance.label', default: 'Zona'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

	def ajaxGetDistritos() {
		def zona = Zona.get(params.id)
		render zona?.distritos as JSON
	}

}
