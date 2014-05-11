package sifs.prj

class Documento {
	
	Integer id
	String tipo
	String descripcion

    static constraints = {
    }

	String toString() {
		"$id, $tipo, $descripcion"
	}

	
}
