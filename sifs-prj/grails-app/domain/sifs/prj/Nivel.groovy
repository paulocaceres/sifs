package sifs.prj

class Nivel {

	Integer id
	String nombre
	String nivelCol
	
    static constraints = {
    }
	
	String toString() {
		"$id, $nombre, $nivelCol"
	}

}
