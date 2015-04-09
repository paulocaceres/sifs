package ar.org.scouts.sifs


class Dictado {
    
	Curso curso
	Zona zona
	Date fecha
	Integer cupo
	Persona formador
	String nombre

	static belongTo = [
		curso: Curso
	]

	static hasMany = [
		recursos: Recurso,
		//inscriptos: Persona
	]

	static constraints = {
		zona(nullable:true)
		fecha(nullable:true)
		cupo(nullable:true)
		//inscriptos(nullable:true)
    }
	
	String toString() {
		"$nombre"
	}

}
