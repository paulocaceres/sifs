package sifs.prj

class Plan {
	
	Integer id
	String nombre
	String descripcion
	Date validez

    static constraints = {
    }

	String toString() {
		"$id, $nombre, $descripcion, $validez"
	}
	
}
