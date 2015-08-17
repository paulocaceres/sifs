package ar.org.scouts.sifs
import java.util.Date

class Dictado {
    
	Curso curso
	Zona zona
	Date fecha = new Date()
	Integer cupo
	Persona formador
	DictadoStatus status
	String nombre
	
	static belongTo = [
		curso: Curso
	]

	static hasMany = [
		recursos: Recurso,
		inscriptos: Inscripto
	]
	
	static mapping = {
		inscriptos cascade: 'all-delete-orphan'
	}

	static constraints = {
		zona(nullable:true)
		fecha(nullable:true)
		cupo(nullable:true)
		inscriptos(nullable:true)
		nombre(matches: "[a-z\\sA-Z\\s0-9]+")
    }
	
	String toString() {
		"$nombre"
	}

}
