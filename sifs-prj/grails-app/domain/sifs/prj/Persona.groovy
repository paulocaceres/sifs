package sifs.prj

class Persona {
	
	Integer id
	Zona zona
	Persona superior
	//Status status
	//TipoDocumento documentoTipo
	String documentoNumero
	String nombre
	String apellido
	String mail
	String direccion
	String provincia
	Boolean bloqueado

    static constraints = {
    }

	String toString() {
		"$id, $zona, $superior, $documentoNumero, $nombre, $apellido, $mail, $direccion, $provincia, $bloqueado"
	}

}
