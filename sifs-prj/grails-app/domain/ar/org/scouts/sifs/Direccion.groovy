package ar.org.scouts.sifs

class Direccion {

	static belongsTo = [persona: Persona]
	
	String calle
	String numero
	String adicional
	String codigoPostal
	String ciudad
	Provincia provincia


	static constraints = {
	}


	String toString() {
		"$calle, $numero, $adicional, $codigoPostal, $ciudad, $provincia"
	}


}
