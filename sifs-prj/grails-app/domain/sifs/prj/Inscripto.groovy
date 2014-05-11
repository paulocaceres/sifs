package sifs.prj

class Inscripto {
	
	Integer id
	Curso curso
	Date fecha

    static constraints = {
    }
	
	String toString() {
		"$id, $curso, $fecha"
	}


}
