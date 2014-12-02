package ar.org.scouts.sifs


class Curso {
	
	//Plan plan
	Zona zona
	Nivel nivel
	String nombre
	String descripcion
	Date fecha
	Integer cupo
	static hasMany = [correlativas: Curso,
		              contenidos: Contenido,
					  recursos: Recurso
					  //inscriptos: Persona
					  ]
	
    static constraints = {
		contenidos nullable:true
		cupo nullable:true
		fecha nullable:true
		//inscriptos nullable:true
		//plan(nullable:true)
		recursos nullable:true
		zona(nullable:true)
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
