package ar.org.scouts.sifs.domain

class Plan {
	
	String nombre
	String descripcion
	Date validez

    static constraints = {
    }

	String toString() {
		"$nombre, $descripcion, ${validez.format('dd/MM/yyyy')}"
	}
	
}
