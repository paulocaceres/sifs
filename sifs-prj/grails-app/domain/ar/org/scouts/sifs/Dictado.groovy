package ar.org.scouts.sifs
import java.util.Date
import org.grails.databinding.BindingFormat

class Dictado {
    
	Curso curso
	Zona zona
	@BindingFormat('dd/MM/yyyy')
	Date fecha = new Date() + 2
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

	static constraints = {
		zona(nullable:false)
		fecha(nullable:true)
		cupo(nullable:true)
		inscriptos(nullable:true)
		nombre(matches: "[a-z\\sA-Z\\s0-9]+")
    }
	
	String toString() {
		"$nombre"
	}

}
