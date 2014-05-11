package sifs.prj

class Curso {
	
	Integer id
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
		"$id, $plan, $zona, $contenido, $correlativas, $inscripto, $nivel, $nombre, $fecha, $cupo"
	}

}
