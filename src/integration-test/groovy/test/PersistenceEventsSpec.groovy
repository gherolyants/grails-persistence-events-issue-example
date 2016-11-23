package test

import grails.persistence.Entity
import grails.test.mixin.TestMixin
import grails.test.mixin.gorm.Domain
import grails.test.mixin.hibernate.HibernateTestMixin
import grails.test.mixin.integration.Integration
import grails.transaction.*
import org.springframework.transaction.PlatformTransactionManager
import spock.lang.*

@Integration
@TestMixin(HibernateTestMixin)
@Domain(SuperBro)
@Rollback
class PersistenceEventsSpec extends Specification {

    void setTransactionManager(PlatformTransactionManager transactionManager) {
        // do I need to implement this? And how?
    }

    void 'should set lastUpdatedBy on entity persistence'() {
        when:
        ['Mario', 'Luigi'].each { new SuperBro(name: it).save(failOnError: true, flush: true) }

        then:
        SuperBro.findAll()*.lastUpdatedBy == ['42'] * 2
    }

}

@Entity
class SuperBro {

    String name
    String lastUpdatedBy

    static constraints = {
        lastUpdatedBy nullable: true
    }
}
