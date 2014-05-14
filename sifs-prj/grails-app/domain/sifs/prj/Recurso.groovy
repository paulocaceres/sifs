package sifs.prj

class Recurso {

	String nombre
	Integer cantidad
	String direccion

    static constraints = {
    }

	String toString() {
		"$nombre, $cantidad, $direccion"
	}
	
}
