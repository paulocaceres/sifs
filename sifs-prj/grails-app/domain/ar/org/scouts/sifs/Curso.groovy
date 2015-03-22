package ar.org.scouts.sifs


class Curso {
	String nombre;
	String descripcion;
	Nivel nivel; // [Nacional|Internacional]
//	Plan plan;

//	static belongsTo = Plan;

	static hasMany = [
//		planes: Plan,
		contenidos: Contenido,
		dictados: Dictado
	];

	static constraints = {
//		contenidos(nullable: true);
//		plan(nullable: true);
		nivel(nullable: true);
		nombre(unique: true);
	}

	String toString() {
		"$nombre";
	}


}