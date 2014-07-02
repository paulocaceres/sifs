package ar.org.scouts.sifs

class Persona {
	
	String documentoNumero
	String nombre
	String apellido
	String mail
	Direccion direccion
	Zona zona
	Persona superior
	Boolean bloqueado
	//Status status
	//TipoDocumento documentoTipo

    static constraints = {
		documentoNumero()
		nombre()
		apellido()
		mail()
		direccion()
		zona()
		superior()
		bloqueado()
    }

	String toString() {
		"$zona, $superior, $documentoNumero, $nombre, $apellido, $mail, $direccion, $bloqueado"
	}

}
