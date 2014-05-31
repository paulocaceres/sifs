package ar.org.scouts.sifs.domain

class Contenido {

	String nombre
	String descripcion
	
    static constraints = {
    }
	
	String toString() {
		"$nombre, $descripcion"
	}

}
