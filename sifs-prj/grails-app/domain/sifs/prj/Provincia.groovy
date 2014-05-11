package sifs.prj

class Provincia {
	
	Integer id
	String descripcion

    static constraints = {
    }

	String toString() {
		"$id, $descripcion"
	}
	
}
