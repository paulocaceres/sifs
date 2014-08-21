package ar.org.scouts.sifs

class Zona {
	
	String nombre
	static hasMany = [distritos: Distrito]


	
	static constraints = {
    }

	String toString() {
		"$nombre"
	}
	
}
