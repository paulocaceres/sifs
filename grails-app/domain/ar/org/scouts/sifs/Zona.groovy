package ar.org.scouts.sifs

import ar.org.scouts.sifs.security.Rol

class Zona {

	String numero;
	
	String nombre;
	
//	Set distritos;
	

//	static hasMany = [
//		distritos: Distrito
//	]


	static constraints = {
		nombre(nullable: false,matches: "[a-z\\sA-Z\\s0-9]+");
		numero(nullable: false, matches:"^[1-9]\\d*", unique: true );
	}

	
	String toString() {
		return "$numero - $nombre";
	}

	
	static List<Zona> listarPorPersona(personaId) {
		def resultList = []
		def persona = Persona.get(personaId);
		def rol = Rol.findByAuthority("ROLE_ADMIN");
		if(persona.getZona() != null) {
			resultList.add(persona.getZona());
		} else if(persona.hasRol(rol)) {
			resultList.addAll(Zona.list());
		}
		return resultList
	}


}


