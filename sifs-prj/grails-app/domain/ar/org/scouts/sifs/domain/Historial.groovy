package ar.org.scouts.sifs.domain

class Historial {
	
	Persona persona
	Curso curso
	Date fechaAprobacion
	Integer calificacion

    static constraints = {
    }

	String toString() {
		"$persona, $curso, ${fechaAprobacion.format('dd/MM/yyyy')}, $calificacion"
	}

}
