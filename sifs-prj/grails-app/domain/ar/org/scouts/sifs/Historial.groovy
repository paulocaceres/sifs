package ar.org.scouts.sifs

class Historial {
	
	Persona persona
	Curso curso
	Date fechaAprobacion
	
	def Historial(Persona p, Curso c, Date fecha) {
		persona = p
		curso = c
		fechaAprobacion = fecha
	}

    static constraints = {
		persona(nullable: false)
		curso(nullable: false)
    }

	String toString() {
		"$persona, $curso"
	}

}
