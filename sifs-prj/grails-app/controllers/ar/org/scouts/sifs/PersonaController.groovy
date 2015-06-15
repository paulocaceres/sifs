package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
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

		PersonaRol.removeAll(personaInstance)
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
		

		Long znSlctd = -1;
		Set dstrts = new HashSet();
		if (zona != null) {
			znSlctd = zona.getId();
			dstrts = zona.getDistritos();
		} else {
			dstrts = Distrito.list();
		}
	
		Long dstrtSlctd = -1;
		Set grps = new HashSet();
		if (distrito != null) {
			dstrtSlctd = distrito.getId();
			grps = distrito.getGrupos();
		} else {
			for (dstrt in dstrts) {
				grps.addAll(dstrt.getGrupos());
			}
		}

		Long grpSlctd = -1;
		Set sprvsrs = new HashSet();
		if (grupo != null) {
			grpSlctd = grupo.getId();
			sprvsrs = grupo.getSupervisores();
		} else {
			for (grp in grps) {
				sprvsrs.addAll(grp.getSupervisores());
			}
		}
		
		Long sprvsrSlctd = -1;
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
		
	
}
