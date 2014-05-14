package sifs.prj

class Contenido {

	String nombre
	String descripcion
	
    static constraints = {
    }
	
	String toString() {
		"$nombre, $descripcion"
	}

}
