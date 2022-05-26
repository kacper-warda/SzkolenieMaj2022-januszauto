package januszauto

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MechanicServiceSpec extends Specification {

    MechanicService mechanicService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        new Mechanic(firstName: "Adrian", lastName: "Nowak").save(flush: true, failOnError: true)
        new Mechanic(firstName: "Adrian", lastName: "Nowak").save(flush: true, failOnError: true)
        new Mechanic(firstName: "Adrian", lastName: "Nowak").save(flush: true, failOnError: true)
        new Mechanic(firstName: "Adrian", lastName: "Nowak").save(flush: true, failOnError: true)
        Mechanic mechanic = new Mechanic(firstName: "Adrian", lastName: "Nowak").save(flush: true, failOnError: true)
//        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        mechanic.id
    }

    void "test get"() {
        setupData()

        expect:
        mechanicService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Mechanic> mechanicList = mechanicService.list(max: 2, offset: 2)

        then:
        mechanicList.size() == 2
    }

    void "test count"() {
        setupData()

        expect:
        mechanicService.count() == 5
    }

    void "test delete"() {
        Long mechanicId = setupData()

        expect:
        mechanicService.count() == 5

        when:
        mechanicService.delete(mechanicId)
        sessionFactory.currentSession.flush()

        then:
        mechanicService.count() == 4
    }

    void "test save"() {
        when:
        Mechanic mechanic = new Mechanic(firstName: "Kacper", lastName: "Warda")
        mechanicService.save(mechanic)

        then:
        mechanic.id != null
    }
}
