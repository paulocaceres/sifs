package ar.org.scouts.sifs

import java.util.List;

class Plan {
	
	String nombre
	String descripcion
	Date validez
	
	static hasMany = [cursos:Curso]

    static constraints = {
    }

	String toString() {
		"$nombre"
	}
	
	//  this additional setter is used in the multiselect list to convert
	//    the ids selected in the checkbox list to the corresponding domain objects
	public void setCursosIds(List ids) {
		this.cursos = ids.collect { Curso.get(it) }
	}
	
}
