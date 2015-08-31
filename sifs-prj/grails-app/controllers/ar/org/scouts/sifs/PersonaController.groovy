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
import jxl.Cell



@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class PersonaController {

	def springSecurityService
	def mailService
	def messageSource
	def progressService
	
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
		params.sort = params.sort ?: "id"
		params.order = params.order ?: "desc"
		if (!springSecurityService.currentUser.hasRol(Rol.findByAuthority('ROLE_ADMIN'))) {
			def lista = Persona.findAllBySupervisor(springSecurityService.currentUser, params)
			respond lista, model:[personaInstanceCount: lista.size]
		} else {
			respond Persona.list(params), model:[personaInstanceCount: Persona.count()]
		}
    }

	def indexSupervised(Integer max) {
		params.sort = params.sort ?: "id"
		params.order = params.order ?: "desc"
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
			if(zona=='null' || zona=='' || zona==null) {
				personaInstance.errors.rejectValue('zona.id', 'ar.org.scouts.sifs.Persona.zona.nullable',
				'El campo Zona es obligatorio')
			} else {
				if (personaInstance.zona == null) {
					personaInstance.properties = [zona:[id: zona]];
				}
			}
			if(distrito=='null' || distrito=='' || distrito==null) {
				personaInstance.errors.rejectValue('distrito.id', 'ar.org.scouts.sifs.Persona.distrito.nullable',
				'El campo Distrito es obligatorio')
			} else {
				if (personaInstance.distrito == null) {
					personaInstance.properties = [distrito:[id: distrito]];
				}
			}
			if(grupo=='null' || grupo=='' || grupo==null) {
				personaInstance.errors.rejectValue('grupo.id', 'ar.org.scouts.sifs.Persona.grupo.nullable',
				'El campo Grupo es obligatorio')
			} else {
				if (personaInstance.grupo == null) {
					personaInstance.properties = [grupo:[id: grupo]];
				}
			}
			if(!rolesList?.contains(supervRol) && (supervisor=='null' || supervisor=='' || supervisor==null)) {
				personaInstance.errors.rejectValue('supervisor.id', 'ar.org.scouts.sifs.Persona.supervisor.nullable',
					'El campo Supervisor es obligatorio')
			} else {
				if (personaInstance.supervisor == null) {
					personaInstance.properties = [supervisor:[id: supervisor]];
				}
			}
		}
		
		//Password code validator
		def pass = params.get('password');
		if (!checkPasswordMinLength(pass) ||
			!checkPasswordMaxLength(pass) ||
			!checkPasswordRegex(pass)) {
			personaInstance.errors.rejectValue('password', 'command.nombre.error.strength',
				'El password no cumple con lo requisitos')
		}
		
			def nombre = params.get('nombre');
			if (!checkNombreRegex(nombre)) {
				personaInstance.errors.rejectValue('nombre', 'ar.org.scouts.sifs.Persona.nombre.error',
					'El nombre no cumple con lo requisitos')
			}
			
		def dniParam = params.int('documentoNumero');
		if (!checkDocumentoNumeroValue(dniParam)) {
			personaInstance.errors.rejectValue('documentoNumero', 'ar.org.scouts.sifs.Persona.documentoNumero.Min',
			'El Numero de documento debe ser mayor a 4000000')
		}
			
			
        if (personaInstance.hasErrors()) {
            respond personaInstance.errors, view:'create'
            return
        }
		
        personaInstance.save flush:true

