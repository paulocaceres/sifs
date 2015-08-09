package ar.org.scouts.sifs

class Distrito {
	
	Zona zona;

	String nombre;
	
//	Set grupos;
	
	
	static belongTo = [
		zona: Zona
	]


//	static hasMany = [
//		grupos: Grupo
//	]
	
    
	static constraints = {
		nombre(blank: false);
    }

	
	String toString() {
		return "$nombre";
	}
	
	
}
