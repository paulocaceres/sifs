package ar.org.scouts.sifs

class Inscripto  {
	
	Long personaId
	String documento
	String nombre
	String apellido
	Calificacion nota
	Dictado dictado
	
	static belongTo = [
		dictado: Dictado
	]
	
	static constraints = {
		nota(nullable:true);
		nombre(matches: "[a-z\\sA-Z\\s0-9]+");
		apellido(matches: "[a-z\\sA-Z\\s0-9]+");
	}
	
}
