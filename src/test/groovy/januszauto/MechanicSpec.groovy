package januszauto

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MechanicSpec extends Specification implements DomainUnitTest<Mechanic> {

    def setup() {
    }

    def cleanup() {
    }

    void "test basic persistence mocking"() {
        setup:
        new Mechanic(firstName: 'Robert', lastName: 'Fripp').save()
        new Mechanic(firstName: 'Adrian', lastName: 'Belew').save()

        expect:
        Mechanic.count() == 2
    }

    void "test full name method"() {
        setup:
        Mechanic robert = new Mechanic(firstName: 'Robert', lastName: 'Fripp')

        expect:
        robert.getFullName() == "Robert Fripp"
    }
}
