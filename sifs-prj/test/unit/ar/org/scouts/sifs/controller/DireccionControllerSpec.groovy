package ar.org.scouts.sifs.controller



import grails.test.mixin.*
import spock.lang.*
import ar.org.scouts.sifs.domain.Direccion
import ar.org.scouts.sifs.domain.Provincia

@TestFor(DireccionController)
@Mock(Direccion)
class DireccionControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["calle"] 		= 	'calle'
        params["numero"] 		= 	'numero'
        params["adicional"] 	= 	'adicional'
        params["codigoPostal"] 	= 	'codigoPostal'
        params["ciudad"] 		= 	'ciudad'
        params["provincia"] 	= 	new Provincia(descripcion: 'descripcion')
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.direccionInstanceList
            model.direccionInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.direccionInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def direccion = new Direccion()
            direccion.validate()
            controller.save(direccion)

        then:"The create view is rendered again with the correct model"
            model.direccionInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            direccion = new Direccion(params)

            controller.save(direccion)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/direccion/show/1'
            controller.flash.message != null
            Direccion.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def direccion = new Direccion(params)
            controller.show(direccion)

        then:"A model is populated containing the domain instance"
            model.direccionInstance == direccion
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def direccion = new Direccion(params)
            controller.edit(direccion)

        then:"A model is populated containing the domain instance"
            model.direccionInstance == direccion
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/direccion/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def direccion = new Direccion()
            direccion.validate()
            controller.update(direccion)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.direccionInstance == direccion

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            direccion = new Direccion(params).save(flush: true)
            controller.update(direccion)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/direccion/show/$direccion.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/direccion/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def direccion = new Direccion(params).save(flush: true)

        then:"It exists"
            Direccion.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(direccion)

        then:"The instance is deleted"
            Direccion.count() == 0
            response.redirectedUrl == '/direccion/index'
            flash.message != null
    }
}
