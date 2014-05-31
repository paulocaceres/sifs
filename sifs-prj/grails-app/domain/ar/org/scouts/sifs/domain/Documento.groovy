package ar.org.scouts.sifs.domain

class Documento {
	
	String tipo
	String descripcion

    static constraints = {
    }

	String toString() {
		"$tipo, $descripcion"
	}

	
}
