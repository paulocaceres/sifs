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
		calle(nullable: false);
		numero(nullable: false,matches:"^[0-9]\\d*");
		adicional(nullable: true);
		codigoPostal(nullable: false,matches:"^[0-9]\\d*", maxSize:4);
		ciudad(nullable: false);
		provincia(nullable: false);
	}


	String toString() {
		"$calle, $numero, $adicional, $codigoPostal, $ciudad, $provincia"
	}

static mapping = {
	calle defaultvalue: "'rio'"
}
}
