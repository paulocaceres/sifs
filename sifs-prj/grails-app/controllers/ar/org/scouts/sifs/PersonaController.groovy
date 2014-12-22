package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional
import ar.org.scouts.sifs.security.PersonaRol
import ar.org.scouts.sifs.security.Rol

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class PersonaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Persona.list(params), model:[personaInstanceCount: Persona.count()]
    }

    def show(Persona personaInstance) {
        respond personaInstance
    }

    def create() {
		params.zona = new Zona()
		params.direccion = new Direccion()
		params.direccion.provincia = new Provincia()
        respond new Persona(params)
    }

    @Transactional
    def save(Persona personaInstance) {
        if (personaInstance == null) {
            notFound()
            return
        }

        if (personaInstance.hasErrors()) {
            respond personaInstance.errors, view:'create'
            return
        }


        personaInstance.save flush:true


		personaInstance.authorities.clear()
		params.each {
			name, value ->
			def rolId = name.find(/^rolRaw\[(\d+)\]$/) {
				match, pid -> return pid
			}
			if (rolId) {
				def rol = Rol.get(rolId as long)
				PersonaRol.create(personaInstance, rol, true)
			}
	
		}

		personaInstance.save flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personaInstance.label', default: 'Persona'), personaInstance.id])
                redirect personaInstance
            }
            '*' { respond personaInstance, [status: CREATED] }
        }
    }

    def edit(Persona personaInstance) {
        respond personaInstance
    }

    @Transactional
    def update(Persona personaInstance) {
        if (personaInstance == null) {
            notFound()
            return
        }

        if (personaInstance.hasErrors()) {
            respond personaInstance.errors, view:'edit'
            return
        }

        personaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Persona.label', default: 'Persona'), personaInstance.id])
                redirect personaInstance
            }
            '*'{ respond personaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Persona personaInstance) {

        if (personaInstance == null) {
            notFound()
            return
        }

        personaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Persona.label', default: 'Persona'), personaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'personaInstance.label', default: 'Persona'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


	static ArrayList<Persona> superiores() {
		def superiores = Persona.list()
        return superiores
    }

	
}
