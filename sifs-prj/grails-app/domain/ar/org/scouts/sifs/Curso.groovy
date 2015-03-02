package ar.org.scouts.sifs


class Curso {
	
//	Plan plan
	Nivel nivel // Nacional e Internacional
	String nombre
	String descripcion

	static hasMany = [correlativas: Curso,
		contenidos: Contenido
	]
	
    static constraints = {
//		contenidos nullable:true
//		plan(nullable:true)
		nivel(nullable:true)
	}
	
	String toString() {
		"$nombre"
	}
	
	//  this additional setter is used in the multiselect list to convert
	//    the ids selected in the checkbox list to the corresponding domain objects
	public void setCursosIds(List ids) {
		this.correlativas = ids.collect { Curso.get(it) }
	}

}
