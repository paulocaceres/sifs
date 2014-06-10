package ar.org.scouts.sifs.controller



import grails.test.mixin.*
import spock.lang.*
import ar.org.scouts.sifs.domain.Contenido
import ar.org.scouts.sifs.domain.Curso
import ar.org.scouts.sifs.domain.Direccion
import ar.org.scouts.sifs.domain.Nivel
import ar.org.scouts.sifs.domain.Persona
import ar.org.scouts.sifs.domain.Plan
import ar.org.scouts.sifs.domain.Provincia
import ar.org.scouts.sifs.domain.Recurso
import ar.org.scouts.sifs.domain.Zona

@TestFor(CursoController)
@Mock(Curso)
class CursoControllerSpec extends Specification {

	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
		params["plan"] 			= 	new Plan(	nombre: 'curso.plan.nombre', 
												descripcion: 'curso.plan.descripcion', 
												validez: Date.parse('dd/MM/yyyy', '31/12/2014'))
		params["zona"] 			= 	new Zona(nombre: 'curso.zona.nombre')
		params["recurso"] 		= 	new Recurso(	nombre: 'curso.recurso.nombre', 
													cantidad: 1, 
													direccion: new Direccion(	calle: 'curso.recurso.direccion.calle', 
																				numero: 'curso.recurso.direccion.numero', 
																				adicional: 'curso.recurso.direccion.adicional', 
																				codigoPostal: 'curso.recurso.direccion.codigoPostal', 
																				ciudad: 'curso.recurso.direccion.ciudad', 
																				provincia: new Provincia(descripcion: 'curso.recurso.direccion.provincia.descripcion')))
		params["contenido"] 	= 	new Contenido(	nombre: 'curso.contenido.nombre', 
													descripcion: 'curso.contenido.descripcion')
		params["correlativas"] 	= 	1
		params["inscripto"] 	= 	new Persona(	zona: new Zona(nombre: 'curso.inscripto.zona.nombre'), 
													superior: null, 
													documentoNumero: 'curso.inscripto.documentoNumero', 
													nombre: 'curso.inscripto.nombre', 
													apellido: 'curso.inscripto.apellido', 
													mail: 'curso.inscripto.mail', 
													direccion: new Direccion(	calle: 'curso.inscripto.direccion.calle', 
																				numero: 'curso.inscripto.direccion.numero', 
																				adicional: 'curso.inscripto.direccion.adicional', 
																				codigoPostal: 'curso.inscripto.direccion.codigoPostal', 
																				ciudad: 'curso.inscripto.direccion.ciudad', 
																				provincia: new Provincia(descripcion: 'curso.inscripto.direccion.provincia.descripcion')), 
													bloqueado: false)
		params["nivel"] 		= 	new Nivel(	nombre: 'curso.nivel.nombre', 
												nivelCol: 'curso.nivel.nivelCol')
		params["nombre"] 		= 	'curso.nombre'
		params["fecha"] 		= 	Date.parse('dd/MM/yyyy', '31/12/2014')
		params["cupo"] 			= 	10
	}

	void "Test the index action returns the correct model"() {

		when:"The index action is executed"
		controller.index()

		then:"The model is correct"
		!model.cursoInstanceList
		model.cursoInstanceCount == 0
	}

	void "Test the create action returns the correct model"() {
		when:"The create action is executed"
		controller.create()

		then:"The model is correctly created"
		model.cursoInstance!= null
	}

	void "Test the save action correctly persists an instance"() {

		when:"The save action is executed with an invalid instance"
		request.contentType = FORM_CONTENT_TYPE
		def curso = new Curso()
		curso.validate()
		controller.save(curso)

		then:"The create view is rendered again with the correct model"
		model.cursoInstance!= null
		view == 'create'

		when:"The save action is executed with a valid instance"
		response.reset()
		populateValidParams(params)
		curso = new Curso(params)

		controller.save(curso)

		then:"A redirect is issued to the show action"
		response.redirectedUrl == '/curso/show/1'
		controller.flash.message != null
		Curso.count() == 1
	}

	void "Test that the show action returns the correct model"() {
		when:"The show action is executed with a null domain"
		controller.show(null)

		then:"A 404 error is returned"
		response.status == 404

		when:"A domain instance is passed to the show action"
		populateValidParams(params)
		def curso = new Curso(params)
		controller.show(curso)

		then:"A model is populated containing the domain instance"
		model.cursoInstance == curso
	}

	void "Test that the edit action returns the correct model"() {
		when:"The edit action is executed with a null domain"
		controller.edit(null)

		then:"A 404 error is returned"
		response.status == 404

		when:"A domain instance is passed to the edit action"
		populateValidParams(params)
		def curso = new Curso(params)
		controller.edit(curso)

		then:"A model is populated containing the domain instance"
		model.cursoInstance == curso
	}

	void "Test the update action performs an update on a valid domain instance"() {
		when:"Update is called for a domain instance that doesn't exist"
		request.contentType = FORM_CONTENT_TYPE
		controller.update(null)

		then:"A 404 error is returned"
		response.redirectedUrl == '/curso/index'
		flash.message != null


		when:"An invalid domain instance is passed to the update action"
		response.reset()
		def curso = new Curso()
		curso.validate()
		controller.update(curso)

		then:"The edit view is rendered again with the invalid instance"
		view == 'edit'
		model.cursoInstance == curso

		when:"A valid domain instance is passed to the update action"
		response.reset()
		populateValidParams(params)
		curso = new Curso(params).save(flush: true)
		controller.update(curso)

		then:"A redirect is issues to the show action"
		response.redirectedUrl == "/curso/show/$curso.id"
		flash.message != null
	}

	void "Test that the delete action deletes an instance if it exists"() {
		when:"The delete action is called for a null instance"
		request.contentType = FORM_CONTENT_TYPE
		controller.delete(null)

		then:"A 404 is returned"
		response.redirectedUrl == '/curso/index'
		flash.message != null

		when:"A domain instance is created"
		response.reset()
		populateValidParams(params)
		def curso = new Curso(params).save(flush: true)

		then:"It exists"
		Curso.count() == 1

		when:"The domain instance is passed to the delete action"
		controller.delete(curso)

		then:"The instance is deleted"
		Curso.count() == 0
		response.redirectedUrl == '/curso/index'
		flash.message != null
	}
}
