package ar.org.scouts.sifs

class Grupo {
	
	String nombre
	String numero
	
	
	static belongTo = [distrito: Distrito]
	
    static constraints = {
		nombre(blank: false) 
		numero(nullable: false, matches:"[0-9]+", unique: true, maxSize:4 )
		
	}

	String toString() {
		"$nombre"
	}
	
}
