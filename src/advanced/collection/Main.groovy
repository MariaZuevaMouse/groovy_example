package advanced.collection

class Main {
    static void main(String[] args) {
        Person johnDoe = new Person("John", "Doe", 40)
        Person maryHill = new Person("Mary", "Hill", 30)
        Person thomasMarks = new Person("Thomas", "Marks", 21)

        // Create a new list of persons
        def allPerson = [johnDoe, maryHill, thomasMarks]

        // Querying Collections
        assert allPerson instanceof List
        assert allPerson.size() == 3
        assert allPerson.get(2) == thomasMarks

        // Iterate over elements
        allPerson.each {
            println it
        }

        // Iterate over elements and using an index
        allPerson.eachWithIndex { Person entry, int i ->
            println i + ": " + entry
        }

        // Filtering a specific element
        allPerson.find { it.lastName == "Hill" } == maryHill

        // Transforming elements into something else
        allPerson.collect { it.age <= 30 } == [false, true, true]

        // Sorting elements based on a criterion
        allPerson.sort { it.age } == [thomasMarks, maryHill, johnDoe]
    }
}
