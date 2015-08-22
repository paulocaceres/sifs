package ar.org.scouts.sifs

class Recurso {

	String nombre
	Integer cantidad
	Direccion direccion

    static constraints = {
		direccion nullable:true 
    }

	String toString() {
		"$nombre"
	}
	
}
