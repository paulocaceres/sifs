package ar.org.scouts.sifs

class Grupo {
	
	String nombre
	String numero
	
	
	static belongTo = [distrito: Distrito]
	
    static constraints = {
		numero(nullable: true)
    }

	String toString() {
		"$nombre"
	}
	
}
