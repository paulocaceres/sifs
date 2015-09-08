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
	
	def String safeAddress() {
		def dirString = "";
					if(calle && calle != 'null') {
						dirString = dirString + calle + " " 
					}
					if(numero && numero != 'null') {
						dirString = dirString + numero + " " 
					}
					if(adicional && adicional != 'null') {
						dirString = dirString + adicional + ", "
					}
					if(codigoPostal && codigoPostal != 'null') {
						dirString = dirString + codigoPostal + " "
					}
					if(ciudad && ciudad != 'null') {
						dirString = dirString + ciudad + ", "
					}	 
				    
					if(provincia && provincia !='null') {
						dirString = dirString + provincia.descripcion
					}
		return dirString
	}

static mapping = {
	calle defaultvalue: "'rio'"
}
}
