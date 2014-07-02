package ar.org.scouts.sifs

class Contenido {

	String nombre
	String descripcion
	

	static constraints = {
    }
	
	String toString() {
		"$nombre, $descripcion"
	}

}
