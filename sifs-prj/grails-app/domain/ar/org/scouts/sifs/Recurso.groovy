package ar.org.scouts.sifs

class Recurso {

	String nombre
	Integer cantidad
	Direccion direccion

    static constraints = {
    }

	String toString() {
		"$nombre, $cantidad, $direccion"
	}
	
}
