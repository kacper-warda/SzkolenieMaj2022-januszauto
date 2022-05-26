package januszauto

import grails.gorm.transactions.Transactional

class BootStrap {

    def init = { servletContext ->
        addFirstUser()
    }
    def destroy = {
    }

    @Transactional
    void addFirstUser(){
        Person kacper = Person.findByUsername("kacper")
        if(kacper == null){
            new Person(username: "kacper", password: "test")
                    .save(flush: true, failOnError: true)
        }

        Role admin = Role.findByAuthority("ROLE_ADMIN")
        if(admin == null){
            admin = new Role(authority: "ROLE_ADMIN")
                    .save(flush: true, failOnError: true)

            new PersonRole(person: kacper, role: admin)
                    .save(flush: true, failOnError: true)
        }
    }
}
