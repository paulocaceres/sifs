package ar.org.scouts.sifs

class Distrito {
	
	String nombre
	static hasMany = [grupos: Grupo]
	
	static belongTo = [zona: Zona]
	
    static constraints = {
    }

	String toString() {
		"$nombre"
	}
	
}
