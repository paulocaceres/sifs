package sifs.prj

class Historial {
	
	Integer id
	Persona persona
	Curso curso
	Date fechaAprobacion
	Integer calificacion

    static constraints = {
    }

	String toString() {
		"$id, $persona, $curso, $fechaAprobacion, $calificacion"
	}

}
