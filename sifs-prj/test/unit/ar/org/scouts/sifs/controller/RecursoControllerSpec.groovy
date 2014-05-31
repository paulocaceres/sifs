package ar.org.scouts.sifs.controller



import ar.org.scouts.sifs.domain.Recurso;
import grails.test.mixin.*
import spock.lang.*

@TestFor(RecursoController)
@Mock(Recurso)
class RecursoControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.recursoInstanceList
            model.recursoInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.recursoInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def recurso = new Recurso()
            recurso.validate()
            controller.save(recurso)

        then:"The create view is rendered again with the correct model"
            model.recursoInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            recurso = new Recurso(params)

            controller.save(recurso)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/recurso/show/1'
            controller.flash.message != null
            Recurso.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def recurso = new Recurso(params)
            controller.show(recurso)

        then:"A model is populated containing the domain instance"
            model.recursoInstance == recurso
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def recurso = new Recurso(params)
            controller.edit(recurso)

        then:"A model is populated containing the domain instance"
            model.recursoInstance == recurso
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/recurso/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def recurso = new Recurso()
            recurso.validate()
            controller.update(recurso)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.recursoInstance == recurso

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            recurso = new Recurso(params).save(flush: true)
            controller.update(recurso)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/recurso/show/$recurso.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/recurso/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def recurso = new Recurso(params).save(flush: true)

        then:"It exists"
            Recurso.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(recurso)

        then:"The instance is deleted"
            Recurso.count() == 0
            response.redirectedUrl == '/recurso/index'
            flash.message != null
    }
}
