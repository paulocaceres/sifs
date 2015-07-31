package ar.org.scouts.sifs

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Ignore;
import spock.lang.Shared;
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Titulo)
@Mock([Plan,Titulo,Curso])
class TituloSpec extends Specification
{
	Plan plan = null;
	Titulo titulo = null;
	Curso curso = null;
	
	def setup()
	{
		//Plan sin titulos
		plan = new Plan(nombre: "Plan0", descripcion: 'Plan sin titulos'); plan.save(flush: true, insert: true);
		
		//Plan con 1 titulo
		plan = new Plan(nombre: "Plan1", descripcion: 'Plan con 1 titulo'); plan.save(flush: true, insert: true);
		titulo = new Titulo(nombre: "Titulo1", plan: plan); titulo.save(flush: true, insert: true);
		
		//Plan con 2 titulos
		plan = new Plan(nombre: "Plan2", descripcion: "Plan con 2 titulos"); plan.save(flush: true, insert: true);
		titulo = new Titulo(nombre: "Titulo1", plan: plan); titulo.save(flush: true, insert: true);
		titulo = new Titulo(nombre: "Titulo2", plan: plan); titulo.save(flush: true, insert: true);
		
		//Otro Plan con 1 titulo y 4 cursos
		plan = new Plan(nombre: "OtroPlan1", descripcion: 'Otro Plan con 1 titulo'); plan.save(flush: true, insert: true);
		titulo = new Titulo(nombre: "Titulo3", plan: plan); titulo.save(flush: true, insert: true);
		
		curso = new Curso(nombre: "Curso1"); curso.save(flush:true, insert:true); titulo.addToCursos(curso);
		curso = new Curso(nombre: "Curso2"); curso.save(flush:true, insert:true); titulo.addToCursos(curso);
		curso = new Curso(nombre: "Curso3"); curso.save(flush:true, insert:true); titulo.addToCursos(curso);
		curso = new Curso(nombre: "Curso4"); curso.save(flush:true, insert:true); titulo.addToCursos(curso);
		curso = new Curso(nombre: "Curso8"); curso.save(flush:true, insert:true);
		
		// inicializo variables
		plan = null;
		titulo = null;
		curso = null;
		
		assert Plan.count().equals(4);
		assert Titulo.count().equals(4);
		assert Curso.count().equals(5);
	}
	
		
	// Pruebas de listarPorPlan
	
	def "probar que si paso un plan que no existe, devuelva una lista vacia" ()
	{
		given: 'un plan inexistente'
		plan = Plan.findByNombre("PlanFAFAFA");
		
		expect: 'listarPorPlan devuelve una lista vacia'
		Titulo.listarPorPlan(plan).isEmpty();
	}
	
	def "probar que si paso null, devuelva una lista vacia" ()
	{
		given: 'un plan null'
		plan = null;
		
		expect: 'listarPorPlan devuelve una lista vacia'
		Titulo.listarPorPlan(plan).isEmpty();
	}
	
	def "probar que si paso un plan sin titulos, devuelva una lista vacia" ()
	{
		given: 'un plan sin titulos'
		plan = Plan.findByNombre("Plan0");
		
		expect: 'listarPorPlan devuelve una lista vacia'
		Titulo.listarPorPlan(plan).isEmpty();
	}
	
	def "probar que si paso un plan con 1 titulo, devuelva el titulo del plan" ()
	{
		given: 'un plan con un titulo'
		plan = Plan.findByNombre("Plan1");

		expect: 'listarPorPlan devuelve una lista con un titulo'
		Titulo.listarPorPlan(plan).size() == 1;
	}
	
	def "probar que si paso un plan con 2 titulos, devuelva los titulos del plan" ()
	{
		given: 'un plan con 2 titulos'
		plan = Plan.findByNombre("Plan2");
		
		expect: 'listarPorPlan devuelve una lista con 2 titulos'
		Titulo.listarPorPlan(plan).size() == 2;
	}
	
	def "probar que si paso un plan con titulos en un escenario complejo, devuelva los titulos del plan" ()
	{
		given: 'el plan OtroPlan1'
		plan = Plan.findByNombre("OtroPlan1");

		when: 'obtengo los titulos'
		List<Titulo> lista = Titulo.listarPorPlan(plan);
		
		then: 'devuelve Titulo3'
		lista.size() == 1;
		lista.get(0).equals(Titulo.findByNombre("Titulo3"));		
	}
	
	// Pruebas de isCompleted
	
	def "probar que si paso una lista null, informe que no esta completo"()
	{
		given: 'un titulo y una lista de cursos aprobados = null'
		titulo = Titulo.findByNombre("Titulo3");
		List<Curso> lista = null;
		
		expect: 'informa que esta no completado'
		titulo.isCompleted(lista) == false;
	}
	
	def "probar que si paso una lista vacia, informe que no esta completo"()
	{
		given: 'un titulo  una lista de cursos aprobados vacia'
		titulo = Titulo.findByNombre("Titulo3");
		List<Curso> lista = new ArrayList<Curso>();
		
		expect: 'informa que esta no completado'
		titulo.isCompleted(lista) == false;
	}
	
	def "probar que si paso una lista con < cantidad de cursos, informe que no esta completo"()
	{
		given: 'un titulo y una lista con menor cantidad de cursos que el titulo'
		titulo = Titulo.findByNombre("Titulo3");
		List<Curso> lista = new ArrayList();
		lista.add(Curso.findByNombre("Curso1"));
		lista.add(Curso.findByNombre("Curso2"));
		lista.add(Curso.findByNombre("Curso3"));
		
		expect: 'informa que esta no completo'
		titulo.isCompleted(lista) == false;
	}
	
	def "probar que si paso una lista con >= cantidad de cursos con algunos de este titulo, informe que no esta completo" ()
	{
		given: 'un titulo y una lista con mayor/igual cantidad de cursos que el titulo pero otros cursos'
		titulo = Titulo.findByNombre("Titulo3");
		List<Curso> lista = new ArrayList();
		lista.add(Curso.findByNombre("Curso1"));
		lista.add(Curso.findByNombre("Curso2"));
		lista.add(Curso.findByNombre("Curso3"));
		lista.add(Curso.findByNombre("Curso8"));
		
		expect: 'informa que esta no completo'
		titulo.isCompleted(lista) == false;
	}
	def "probar que si paso una lista con >= cantidad de cursos con todos de este titulo, informe que esta completo" ()
	{
		given: 'un titulo y una lista con mayor/igual cantidad de cursos que el titulo'
		titulo = Titulo.findByNombre("Titulo3");
		List<Curso> lista = new ArrayList();
		lista.add(Curso.findByNombre("Curso1"));
		lista.add(Curso.findByNombre("Curso2"));
		lista.add(Curso.findByNombre("Curso3"));
		lista.add(Curso.findByNombre("Curso4"));
		
		expect: 'informa que esta completo'
		titulo.isCompleted(lista) == true;

	}

	def "probar que si paso una lista con >= cantidad de cursos con todos de este titulo y otros, informe que esta completo" ()
	{
		given: 'un titulo y una lista con mayor/igual cantidad de cursos que el titulo'
		titulo = Titulo.findByNombre("Titulo3");
		List<Curso> lista = new ArrayList();
		lista.add(Curso.findByNombre("Curso8"));
		lista.add(Curso.findByNombre("Curso1"));
		lista.add(Curso.findByNombre("Curso2"));
		lista.add(Curso.findByNombre("Curso3"));
		lista.add(Curso.findByNombre("Curso4"));
		
		expect: 'informa que esta completo'
		titulo.isCompleted(lista) == true;

	}

}

