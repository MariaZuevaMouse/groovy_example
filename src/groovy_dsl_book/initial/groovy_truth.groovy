package groovy_dsl_book.initial

// Groovy equivalent does not need to form a boolean expression
def n = 1;
if (n) {
}

//Boolean true value:
given: "An initialized value"
    String initialized = "Some Value"
    Customer customer = new Customer(name: "Joey")
    def array = [1,2,3]
    def map = [a:1,b:2]
expect: "it will evaluate to true"
    initialized
    customer
    array
    map

given: "A null uninitialized or empty value"
    String nullString = null
    String uninitializedString
    Customer customer1 = null
    def array1 = []
    def map1 = [:]
    def emptyString = ''
expect: "it will evaluate to false"
    !nullString
    !uninitializedString
    !customer1
    !array1
    !map1
    !emptyString


//any class can implement
//the asBoolean method to define what it means for it to be true:
enum Status {
    ACTIVE,
    INACTIVE,
    DELETED
    def asBoolean () {
        this == Status.ACTIVE
    }
}

expect: "Only Status.ACTIVE will return true from asBoolean"
    Status.ACTIVE
    !Status.INACTIVE
    !Status.DELETED
