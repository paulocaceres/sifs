package sifs.prj

class Persona {
	
	Zona zona
	Persona superior
	//Status status
	//TipoDocumento documentoTipo
	String documentoNumero
	String nombre
	String apellido
	String mail
	String direccion
	Provincia provincia
	Boolean bloqueado

    static constraints = {
    }

	String toString() {
		"$zona, $superior, $documentoNumero, $nombre, $apellido, $mail, $direccion, $provincia, $bloqueado"
	}

}
