package ar.org.scouts.sifs

class Distrito {
	
//	Zona zona;

	String nombre;
	
//	Set grupos;
	
	
	static belongsTo = [
		zona: Zona
	]


//	static hasMany = [
//		grupos: Grupo
//	]
	
    
	static constraints = {
		nombre(blank: false,matches: "[a-z\\sA-Z\\s0-9]+");
    }

	
	String toString() {
		return "$nombre";
	}
	
	
}
