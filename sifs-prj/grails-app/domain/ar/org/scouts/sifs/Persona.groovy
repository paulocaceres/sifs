package ar.org.scouts.sifs



import ar.org.scouts.sifs.security.PersonaRol
import ar.org.scouts.sifs.security.Rol



class Persona {

	transient springSecurityService

	Persona supervisor
	
	String documentoNumero
	
	String nombre
	
	String apellido
	
	String mail
	
	String telefono
	
	Zona zona
	
	Distrito distrito
	
	Grupo grupo
	
	String password
	
	boolean enabled = true
	
	boolean accountExpired
	
	boolean accountLocked
	
	boolean passwordExpired

	static hasOne = [
		direccion: Direccion
	]
	
	static belongsTo = [
		supervisor: Persona
	]

	static hasMany = [
		dictadosAprobados: Long, 
		dictadosAnotados: Long
	]

	
	static mapping = { 
		password column: '`password`'
	}

	
	static constraints = {
		nombre(matches: "[a-z\\sA-Z\\s,.'-]+");
		apellido(matches: "[a-z\\sA-Z\\s,.'-]+");
		documentoNumero(blank: false,matches: "^[0-9]\\d*",unique: true);
		mail(email: true)
		telefono(nullable: true,matches: "[0-9-]+");
		direccion(nullable: true);
		zona(nullable: true);
		distrito(nullable: true);
		grupo(nullable: true);
		supervisor(nullable: true);
		password(blank: false);
		dictadosAprobados(nullable: true);
		dictadosAnotados(nullable: true);
	}


	String toString() {
		"$apellido, $nombre"
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
		password = springSecurityService.encodePassword(password, documentoNumero)
	}


	// this additional setter is used in the multiselect list to convert the ids selected in the checkbox list to the corresponding domain objects
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
