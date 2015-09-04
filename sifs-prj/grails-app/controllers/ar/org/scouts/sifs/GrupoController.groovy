package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional



@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class GrupoController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Grupo.list(params), model:[grupoInstanceCount: Grupo.count()]
	}

	def show(Grupo grupoInstance) {
		respond grupoInstance
	}

	def create() {
		respond new Grupo(params)
	}

	@Transactional
	def save(Grupo grupoInstance) {
		if (grupoInstance == null) {
			notFound()
			return
		}

		def nombre = params.get('nombre');
		if (!checkNombreRegex(nombre)) {
			grupoInstance.errors.rejectValue('nombre', 'ar.org.scouts.sifs.Grupo.nombre.error',
				'El nombre no cumple con lo requisitos')
		}
		
		if (grupoInstance.hasErrors()) {
			respond grupoInstance.errors, view:'create'
			return
		}

		grupoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'grupoInstance.label', default: 'Grupo'), grupoInstance.numero])
				redirect grupoInstance
			}
			'*' { respond grupoInstance, [status: CREATED] }
		}
	}

	def edit(Grupo grupoInstance) {
		respond grupoInstance
	}

	@Transactional
	def update(Grupo grupoInstance) {
		if (grupoInstance == null) {
			notFound()
			return
		}

		if (grupoInstance.hasErrors()) {
			respond grupoInstance.errors, view:'edit'
			return
		}

		grupoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'grupoInstance.label', default: 'Grupo'), grupoInstance.numero])
				redirect grupoInstance
			}
			'*'{ respond grupoInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Grupo grupoInstance) {

		if (grupoInstance == null) {
			notFound()
			return
		}

		if (Persona.findByGrupo(grupoInstance) != null) {
			flash.message = message(code: 'default.not.deleted.message.referential.integrity.persona', args: [message(code: 'grupoInstance.label', default: 'Grupo'), grupoInstance.nombre])
			redirect action:"show", id:grupoInstance.id, method:"GET"
			return
		}

		grupoInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'grupoInstance.label', default: 'Grupo'), grupoInstance.numero])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoInstance.label', default: 'Grupo'), params.numero])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}

	static boolean checkNombreRegex(String nombre) {
		def pattern = ~/^\s*[a-zA-ZáéíñóúüÁÉÍÑÓÚÜ0123456789,\s]+\s*$/
		nombre && pattern.matcher(nombre).matches()
	}
	
	def jsonZonaDistrito() {
		
		Zona zona = null;
		Distrito distrito = null;
		
		if (params.int('dstrtParam') > 0) {
			distrito = Distrito.get(params.dstrtParam);
			zona = distrito.getZona();
		}
		
		if ((zona == null) && (params.int('znParam') > 0)) {
			zona = Zona.get(params.znParam);
		}
		

		Long znSlctd = null;
		Set dstrts = new HashSet();
		if (zona != null) {
			znSlctd = zona.getId();
			dstrts = Distrito.findAllByZona(zona);
		} else {
			dstrts = Distrito.list();
		}
	
		Long dstrtSlctd = null;
		if (distrito != null) {
			dstrtSlctd = distrito.getId();
		}

		def respuesta = [zonaSelected: znSlctd, distritos: dstrts.sort{it.nombre}, distritoSelected: dstrtSlctd];
		render respuesta as JSON;
	}

	
}


