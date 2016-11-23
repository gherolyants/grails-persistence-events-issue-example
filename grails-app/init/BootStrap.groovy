import grails.core.GrailsApplication
import org.grails.datastore.mapping.core.Datastore
import test.PersistenceEventListener

class BootStrap {
    GrailsApplication grailsApplication

    def init = { servletContext ->
        grailsApplication.mainContext.getBeansOfType(Datastore).values().each { Datastore datastore ->
            grailsApplication.mainContext.addApplicationListener new PersistenceEventListener(datastore)
        }
    }
    def destroy = {
    }
}
