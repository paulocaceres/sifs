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

	static hasMany = [cursosAprobados : Curso,
					  cursosAnotados : Curso]

	static constraints = {
		documentoNumero blank: false, unique: true
		direccion(nullable: true)
		zona(nullable: true)
		distrito(nullable: true)
		grupo(nullable: true)
		superior(nullable: true)
		password blank: false
		cursosAprobados(nullable: true)
		cursosAnotados(nullable : true)
	}

	String toString() {
		"$documentoNumero, $nombre, $apellido, $mail"
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		PersonaRol.findAllByPersona(this).collect { it.rol } as Set
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

	//  this additional setter is used in the multiselect list to convert
	//    the ids selected in the checkbox list to the corresponding domain objects
	//public void setCursosAnotadosIds(List ids) {
	//	this.cursosAnotados = ids.collect { Curso.get(it) }
	//}

}
