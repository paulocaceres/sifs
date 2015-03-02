package ar.org.scouts.sifs


class Dictado {

	Zona zona
	Date fecha
	Integer cupo

	static belongTo = [
		curso: Curso
	]

	static hasMany = [
		recursos: Recurso,
		inscriptos: Persona
	]

	static constraints = {
		zona(nullable:true)
		fecha(nullable:true)
		cupo(nullable:true)
    }

}
