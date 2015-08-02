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
		calle(nullable: true);
		numero(nullable: true);
		adicional(nullable: true);
		codigoPostal(nullable: true);
		ciudad(nullable: true);
		provincia(nullable: true);
	}


	String toString() {
		"$calle, $numero, $adicional, $codigoPostal, $ciudad, $provincia"
	}


}
