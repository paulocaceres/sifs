package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional



@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class DistritoController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Distrito.list(params), model:[distritoInstanceCount: Distrito.count()]
	}

	def show(Distrito distritoInstance) {
		respond distritoInstance
	}

	def create() {
		respond new Distrito(params)
	}

	@Transactional
	def save(Distrito distritoInstance) {
		if (distritoInstance == null) {
			notFound()
			return
		}
		
		//Validamos que no se cree un Distrito repetido (nombre en la misma zona)
		def laZona = distritoInstance.zona
		def distritos = laZona? Distrito.findAllByZona(laZona) : []
		if(distritos.size() > 0) {
			def boolean repetido = false
			distritos.each{
				if(it.nombre?.equalsIgnoreCase(distritoInstance.nombre)) {
					distritoInstance.errors.rejectValue('nombre', 'ar.org.scouts.sifs.Distrito.nombre.repetido',
						'Nombre de Distrito repetido en la zona elegida')
				}
			}
		}
		
		if (distritoInstance.hasErrors()) {
			respond distritoInstance.errors, view:'create'
			return
		}

		distritoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'distritoInstance.label', default: 'Distrito'), distritoInstance.nombre])
				redirect distritoInstance
			}
			'*' { respond distritoInstance, [status: CREATED] }
		}
	}

	def edit(Distrito distritoInstance) {
		respond distritoInstance
	}

	@Transactional
	def update(Distrito distritoInstance) {
		if (distritoInstance == null) {
			notFound()
			return
		}
		
		//Validamos que no se cree un Distrito repetido (nombre en la misma zona)
		def laZona = distritoInstance.zona
		def distritos = laZona? Distrito.findAllByZona(laZona) : []
		if(distritos.size() > 0) {
			def boolean repetido = false
			distritos.each{
				if(it.id != distritoInstance.id && it.nombre?.equalsIgnoreCase(distritoInstance.nombre)) {
					distritoInstance.errors.rejectValue('nombre', 'ar.org.scouts.sifs.Distrito.nombre.repetido',
						'Nombre de Distrito repetido en la zona elegida')
				}
			}
		}

		if (distritoInstance.hasErrors()) {
			respond distritoInstance.errors, view:'edit'
			return
		}

		distritoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'distritoInstance.label', default: 'Distrito'), distritoInstance.nombre])
				redirect distritoInstance
			}
			'*'{ respond distritoInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Distrito distritoInstance) {

		if (distritoInstance == null) {
			notFound()
			return
		}

		if (Persona.findByDistrito(distritoInstance) != null) {
			flash.message = message(code: 'default.not.deleted.message.referential.integrity.persona', args: [message(code: 'distritoInstance.label', default: 'Distrito'), distritoInstance.nombre])
			redirect action:"show", id:distritoInstance.id, method:"GET"
			return
		}
		if (Grupo.findByDistrito(distritoInstance) != null) {
			flash.message = message(code: 'default.not.deleted.message.referential.integrity.grupo', args: [message(code: 'distritoInstance.label', default: 'Distrito'), distritoInstance.nombre])
			redirect action:"show", id:distritoInstance.id, method:"GET"
			return
		}

		distritoInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'distritoInstance.label', default: 'Distrito'), distritoInstance.nombre])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'distritoInstance.label', default: 'Distrito'), params.nombre])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
