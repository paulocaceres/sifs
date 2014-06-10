package ar.org.scouts.sifs.domain



import grails.test.mixin.*



/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Plan)
class PlanSpec {


	def setup() {
	}


	def cleanup() {
	}


	void testToString() {
		def unPlan = new Plan(	nombre: 		'plan.nombre',
								descripcion: 	'plan.descripcion',
								validez: 		Date.parse('dd/MM/yyyy', '31/12/2014'))

		assertEquals 	'plan.nombre, ' +
						'plan.descripcion, ' +
						'31/12/2014', unPlan.toString()
	}


}
