package ar.org.scouts.sifs



import static org.springframework.http.HttpStatus.*
import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class PlanCursoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		Plan unPlan = Plan.get(params.planID);
        respond PlanCurso.findAllByPlan(unPlan), model:[planCursoInstanceCount: PlanCurso.count(), planInstance: unPlan];
    }

    def show(PlanCurso planCursoInstance) {
        respond planCursoInstance
    }

    def create() {
        respond new PlanCurso(plan: Plan.get(params.planID))
    }

    @Transactional
    def save(PlanCurso planCursoInstance) {
        if (planCursoInstance == null) {
            notFound()
            return
        }

        if (planCursoInstance.hasErrors()) {
            respond planCursoInstance.errors, view:'create'
            return
        }

        planCursoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'planCursoInstance.label', default: 'PlanCurso'), planCursoInstance.id])
                redirect planCursoInstance
            }
            '*' { respond planCursoInstance, [status: CREATED] }
        }
    }

    def edit(PlanCurso planCursoInstance) {
        respond planCursoInstance
    }

    @Transactional
    def update(PlanCurso planCursoInstance) {
        if (planCursoInstance == null) {
            notFound()
            return
        }

        if (planCursoInstance.hasErrors()) {
            respond planCursoInstance.errors, view:'edit'
            return
        }

        planCursoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'PlanCurso.label', default: 'PlanCurso'), planCursoInstance.id])
                redirect planCursoInstance
            }
            '*'{ respond planCursoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(PlanCurso planCursoInstance) {

        if (planCursoInstance == null) {
            notFound()
            return
        }

		Plan planInstance = Plan.get(planCursoInstance.plan.id);
		
        planCursoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'PlanCurso.label', default: 'PlanCurso'), planCursoInstance.id])
                redirect action:"index", method:"GET", params: [planID: planInstance.id]
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'planCursoInstance.label', default: 'PlanCurso'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
