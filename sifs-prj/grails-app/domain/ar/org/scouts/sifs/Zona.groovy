package ar.org.scouts.sifs

import java.util.List;

class Zona {

	String numero
	
	String nombre
	
	static hasMany = [
		distritos: Distrito
	]


	static constraints = {
		nombre(nullable: false)
		numero(nullable: false, matches:"^[1-9]\\d*", unique: true )
	}

	
	String toString() {
		"$nombre"
	}

	static List<Zona> listarPorPersona(personaId) {
		def resultList = []
		def persona = Persona.get(personaId);
		resultList.add(persona.getZona());
		return resultList
	}
}


