package sifs.prj

class Curso {
	
	Plan plan
	Zona zona
	//Recurso recurso
	Contenido contenido
	Integer correlativas
	Persona inscripto
	Nivel nivel
	String nombre
	Date fecha
	Integer cupo

    static constraints = {
    }
	
	String toString() {
		"$plan, $zona, $contenido, $correlativas, $inscripto, $nivel, $nombre, ${fecha.format('dd/MM/yyyy')}, $cupo"
	}

}
