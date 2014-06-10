package ar.org.scouts.sifs.controller



import grails.test.mixin.*
import spock.lang.*
import ar.org.scouts.sifs.domain.Contenido
import ar.org.scouts.sifs.domain.Curso
import ar.org.scouts.sifs.domain.Direccion
import ar.org.scouts.sifs.domain.Historial
import ar.org.scouts.sifs.domain.Nivel
import ar.org.scouts.sifs.domain.Persona
import ar.org.scouts.sifs.domain.Plan
import ar.org.scouts.sifs.domain.Provincia
import ar.org.scouts.sifs.domain.Recurso
import ar.org.scouts.sifs.domain.Zona

@TestFor(HistorialController)
@Mock(Historial)
class HistorialControllerSpec extends Specification {

	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
		params["persona"]			=	new Persona(	zona: new Zona(nombre: 'historial.persona.zona.nombre'), 
														superior: null,
														documentoNumero: 'historial.persona.documentoNumero',
														nombre: 'historial.persona.nombre',
														apellido: 'historial.persona.apellido',
														mail: 'historial.persona.mail',
														direccion: new Direccion(	calle: 'historial.persona.direccion.calle', 
																					numero: 'historial.persona.direccion.numero', 
																					adicional: 'historial.persona.direccion.adicional', 
																					codigoPostal: 'historial.persona.direccion.codigoPostal', 
																					ciudad: 'historial.persona.direccion.ciudad', 
																					provincia: new Provincia(descripcion: 'historial.persona.direccion.provincia.descripcion')),
														bloqueado: false)
		params["curso"]				=	new Curso(	plan: new Plan(	nombre: 'historial.curso.plan.nombre',
																	descripcion: 'historial.curso.plan.descripcion',
																	validez: Date.parse('dd/MM/yyyy', '31/12/2014')),
													zona: new Zona(	nombre: 'historial.curso.zona.nombre'),
													recurso: new Recurso(	nombre: 'historial.curso.recurso.nombre',
																			cantidad: 1,
																			direccion: new Direccion(	calle: 'historial.curso.recurso.direccion.calle',
																										numero: 'historial.curso.recurso.direccion.numero',
																										adicional: 'historial.curso.recurso.direccion.adicional',
																										codigoPostal: 'historial.curso.recurso.direccion.codigoPostal',
																										ciudad: 'historial.curso.recurso.direccion.ciudad',
																										provincia: new Provincia(descripcion: 'historial.curso.recurso.direccion.provincia.descripcion'))),
													contenido: new Contenido(	nombre: 'historial.curso.contenido.nombre',
																				descripcion: 'historial.curso.contenido.descripcion'),
													correlativas: 1,
													inscripto: new Persona(	zona: new Zona(	nombre: 'historial.curso.persona.zona.nombre'),
																			superior: null,
																			documentoNumero: 'historial.persona.documentoNumero',
																			nombre: 'historial.persona.nombre',
																			apellido: 'historial.persona.apellido',
																			mail: 'historial.persona.mail',
																			direccion: new Direccion(	calle: 'historial.persona.direccion.calle', 
																										numero: 'historial.persona.direccion.numero', 
																										adicional: 'historial.persona.direccion.adicional', 
																										codigoPostal: 'historial.persona.direccion.codigoPostal', 
																										ciudad: 'historial.persona.direccion.ciudad', 
																										provincia: new Provincia(descripcion: 'historial.persona.direccion.provincia.descripcion')),
																			bloqueado: false),
													nivel: new Nivel(	nombre: 'historial.curso.nivel.nombre',
																		nivelCol: 'historial.curso.nivel.nivelCol'),
													nombre: 'historial.curso.nombre',
													fecha: Date.parse('dd/MM/yyyy', '31/12/2014'),
													cupo: 10)
		params["fechaAprobacion"]	=	Date.parse('dd/MM/yyyy', '31/12/2014')
		params["calificacion"]		=	1
	}

	void "Test the index action returns the correct model"() {

		when:"The index action is executed"
		controller.index()

		then:"The model is correct"
		!model.historialInstanceList
		model.historialInstanceCount == 0
	}

	void "Test the create action returns the correct model"() {
		when:"The create action is executed"
		controller.create()

		then:"The model is correctly created"
		model.historialInstance!= null
	}

	void "Test the save action correctly persists an instance"() {

		when:"The save action is executed with an invalid instance"
		request.contentType = FORM_CONTENT_TYPE
		def historial = new Historial()
		historial.validate()
		controller.save(historial)

		then:"The create view is rendered again with the correct model"
		model.historialInstance!= null
		view == 'create'

		when:"The save action is executed with a valid instance"
		response.reset()
		populateValidParams(params)
		historial = new Historial(params)

		controller.save(historial)

		then:"A redirect is issued to the show action"
		response.redirectedUrl == '/historial/show/1'
		controller.flash.message != null
		Historial.count() == 1
	}

	void "Test that the show action returns the correct model"() {
		when:"The show action is executed with a null domain"
		controller.show(null)

		then:"A 404 error is returned"
		response.status == 404

		when:"A domain instance is passed to the show action"
		populateValidParams(params)
		def historial = new Historial(params)
		controller.show(historial)

		then:"A model is populated containing the domain instance"
		model.historialInstance == historial
	}

	void "Test that the edit action returns the correct model"() {
		when:"The edit action is executed with a null domain"
		controller.edit(null)

		then:"A 404 error is returned"
		response.status == 404

		when:"A domain instance is passed to the edit action"
		populateValidParams(params)
		def historial = new Historial(params)
		controller.edit(historial)

		then:"A model is populated containing the domain instance"
		model.historialInstance == historial
	}

	void "Test the update action performs an update on a valid domain instance"() {
		when:"Update is called for a domain instance that doesn't exist"
		request.contentType = FORM_CONTENT_TYPE
		controller.update(null)

		then:"A 404 error is returned"
		response.redirectedUrl == '/historial/index'
		flash.message != null


		when:"An invalid domain instance is passed to the update action"
		response.reset()
		def historial = new Historial()
		historial.validate()
		controller.update(historial)

		then:"The edit view is rendered again with the invalid instance"
		view == 'edit'
		model.historialInstance == historial

		when:"A valid domain instance is passed to the update action"
		response.reset()
		populateValidParams(params)
		historial = new Historial(params).save(flush: true)
		controller.update(historial)

		then:"A redirect is issues to the show action"
		response.redirectedUrl == "/historial/show/$historial.id"
		flash.message != null
	}

	void "Test that the delete action deletes an instance if it exists"() {
		when:"The delete action is called for a null instance"
		request.contentType = FORM_CONTENT_TYPE
		controller.delete(null)

		then:"A 404 is returned"
		response.redirectedUrl == '/historial/index'
		flash.message != null

		when:"A domain instance is created"
		response.reset()
		populateValidParams(params)
		def historial = new Historial(params).save(flush: true)

		then:"It exists"
		Historial.count() == 1

		when:"The domain instance is passed to the delete action"
		controller.delete(historial)

		then:"The instance is deleted"
		Historial.count() == 0
		response.redirectedUrl == '/historial/index'
		flash.message != null
	}
}
