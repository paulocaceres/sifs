package ar.org.scouts.sifs


class Curso {

	String nombre
	String descripcion
	Nivel nivel // [Nacional|Internacional]

	static belongsTo = Plan

	static hasMany = [
   		planes: Plan,
		contenidos: Contenido,
		dictados: Dictado
	]

	static constraints = {
//		contenidos(nullable:true)
//		plan(nullable:true)
		nivel(nullable:true)
		nombre(unique: true)
	}

	String toString() {
		"$nombre"
	}

	// this additional setter is used in the multiselect list to convert the ids selected in the checkbox list to the corresponding domain objects
	public void setCursosIds(List ids) {
		this.correlativas = ids.collect { Curso.get(it) }
	}

}
