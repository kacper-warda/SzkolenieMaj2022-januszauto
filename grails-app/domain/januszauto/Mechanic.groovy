package januszauto

class Mechanic {

    String firstName
    String lastName
    String title
    String gender
    Integer age

    static constraints = {
        age nullable: true, blank: true
        gender nullable: true, blank: true
    }

    String getFullName() {
        return this.firstName + " " + this.lastName
    }
}
