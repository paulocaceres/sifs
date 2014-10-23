package ar.org.scouts.sifs

import ar.org.scouts.sifs.security.PersonaRol
import ar.org.scouts.sifs.security.Rol

class Persona {
	
	transient springSecurityService

	String documentoNumero
	String nombre
	String apellido
	String mail
	Direccion direccion
	Zona zona
	Distrito distrito
	Grupo grupo
	Persona superior
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	//static hasMany = [roles: SifsRole]
	//Status status
	//TipoDocumento documentoTipo

    static constraints = {
		documentoNumero blank: false, unique: true
		nombre()
		apellido()
		mail()
		direccion()
		zona(nullable: true)
		distrito(nullable: true)
		grupo(nullable: true)
		superior(nullable: true)
		password blank: false
}

	String toString() {
		"$zona, $superior, $documentoNumero, $nombre, $apellido, $mail, $direccion"
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		def emi01 = PersonaRol.findAllByPersona(this)
		emi01.collect { it.rol } as Set
	}

	boolean hasRol(Rol rol) {
	   PersonaRol.countByPersonaAndRol(this, rol) > 0
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
}
