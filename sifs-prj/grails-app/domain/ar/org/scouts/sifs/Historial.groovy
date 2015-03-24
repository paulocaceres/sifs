package ar.org.scouts.sifs

class Historial {
	
	Persona persona
	Curso curso
	Date fechaAprobacion

    static constraints = {
		persona(nullable: false)
		curso(nullable: false)
    }

	String toString() {
		"$persona, $curso"
	}

}
