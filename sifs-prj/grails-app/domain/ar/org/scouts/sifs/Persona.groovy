package ar.org.scouts.sifs

import ar.org.scouts.sifs.security.PersonaRol
import ar.org.scouts.sifs.security.Rol

class Persona {

	transient springSecurityService

	String documentoNumero
	String nombre
	String apellido
	String mail
	static hasOne = [direccion: Direccion]
	Zona zona
	Distrito distrito
	Grupo grupo
	Persona superior
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static belongsTo = [ superior: Persona ]

	static hasMany = [dictadosAprobados : Long,
					  dictadosAnotados : Long]
	
	static mapping = {
		password column: '`password`'
	}

	static constraints = {
		documentoNumero blank: false, unique: true
		direccion(nullable: true)
		zona(nullable: true)
		distrito(nullable: true)
		grupo(nullable: true)
		superior(nullable: true)
		password blank: false
		dictadosAprobados(nullable: true)
		dictadosAnotados(nullable : true)
	}

	String toString() {
		"$nombre $apellido"
	}

	

	Set<Rol> getAuthorities() {
		PersonaRol.findAllByPersona(this).collect { it.rol } as Set
	}

	boolean hasRol(Rol rol) {
		if (this.id != null) {
			PersonaRol.countByPersonaAndRol(this, rol)
		} else {
			false
		}
	}
	
	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

	//  this additional setter is used in the multiselect list to convert
	//    the ids selected in the checkbox list to the corresponding domain objects
	public void setDictadosAnotadosIds(List ids) {
		this.dictadosAnotados = ids.collect { Dictado.get(it) }
	}
	
	static List<Persona> listarPorRol(String rolDesc) {
		def resultList = []
		def rol = Rol.findByAuthority(rolDesc)
		def personaList = Persona.list()
		personaList.each {
				if(it.hasRol(rol)) {
						resultList.add(it)
				}
		}
		return resultList
	}


}
