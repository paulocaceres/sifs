package ar.org.scouts.sifs

import org.apache.commons.lang.builder.HashCodeBuilder


class PlanCurso /*implements Serializable*/ {
	Plan plan;
	Curso curso;

	static constraints = {
		plan(nullable: false);
		curso(nullable: false);
//		curso unique: 'plan';
	}
	
	static mapping = {
		curso unique: 'plan'
//		table 'plan_cursos'
	}

	static hasMany = [
		correlativas: Curso
	];

//	boolean equals(other) {
//		if (!(other instanceof PlanCurso)) {
//			return false
//		}
//
//		other.plan.id == this.plan.id && other.curso.id== this.curso.id;
//	}
//
//	int hashCode() {
//		def builder = new HashCodeBuilder();
//		builder.append(plan);
//		builder.append(curso);
//		builder.toHashCode();
//	}

}