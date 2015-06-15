package ar.org.scouts.sifs

class Distrito {
	
	Zona zona;

	String nombre;
	
	Set grupos;
	
	
	static belongTo = [
		zona: Zona
	]


	static hasMany = [
		grupos: Grupo
	]
	
    
	static constraints = {
    }

	
	String toString() {
		return "$nombre";
	}
	
	
}
