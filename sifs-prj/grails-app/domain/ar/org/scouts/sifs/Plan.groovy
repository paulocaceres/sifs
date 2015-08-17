package ar.org.scouts.sifs


class Plan {
	String nombre;
	String descripcion;
//	Date validez;

//	static hasMany = [
//		cursos: Curso
//	];

//	static mappedBy = [
//		cursos: "plan"
//	];

	String toString() {
		"$nombre";
	}
	
	static constraints = {
		nombre(matches: "[a-z\\sA-Z\\s0-9]+");
		descripcion(matches: "[a-z\\sA-Z\\s0-9]+");
	}

}
