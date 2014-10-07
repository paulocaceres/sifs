package ar.org.scouts.sifs

import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class GrupoController {

    def index() { }
}
