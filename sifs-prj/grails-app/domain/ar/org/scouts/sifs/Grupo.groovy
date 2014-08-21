package ar.org.scouts.sifs

class Grupo {
	
	String nombre
	
	
	static belongTo = [distrito: Distrito]
	
    static constraints = {
    }

	String toString() {
		"$nombre"
	}
	
}
