package sifs.prj

class Documento {
	
	String tipo
	String descripcion

    static constraints = {
    }

	String toString() {
		"$tipo, $descripcion"
	}

	
}
