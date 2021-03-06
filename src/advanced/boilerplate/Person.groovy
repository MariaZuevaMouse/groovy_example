package advanced.boilerplate

class Person {

    String firstName
    String lastName
    int age

    Person() {
    }

    Person(String firstName, String lastName, int age) {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Person person = (Person) o

        if (age != person.age) return false
        if (firstName != person.firstName) return false
        if (lastName != person.lastName) return false

        return true
    }

    int hashCode() {
        int result
        result = (firstName != null ? firstName.hashCode() : 0)
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0)
        result = 31 * result + age
        return result
    }


    @Override
    String toString() {
        return "Person(" +
                firstName + ", " +
                lastName + ", " +
                age +
                ")"
    }
}
