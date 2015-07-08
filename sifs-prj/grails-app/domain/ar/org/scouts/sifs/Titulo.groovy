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
	
	static List<Titulo> listarPorPlan(Plan plan)
	{
		def planList = [];
		def tituloList = Titulo.list();
		
		tituloList.each {
			if (it.plan.equals(plan))
				planList.add(it);
		}
		return planList;
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
