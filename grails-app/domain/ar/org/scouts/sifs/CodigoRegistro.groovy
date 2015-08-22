package ar.org.scouts.sifs

class CodigoRegistro {
	
	String username
	String token = UUID.randomUUID().toString().replaceAll('-', '')
	Date dateCreated

	static mapping = {
		version false
	}

    static constraints = {
    }
}
