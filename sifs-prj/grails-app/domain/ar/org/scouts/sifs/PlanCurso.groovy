package ar.org.scouts.sifs


class PlanCurso implements Serializable {
	Plan plan;
	Curso curso;

	static mapping = {
		id composite: ['plan', 'curso']
		table 'plan_cursos'
	}
	
	static hasMany = [
		correlativas: Curso
	];
	
}