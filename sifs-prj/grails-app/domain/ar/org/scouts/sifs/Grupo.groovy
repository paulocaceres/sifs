package ar.org.scouts.sifs

class Grupo {
	
	Distrito distrito;

	String nombre;
	
	String numero;
	
//	Set supervisores;
	
	
	static belongTo = [
		distrito: Distrito
	]
			
//	static hasMany = [
//		supervisores: Persona
//	]
	
		
    static constraints = {
		nombre(blank: false);
		numero(nullable: false, matches:"^[1-9]\\d*", unique: true, maxSize:4 );
	}

	
	String toString() {
		return "$nombre";
	}


}
