package ar.org.scouts.sifs.domain

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
