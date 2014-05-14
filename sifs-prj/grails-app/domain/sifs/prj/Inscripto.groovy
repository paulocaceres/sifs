package sifs.prj

class Inscripto {
	
	Curso curso
	Date fecha

    static constraints = {
    }
	
	String toString() {
		"$curso, ${fecha.format('dd/MM/yyyy')}"
	}


}
