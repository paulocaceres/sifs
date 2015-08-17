package ar.org.scouts.sifs

class Titulo {

	Plan plan
	String nombre
	static hasMany = [cursos: Curso]

	static constraints = {
		plan(blank: false)
		nombre(blank: false,matches: "[a-z\\sA-Z\\s0-9]+",maxSize:70,unique: 'plan' )
	}

	static mapping = { titulo unique: 'plan' //		table 'plan_cursos'
	}

	String toString() {
		"$nombre"
	}

	// Funcion estatica para obtener los titulos que pertenecen a este plan
	// In: el Plan del cual se quieren obtener los titulos
	// Out: lista de titulos que pertenecen al plan (0, 1 ó más)
	static List<Titulo> listarPorPlan(Plan plan)
	{
		def tituloList = [];

		Titulo.list().each {
			if (it.plan.equals(plan))
				tituloList.add(it);
		}
		return tituloList;
	}

	// Funcion para verificar si se obtuvo un titulo en funcion de una lista de cursos aprobados.
	// In: lista de cursos aprobados
	// Out: boolean que indica si completó el título
	public boolean isCompleted(List<Curso> cursosAprobados)
	{
		def aprobadas = 0;
		def result = false;

		if (cursosAprobados != null && this.cursos != null && cursosAprobados.size() >= this.cursos.size())
		{
			for (Curso cursoTit : this.cursos) {

				for (Curso cursoAp : cursosAprobados) {
					if (cursoTit.equals(cursoAp))
					{
						aprobadas++;
						break;
					}
				}
			}
			
			if (aprobadas == this.cursos.size())
				result = true;
			else
				result = false;
		}
		else
		{
			result = false;
		}

		return result;
	}
}
