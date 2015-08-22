package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional
import groovy.text.SimpleTemplateEngine
import ar.org.scouts.sifs.security.PersonaRol
import ar.org.scouts.sifs.security.Rol
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import jxl.DateCell
import jxl.LabelCell
import jxl.NumberCell
import jxl.Sheet
import jxl.Workbook



@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class PersonaController {

	def springSecurityService
	def mailService
	def messageSource
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	private final static int COLUMN_DNI = 0
	private final static int COLUMN_NOMBRE = 1
	private final static int COLUMN_APELLIDO = 2
	private final static int COLUMN_MAIL = 3
	private final static int COLUMN_TELEFONO = 4
	private final static int COLUMN_DIR_CALLE = 5
	private final static int COLUMN_DIR_NUMERO = 6
	private final static int COLUMN_DIR_ADICIONAL = 7 
	private final static int COLUMN_DIR_CP = 8
	private final static int COLUMN_DIR_CIUDAD = 9 
	private final static int COLUMN_DIR_PCIA = 10
	private final static int COLUMN_ZONA = 11
	private final static int COLUMN_DISTRITO = 12
	private final static int COLUMN_GRUPO = 13
	private final static int COLUMN_SUPERVISOR = 14
	private final static int COLUMN_ROL = 15

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
		def supervRol = Rol.findByAuthority('ROLE_SUPERVISOR')
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
			def zona = params.get('zona.id') != null ? params.get('zona.id') : params.get('zonaHdd')
			def distrito = params.get('distrito.id') != null ? params.get('distrito.id') : params.get('distritoHdd')
			def grupo = params.get('grupo.id') != null ? params.get('grupo.id') : params.get('grupoHdd')
			def supervisor = params.get('supervisor.id') != null ? params.get('supervisor.id') : params.get('supervisorHdd')
			if(zona=='null' || zona==null) {
				personaInstance.errors.rejectValue('zona.id', 'ar.org.scouts.sifs.Persona.zona.nullable',
				'El campo Zona es obligatorio')
			} else {
				if (personaInstance.zona == null) {
					personaInstance.properties = [zona:[id: zona]];
				}
			}
			if(distrito=='null' || distrito==null) {
				personaInstance.errors.rejectValue('distrito.id', 'ar.org.scouts.sifs.Persona.distrito.nullable',
				'El campo Distrito es obligatorio')
			} else {
				if (personaInstance.distrito == null) {
					personaInstance.properties = [distrito:[id: distrito]];
				}
			}
			if(grupo=='null' || grupo==null) {
				personaInstance.errors.rejectValue('grupo.id', 'ar.org.scouts.sifs.Persona.grupo.nullable',
				'El campo Grupo es obligatorio')
			} else {
				if (personaInstance.grupo == null) {
					personaInstance.properties = [grupo:[id: grupo]];
				}
			}
			if(!rolesList?.contains(supervRol) && (supervisor=='null' || supervisor==null)) {
				personaInstance.errors.rejectValue('supervisor.id', 'ar.org.scouts.sifs.Persona.supervisor.nullable',
					'El campo Supervisor es obligatorio')
			} else {
				if (personaInstance.supervisor == null) {
					personaInstance.properties = [supervisor:[id: supervisor]];
				}
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

    
	//@Transactional
    def update(Persona personaInstance) {
		def rolesList = []
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
		
		PersonaRol.removeAll(personaInstance)
		personaInstance.save flush:true
		
		rolesList.each {
			PersonaRol.create(personaInstance, it, true)
		}
		
		//Si no tiene roles, le asigno el rol cursante por default
		def cursante = Rol.findByAuthority('ROLE_CURSANTE')
		if(!personaInstance.hasRol(cursante)) {
			PersonaRol.create(personaInstance, cursante, true)
		}
		
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
		
		personaInstance.zona = null
		personaInstance.distrito = null
		personaInstance.grupo = null
		personaInstance.supervisor = null
		personaInstance.save flush:true
		
		
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

	
	def upload() {
	}
    
	
	def doUpload() {
		def file = request.getFile('file')
		Workbook workbook = Workbook.getWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheet(0);

		// skip first row (row 0) by starting from 1
		for (int row = 1; row < sheet.getRows(); row++) {
			LabelCell lastName = sheet.getCell(COLUMN_LAST_NAME, row)
			LabelCell firstName = sheet.getCell(COLUMN_FIRST_NAME, row)
			DateCell dateOfBirth = sheet.getCell(COLUMN_DATE_OF_BIRTH, row)
			NumberCell numberOfChildren = sheet.getCell(COLUMN_NUMBER_OF_CHILDREN, row)

			new Persona(lastName:lastName.string , firstName:firstName.string ,
					dateOfBirth:dateOfBirth.date, numberOfChildren:numberOfChildren.value).save()

		}
		redirect (action:'index')
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
		Set sprvsrsTmp = new HashSet(); 
		sprvsrsTmp.addAll(sprvsrs);
		sprvsrsTmp.each {
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