//        if (personaInstance.hasErrors()) {
//            respond personaInstance.errors, view:'create'
//            return
//        }
		
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
    
	@Transactional
	def doUpload() {
		def file = request.getFile('file')
		Workbook workbook = Workbook.getWorkbook(file.getInputStream());
		progressService.setProgressBarValue("cargaExcelProgress", 0)
		Sheet sheet = workbook.getSheet(0);
		boolean success = false
		def persona = null
		def passGenerica = 'password1#'
		def porcentaje = 0
		
		// skip first row (row 0) by starting from 1
		for (int row = 1; row < sheet.getRows(); row++) {
			//this updates the progress bar value for the progress id 123
			porcentaje = (row / sheet.getRows()) * 100
			progressService.setProgressBarValue("cargaExcelProgress", porcentaje)
			Cell dni = sheet.getCell(COLUMN_DNI, row)
			Cell firstName = sheet.getCell(COLUMN_NOMBRE, row)
			Cell lastName = sheet.getCell(COLUMN_APELLIDO, row)
			Cell email = sheet.getCell(COLUMN_MAIL, row)
			Cell tel = sheet.getCell(COLUMN_TELEFONO, row)
			Cell dirCalle = sheet.getCell(COLUMN_DIR_CALLE, row)
			Cell dirNumero = sheet.getCell(COLUMN_DIR_NUMERO, row)
			Cell dirAdicional = sheet.getCell(COLUMN_DIR_ADICIONAL, row)
			Cell dirCP = sheet.getCell(COLUMN_DIR_CP, row)
			Cell dirCiudad = sheet.getCell(COLUMN_DIR_CIUDAD, row)
			Cell dirPcia = sheet.getCell(COLUMN_DIR_PCIA, row)
			Cell numZona = sheet.getCell(COLUMN_ZONA, row)
			Cell nomDistrito = sheet.getCell(COLUMN_DISTRITO, row)
			Cell numGrupo = sheet.getCell(COLUMN_GRUPO, row)
			Cell dniSupervisor = sheet.getCell(COLUMN_SUPERVISOR, row)
			Cell rolName = sheet.getCell(COLUMN_ROL, row)
			
			if(Persona.findByDocumentoNumero(dni.getContents())) {
				log.error("La persona dni: " + dni.getContents() + " ya existe");
			} else {
				try {
					def xZona = Zona.findByNumero(numZona.getContents())
					def xDistrito = Distrito.findByNombreIlike(nomDistrito.string)
					def xGrupo = Grupo.findByNumero(numGrupo.getContents())
					def xSupervisor = null
					def xRol = null
					if(rolName.getContents()) {
						xRol = Rol.findByAuthority(rolName.string)
					}
					def prov = Provincia.findByDescripcionIlike(dirPcia.string)
					
					if(dniSupervisor.getContents()) {
						xSupervisor = Persona.findByDocumentoNumero(dniSupervisor.getContents())
					}
					def dir = new Direccion(calle: dirCalle.string, numero: dirNumero.getContents(), adicional: dirAdicional.string,
						codigoPostal: dirCP.getContents(), ciudad: dirCiudad.string, provincia: prov)

					persona = new Persona(documentoNumero: dni.getContents(), nombre: firstName.string, apellido: lastName.string,
						mail:email.string, telefono: tel.getContents(), zona: xZona, distrito: xDistrito,
						grupo: xGrupo, supervisor: xSupervisor, password: passGenerica, enabled: true, accountExpired: false, accountLocked: false, 
										passwordExpired: false, direccion: dir).save(flush:true, insert: true)														
						
					def cursante = Rol.findByAuthority('ROLE_CURSANTE')
					PersonaRol.create(persona, cursante, true)
					persona.save flush:true
					
					if(xRol) {
						PersonaRol.create(persona, xRol, true)
						persona.save flush:true
					}
					success = true
				} catch(Exception e) {
					log.error("Error al crear la persona con dni: " + dni.getContents());
					success = false
				}
			} 
			if(success) {
				log.info("DNI: " + dni.getContents() + " cargado con exito.");
				String urlSifs = generateLinkResetPassword()
				def emailMessage = null
				def conf = SpringSecurityUtils.securityConfig
				def body = conf.ui.personaCreada.emailBody
				if (body.contains('$')) {
					body = evaluate(body, [user: persona, scoutpwd: passGenerica ,url: urlSifs])
				}
				
				try {
					mailService.sendMail {
						to persona.mail
						from conf.ui.personaCreada.emailFrom
						subject conf.ui.personaCreada.emailSubject
						html body.toString()
					}
				} catch(Exception e) {
					log.error("Error durante el envio de email de notificacion de creacion de persona", e)
				}
			}
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
	
	protected String generateLinkResetPassword() {
		createLink(base: "$request.scheme://$request.serverName:$request.serverPort$request.contextPath",
				controller: 'registro', action: 'forgotPassword')
	}
	
	protected String evaluate(s, binding) {
		new SimpleTemplateEngine().createTemplate(s).make(binding)
	}
	
	static boolean checkPasswordMinLength(String password) {
		int minLength = 8
		password && password.length() >= minLength
	}

	static boolean checkPasswordMaxLength(String password) {
		int maxLength = 64
		password && password.length() <= maxLength
	}

	static boolean checkPasswordRegex(String password) {
		String passValidationRegex = '^.*(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$'
		password && password.matches(passValidationRegex)
	}
	
	static boolean checkNombreRegex(String nombre) {
		String nombreValidationRegex = '^.*(aábcdeéfghiíjklmnñoópqrstuúüvwxyzAÁBCDEÉFGHIÍJKLMNÑOÓPQRSTUÚÜVWXYZ\\s,.-).*$'
		nombre && nombre.matches(nombreValidationRegex)
	}
	
	static boolean checkDocumentoNumeroValue(Integer documentoNumero) {
		int minvalue = 4000000
		documentoNumero  >= minvalue
	}
}
