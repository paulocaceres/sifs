package ar.org.scouts.sifs

class Distrito {
	
	String nombre
	
	static belongTo = [
		zona: Zona
	]

	static hasMany = [
		grupos: Grupo
	]
	
    
	static constraints = {
    }

	
	String toString() {
		"$nombre"
	}
	
	
}
