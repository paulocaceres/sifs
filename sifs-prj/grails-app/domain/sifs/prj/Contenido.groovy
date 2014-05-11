package sifs.prj

class Contenido {

	Integer id
	String nombre
	String descripcion
	
    static constraints = {
    }
	
	String toString() {
		"$id, $nombre, $descripcion"
	}

}
