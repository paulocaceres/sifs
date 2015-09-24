package ar.org.scouts.sifs


class Curso {
	String nombre;
	Nivel nivel; // [Nacional|Internacional]
//	Plan plan;

//	static belongsTo = Plan;

	static hasMany = [
//		planes: Plan,
		contenidos: Contenido,
		dictados: Dictado
	];
	
	static mapping = {
		dictados lazy: false
	}

	static constraints = {
//		contenidos(nullable: true);
//		plan(nullable: true);
		nivel(nullable: false);
		nombre(unique: true,matches: "[a-z\\sA-Z\\s0-9]+");
	}

	String toString() {
		"$nombre";
	}


}