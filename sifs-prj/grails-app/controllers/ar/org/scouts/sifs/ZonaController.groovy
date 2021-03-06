package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
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

		
         def nombre = params.get('nombre');
         if (!checkNombreRegex(nombre)) {
			zonaInstance.errors.rejectValue('nombre', 'ar.org.scouts.sifs.Zona.nombre.error',
					'El nombre no cumple con lo requisitos')
			}
		
        if (zonaInstance.hasErrors()) {
            respond zonaInstance.errors, view:'create'
            return
        }

        zonaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'zonaInstance.label', default: 'Zona'), zonaInstance.numero])
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'zonaInstance.label', default: 'Zona'), zonaInstance.numero])
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

		if (Persona.findByZona(zonaInstance) != null) {
			flash.message = message(code: 'default.not.deleted.message.referential.integrity.persona', args: [message(code: 'zonaInstance.label', default: 'Zona'), zonaInstance.nombre])
			redirect action:"show", id:zonaInstance.id, method:"GET"
			return
		}
		if (Distrito.findByZona(zonaInstance) != null) {
			flash.message = message(code: 'default.not.deleted.message.referential.integrity.distrito', args: [message(code: 'zonaInstance.label', default: 'Zona'), zonaInstance.nombre])
			redirect action:"show", id:zonaInstance.id, method:"GET"
			return
		}

		zonaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'zonaInstance.label', default: 'Zona'), zonaInstance.numero])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'zonaInstance.label', default: 'Zona'), params.numero])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	static boolean checkNombreRegex(String nombre) {
		def pattern = ~/^\s*[a-zA-ZáéíñóúüÁÉÍÑÓÚÜ0123456789,\s]+\s*$/
		nombre && pattern.matcher(nombre).matches()
	}
}
