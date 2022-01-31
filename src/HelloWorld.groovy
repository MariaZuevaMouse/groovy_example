class HelloWorld {
    static void main(String[] args) {
        println "Hello world"

        int age = 40
        println age
        println age.getClass()

        def name = "John"
        println name
        println name.getClass()

        Person jhonDoe = new Person()
        jhonDoe.setFirstName("Jhon")
        jhonDoe.setLastName("Doe")
        jhonDoe.setAge(30)

        println jhonDoe.getFullName()
        println jhonDoe.getAge()

        if (jhonDoe.getAge() >= 45 && jhonDoe.getAge() <= 65) {
            println jhonDoe.getFullName() + " is middle-aged"
        } else {
            println jhonDoe.getFullName() + " is " + jhonDoe.getAge() + " years old"
        }

        def persons = [jhonDoe, new Person(firstName: "Mary", lastName: "Hill", age: 33)]

        for(Person p: persons){
            println p.getFullName()
        }

        try{
            jhonDoe.getFirstName().toLong()
        }catch(NumberFormatException e){
            assert e instanceof NumberFormatException
            println "Cannot convert a String into a Long"
        }

        jhonDoe.setFirstName("Jhonny")
        println jhonDoe.getFirstName()
        println jhonDoe.getFirstName().dropRight(2)
    }
}
