package ar.org.scouts.sifs.controller



import ar.org.scouts.sifs.domain.Nivel;
import grails.test.mixin.*
import spock.lang.*

@TestFor(NivelController)
@Mock(Nivel)
class NivelControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.nivelInstanceList
            model.nivelInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.nivelInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def nivel = new Nivel()
            nivel.validate()
            controller.save(nivel)

        then:"The create view is rendered again with the correct model"
            model.nivelInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            nivel = new Nivel(params)

            controller.save(nivel)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/nivel/show/1'
            controller.flash.message != null
            Nivel.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def nivel = new Nivel(params)
            controller.show(nivel)

        then:"A model is populated containing the domain instance"
            model.nivelInstance == nivel
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def nivel = new Nivel(params)
            controller.edit(nivel)

        then:"A model is populated containing the domain instance"
            model.nivelInstance == nivel
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/nivel/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def nivel = new Nivel()
            nivel.validate()
            controller.update(nivel)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.nivelInstance == nivel

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            nivel = new Nivel(params).save(flush: true)
            controller.update(nivel)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/nivel/show/$nivel.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/nivel/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def nivel = new Nivel(params).save(flush: true)

        then:"It exists"
            Nivel.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(nivel)

        then:"The instance is deleted"
            Nivel.count() == 0
            response.redirectedUrl == '/nivel/index'
            flash.message != null
    }
}
