package ar.org.scouts.sifs

class Titulo {
	
	String nombre
	static hasMany = [cursos: Curso]
	
    static constraints = {
		nombre(blank: false,unique: true, maxSize:70 ) 
    }

	String toString() {
		"$nombre"
	}
	
}
