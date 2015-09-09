package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.StringUtils;

import org.apache.commons.validator.EmailValidator;

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class DictadoController {
	
	def mailService
	def messageSource
	def emailValidator = EmailValidator.getInstance()

    static allowedMethods = [aprobar: "POST", update: "PUT", delete: "DELETE"]

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
				flash.message = message(code: 'default.created.message', args: [message(code: 'dictadoInstance.label', default: 'Dictado'), dictadoInstance.nombre])
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Dictado.label', default: 'Dictado'), dictadoInstance.nombre])
                redirect dictadoInstance
            }
            '*'{ respond dictadoInstance, [status: OK] }
        }
    }
	
	@Transactional
	def aprobar(Dictado dictadoInstance) {
		for(Inscripto i : dictadoInstance?.inscriptos) {
			def notaId = params.get('nota_' + i.personaId) 
			def aprobado = false
			if(notaId != i.nota?.id) {
				if(notaId == null || notaId == "null" || notaId == "") {
					i.nota = null
				} else {
					i.nota = Calificacion.get(notaId)
					if(i.nota.nombre == 'APROBADO') {
						procesarDictadoAprobado(i.personaId,dictadoInstance)
					} else if(i.nota.nombre == 'DESAPROBADO') {
						procesarDictadoNoAprobado(i.personaId,dictadoInstance)
					}
				}
				i.save flush:true
			}
		}
		
		return [dictadoInstance: dictadoInstance, sucessMessage: message(code: 'default.aprobacionDictado.success.message', args:[dictadoInstance.nombre])]
	}

	private procesarDictadoAprobado(Long personaId, Dictado dictado) {
		Persona p = Persona.get(personaId)
		p.addToDictadosAprobados(dictado.id);
		p.removeFromDictadosAnotados(dictado.id)
		p.save flush:true
		Historial h = new Historial(p, dictado.curso, new Date());
		h.save flush:true
		
		// Send message
		if (p.mail != null && !(p.mail instanceof ConfigObject) &&
            !StringUtils.isBlank(p.mail) && emailValidator.isValid(p.mail)) {
			log.debug("Intentando enviar el mensaje de aprobacion de dictado a travez del servidor de correo")

			try {
				mailService.sendMail {
					to p.mail
					from message(code:"default.aprobacionDictado.email.from.nombre") + " <" + message(code:"default.aprobacionDictado.email.from.direccion") + ">"
					replyTo message(code:"default.aprobacionDictado.email.from.direccion")
					subject message(code:"default.aprobacionDictado.email.asunto.aprobado")
					body message(code:"default.aprobacionDictado.email.from.body.aprobado",args:[dictado.nombre, dictado.curso.nombre])
				}

				log.debug("Se ha enviado el mensaje satisfactoriamente")
				
			} catch (Exception e) {
				log.error("No se pudo enviar el email de notificacion de dictado aprobado.", e)
				//flash.message = "${message(code: 'default.aprobacionDictado.email.send.fail')}"
			}
		} else {
			log.debug("Error al enviar notificacion - direccion de correo invalida del DNI: " + p.documentoNumero)
		}
	}
	
	private procesarDictadoNoAprobado(Long personaId, Dictado dictado) {
		Persona p = Persona.get(personaId)
		if(p.dictadosAprobados?.contains(dictado.id)) {
			p.removeFromDictadosAprobados(dictado.id)
		}
		p.removeFromDictadosAnotados(dictado.id)
		p.save flush:true
		Historial h = new Historial(p, dictado.curso, new Date());
		h.save flush:true	
	}

	
    @Transactional
    def delete(Dictado dictadoInstance) {
		
        if (dictadoInstance == null) {
            notFound()
            return
        }
		
		if(dictadoInstance.inscriptos?.size() > 0 || tieneAprobados(dictadoInstance)) {
			dictadoInstance.errors.reject('ar.org.scouts.sifs.Dictado.delete.inscriptos',
			'El Dictado no se puede eliminar ya que contiene Inscriptos')
		}
		
		if (dictadoInstance.hasErrors()) {
			respond dictadoInstance.errors, view:'show'
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
	
	@Transactional
	def cerrar(Dictado dictadoInstance) {
		dictadoInstance.status = DictadoStatus.findByNombre("CERRADO")
		dictadoInstance.save flush:true
		
		flash.message = message(code: 'default.dictado.success.closed', args: [dictadoInstance.nombre])
		redirect action: "index", controller: "aprobacionCurso"
	}
	
	/**
	 * Verifica si el dictado tiene inscriptos ya aprobados
	 * @param d
	 * @return
	 */
	def boolean tieneAprobados(Dictado d) {
		def c = Persona.executeQuery("SELECT p FROM Persona p JOIN p.dictadosAprobados pa WHERE pa.id = :dictadoId", [dictadoId:d.id],[max:1])
		if(c?.size > 0) {
			return true
		}
		return false
	}
	
}
