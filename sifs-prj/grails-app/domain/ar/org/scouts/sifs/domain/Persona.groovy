package ar.org.scouts.sifs.domain

class Persona {
	
	Zona zona
	Persona superior
	//Status status
	//TipoDocumento documentoTipo
	String documentoNumero
	String nombre
	String apellido
	String mail
	Direccion direccion
	Boolean bloqueado

    static constraints = {
    }

	String toString() {
		"$zona, $superior, $documentoNumero, $nombre, $apellido, $mail, $direccion, $bloqueado"
	}

}
