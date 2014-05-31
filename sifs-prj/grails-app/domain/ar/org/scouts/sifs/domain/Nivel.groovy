package ar.org.scouts.sifs.domain

class Nivel {

	String nombre
	String nivelCol
	
    static constraints = {
    }
	
	String toString() {
		"$nombre, $nivelCol"
	}

}
