package ar.org.scouts.sifs.domain

class Direccion {

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
