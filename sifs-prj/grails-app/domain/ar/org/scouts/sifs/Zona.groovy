package ar.org.scouts.sifs

class Zona {

	String numero;
	
	String nombre;
	
//	Set distritos;
	

//	static hasMany = [
//		distritos: Distrito
//	]


	static constraints = {
		nombre(nullable: false);
		numero(nullable: false, matches:"^[1-9]\\d*", unique: true );
	}

	
	String toString() {
		return "$nombre";
	}

	
	static List<Zona> listarPorPersona(personaId) {
		def resultList = []
		def persona = Persona.get(personaId);
		resultList.add(persona.getZona());
		return resultList
	}


}


