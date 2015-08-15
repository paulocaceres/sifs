package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional
import groovy.text.SimpleTemplateEngine
import ar.org.scouts.sifs.security.PersonaRol
import ar.org.scouts.sifs.security.Rol
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils



@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class PersonaController {

	def springSecurityService
	def mailService
	def messageSource
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


	@Secured(['ROLE_SUPERVISOR','ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		if (!springSecurityService.currentUser.hasRol(Rol.findByAuthority('ROLE_ADMIN'))) {
			def lista = Persona.findAllBySupervisor(springSecurityService.currentUser, params)
			respond lista, model:[personaInstanceCount: lista.size]
		} else {
			respond Persona.list(params), model:[personaInstanceCount: Persona.count()]
		}
    }

	def indexSupervised(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def lista = Persona.findAllBySupervisor(springSecurityService.currentUser, params)
		redirect(action: 'index', params: [personaInstanceList: lista, personaInstanceCount: lista.size])
	}


	@Secured(['ROLE_CURSANTE','ROLE_SUPERVISOR','ROLE_ADMIN'])
    def show(Persona personaInstance) {
		if ((personaInstance == null) || (personaInstance.id == null)) {
			personaInstance = springSecurityService.currentUser;
		}
        respond personaInstance
    }

	
	@Secured(['ROLE_SUPERVISOR','ROLE_ADMIN'])
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

		//Grupo, Zona y Distrito son obligatorios
		def rolesList = []
		def adminRol = Rol.findByAuthority('ROLE_ADMIN')
		params.each {
			name, value ->
			def rolId = name.find(/^rolRaw\[(\d+)\]$/) {
				match, pid -> return pid
			}
			if (rolId) {
				def rol = Rol.get(rolId as long)
				rolesList.add(rol)		
			}
		}
		if(!rolesList?.contains(adminRol)) {
			def zona = params.get('zona.id')
			def distrito = params.get('distrito.id')
			def grupo = params.get('grupo.id')
			if(zona=='null') { // || distrito == null || grupo == null) {
				personaInstance.errors.rejectValue('zona.id', 'ar.org.scouts.sifs.Persona.zona.nullable',
				'El campo Zona es obligatorio')
			}
			if(distrito=='null') {
				personaInstance.errors.rejectValue('distrito.id', 'ar.org.scouts.sifs.Persona.distrito.nullable',
				'El campo Distrito es obligatorio')
			}
			if(grupo=='null') {
				personaInstance.errors.rejectValue('grupo.id', 'ar.org.scouts.sifs.Persona.grupo.nullable',
				'El campo Distrito es obligatorio')
			}
		}
		
        if (personaInstance.hasErrors()) {
            respond personaInstance.errors, view:'create'
            return
        }
		
        personaInstance.save flush:true

		personaInstance.authorities.clear()
		rolesList.each {
			PersonaRol.create(personaInstance, it, true)
		}
//		params.each {
//			name, value ->
//			def rolId = name.find(/^rolRaw\[(\d+)\]$/) {
//				match, pid -> return pid
//			}
//			if (rolId) {
//				def rol = Rol.get(rolId as long)
//				PersonaRol.create(personaInstance, rol, true)
//			}
//		}
		
		personaInstance.save flush:true
		
		//Si no tiene roles, le asigno el rol cursante por default
		def cursante = Rol.findByAuthority('ROLE_CURSANTE')
		if(!personaInstance.hasRol(cursante)) {
			PersonaRol.create(personaInstance, cursante, true)
			personaInstance.save flush:true
		}
		
		String password = params.password	
		String urlSifs = generateLink()
		def emailMessage = null
		def conf = SpringSecurityUtils.securityConfig
		def body = conf.ui.personaCreada.emailBody
		if (body.contains('$')) {
			body = evaluate(body, [user: personaInstance, scoutpwd: password ,url: urlSifs])
		}
		
		try {
			mailService.sendMail {
				to personaInstance.mail
				from conf.ui.personaCreada.emailFrom
				subject conf.ui.personaCreada.emailSubject
				html body.toString()
			}
		} catch(Exception e) {
			log.error("Error durante el envio de email de notificacion de creacion de persona", e)
			emailMessage = "Hubo un error al intentar enviar el email de notificacion."
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personaInstance.label', default: 'Persona'), personaInstance.documentoNumero])
                redirect personaInstance
            }
            '*' { respond personaInstance, [status: CREATED, emailMessage: emailMessage] }
        }
    }

	
	@Secured(['ROLE_CURSANTE','ROLE_SUPERVISOR','ROLE_ADMIN'])
    def edit(Persona personaInstance) {
		if ((personaInstance == null) || (personaInstance.id == null)) {
			personaInstance = springSecurityService.currentUser;
		}
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Persona.label', default: 'Persona'), personaInstance.documentoNumero])
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

		PersonaRol.removeAll(personaInstance)
        personaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Persona.label', default: 'Persona'), personaInstance.documentoNumero])
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

	
	def jsonZonaDistritoGrupoSupervisor() {
		
		Zona zona = null;
		Distrito distrito = null;
		Grupo grupo = null;
		Persona supervisor = null;
		
		if (params.int('prsnParam') > 0) {
			supervisor = Persona.get(params.prsnParam);
			grupo = supervisor.getGrupo();
		}
		
		if (grupo != null) {
			distrito = grupo.getDistrito();
		} else if (params.int('grpParam') > 0) {
			grupo = Grupo.get(params.grpParam);
			distrito = grupo.getDistrito();
		}
		
		if (distrito != null) {
			zona = distrito.getZona();
		} else if (params.int('dstrtParam') > 0) {
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
		Set grps = new HashSet();
		if (distrito != null) {
			dstrtSlctd = distrito.getId();
			grps = Grupo.findAllByDistrito(distrito);
		} else {
			for (dstrt in dstrts) {
				grps.addAll(Grupo.findAllByDistrito(dstrt));
			}
		}

		Long grpSlctd = null;
		Set sprvsrs = new HashSet();
		if (grupo != null) {
			grpSlctd = grupo.getId();
			sprvsrs = Persona.findAllByGrupo(grupo);
		} else {
			for (grp in grps) {
				sprvsrs.addAll(Persona.findAllByGrupo(grp));
			}
		}
		
		// Saco los que no tiene rol Supervisor de la lista
		def rolSupervisor = Rol.findByAuthority('ROLE_SUPERVISOR')
		sprvsrs.each {
			if(!it.hasRol(rolSupervisor)) {
				sprvsrs.remove(it)
			}
		}
		
		Long sprvsrSlctd = null;
		if (supervisor != null) {
			sprvsrSlctd = supervisor.getId();
			if (grpSlctd == null) {
				grpSlctd = supervisor.getGrupo().getDistrito().getId();
			}
			if (dstrtSlctd == null) {
				dstrtSlctd = supervisor.getGrupo().getDistrito().getId();
			}
			if (znSlctd == null) {
				znSlctd = supervisor.getGrupo().getDistrito().getZona().getId();
			}
		}

		def respuesta = [zonaSelected: znSlctd, distritos: dstrts.sort{it.nombre}, distritoSelected: dstrtSlctd, grupos: grps.sort{it.nombre}, grupoSelected: grpSlctd, supervisores: sprvsrs.sort{it.apellido}, supervisorSelected: sprvsrSlctd];
		render respuesta as JSON;
	}
		
	
	protected String generateLink() {
		createLink(base: "$request.scheme://$request.serverName:$request.serverPort$request.contextPath",
				controller: 'login')
	}
	
	protected String evaluate(s, binding) {
		new SimpleTemplateEngine().createTemplate(s).make(binding)
	}
	
}
