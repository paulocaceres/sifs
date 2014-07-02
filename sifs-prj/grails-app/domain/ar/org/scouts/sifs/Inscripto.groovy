package ar.org.scouts.sifs

class Inscripto {
	
	Curso curso
	Date fecha

    static constraints = {
    }
	
	String toString() {
		"$curso, ${fecha.format('dd/MM/yyyy')}"
	}


}
