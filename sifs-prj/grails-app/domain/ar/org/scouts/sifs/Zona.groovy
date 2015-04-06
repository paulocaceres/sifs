package ar.org.scouts.sifs

class Zona {
	String numero
	String nombre
	
	static hasMany = [distritos: Distrito]


	
	static constraints = {
		nombre(nullable: false) 
		numero(nullable: false, matches:"[0-9]+", unique: true )
    }

	String toString() {
		"$nombre"
	}
	
}


