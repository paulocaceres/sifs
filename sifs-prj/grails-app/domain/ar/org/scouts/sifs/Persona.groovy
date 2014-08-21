package ar.org.scouts.sifs

class Persona {
	
	String documentoNumero
	String nombre
	String apellido
	String mail
	Direccion direccion
	Zona zona
	Distrito distrito
	Grupo grupo
	Persona superior
	Boolean bloqueado
	String[] roles 
	//Status status
	//TipoDocumento documentoTipo

    static constraints = {
		documentoNumero()
		nombre()
		apellido()
		mail()
		direccion()
		zona()
		superior(nullable: true)
		bloqueado()
    }

	String toString() {
		"$zona, $superior, $documentoNumero, $nombre, $apellido, $mail, $direccion, $bloqueado"
	}

}
