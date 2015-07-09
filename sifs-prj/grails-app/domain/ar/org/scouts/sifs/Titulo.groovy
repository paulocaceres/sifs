package ar.org.scouts.sifs

class Titulo {
	
	Plan plan
	String nombre
	static hasMany = [cursos: Curso]
	
    static constraints = {
		plan(blank: false) 
		nombre(blank: false, maxSize:70,unique: 'plan' ) 
    }
	
	static mapping = {
		titulo unique: 'plan'
//		table 'plan_cursos'
	}

	String toString() {
		"$nombre"
	}
	
	// Funcion estatica para obtener los titulos que pertenecen a este plan
	// In: el Plan del cual se quieren obtener los titulos
	// Out: lista de titulos con los que pertenecen al plan (0, 1 ó más)
	static List<Titulo> listarPorPlan(Plan plan)
	{
		def tituloList = [];
		
		Titulo.list().each {
			if (it.plan.equals(plan))
				tituloList.add(it);
		}
		return tituloList;
	}
	
	public boolean isCompleted(List<Curso> cursosAprobados)
	{
		def result = true;
		
		for (Curso cursoTit : this.cursos) {
			
			for (Curso cursoAp : cursosAprobados) {
				if (cursoTit.equals(cursoAp))
					break;
				
				result = false;
			}
			
		}
		
		return result;
	}
}
