package sifs.prj

class Recurso {

	Integer id
	String nombre
	Integer cantidad
	String direccion

    static constraints = {
    }

	String toString() {
		"$id, $nombre, $cantidad, $direccion"
	}
	
}
