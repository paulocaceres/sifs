package ar.org.scouts.sifs

class Documento {
	
	String tipo
	String descripcion

    static constraints = {
    }

	String toString() {
		"$tipo, $descripcion"
	}

	
}
