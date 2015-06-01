package ar.org.scouts.sifs

class Titulo {
	
	Plan plan
	String nombre
	static hasMany = [cursos: Curso]
	
    static constraints = {
		plan(blank: false) 
		nombre(blank: false, maxSize:70,unique: 'plan' ) 
    }
	
	static mapping = {
		titulo unique: 'plan'
//		table 'plan_cursos'
	}

	String toString() {
		"$nombre"
	}
	
}
