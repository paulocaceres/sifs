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
		calle(nullable: false,matches: "[a-z\\sA-Z\\s0-9,.'-]+");
		numero(nullable: false,matches:"^[0-9]\\d*");
		adicional(nullable: true,matches: "[a-z\\sA-Z\\s0-9,.'-]+");
		codigoPostal(nullable: false,matches:"^[0-9]\\d*", maxSize:4);
		ciudad(nullable: false,matches: "[a-z\\sA-Z\\s,.'-]+");
		provincia(nullable: false);
	}


	String toString() {
		if(adicional != null) {
			"$calle, $numero, $adicional, $codigoPostal, $ciudad, $provincia"
		} else {
			"$calle, $numero, $codigoPostal, $ciudad, $provincia"
		}
		//"$calle, $numero, $adicional, $codigoPostal, $ciudad, $provincia"
	}

static mapping = {
	calle defaultvalue: "'rio'"
}
}
