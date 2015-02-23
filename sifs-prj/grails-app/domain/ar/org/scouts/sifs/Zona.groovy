package ar.org.scouts.sifs

class Zona {
	
	String nombre
	String numero
	
	static hasMany = [distritos: Distrito]


	
	static constraints = {
		numero(nullable: true, matches:"[0-9]+", unique: true )
    }

	String toString() {
		"$nombre"
	}
	
}


