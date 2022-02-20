package groovy_dsl_book.initial
//Spread and spread-dot
//Null safe dereference
//Operator overloading

//Spread and spread-dot
given: "a map and two arrays with the same keys and values"
    def map = [a:"apple", o:"orange", p:"pear"]
    def keys = ["a", "o", "p"]
    def values = ["apple", "orange", "pear"]
expect: "we can use spread dot to access all keys/values"
    map*.key == keys
    map*.value == values
and: "which is equivalent to using the collect method"
    map.collect { it.key } == keys
    map.collect { it.value } == values


class Name {
    def name
    def greet(greeting) {
        println greeting + " " + name
    }
}

//rootDir = new File('.').absolutePath
GroovyShell shell = new GroovyShell()

def helper = shell.parse(new File('helper.groovy'))

given: "An array of Name objects"
    def names = [ new Name(name:"Aaron"),
                  new Name(name:"Bruce"),
                  new Name(name:"Carol")]
when: "we invoke a method via spread dot"
    names*.greet("Hello")
then: "the method is called in sequence across all the members"
    """Hello, Aaron
    Hello, Bruce
//    Hello, Carol""" == helper.output()

//A close relative of spread-dot is the spread operator. Spread has the effect of tearing
//a list apart into its constituent elements, as shown here:
and: "a closure that expects three parameters"
    def greetAll = { a, b, c ->
        println "Hello $a, $b and $c"
    }
when: "we use spread against the names array"
    greetAll(*names.name)
then: "It explodes the names array into three separate objects"
    "Hello Arron, Bruce and Carol"


//Null safe dereference
Customer customer = new Customer();

if (customer != null) {
    String name = customer.getName();
}

String name = customer?.name;

//Operator overloading

given:
    def today = new Date()
    def tomorrow = today + 1
    def yesterday = today - 1
expect:
    today.plus(1) == tomorrow
    tomorrow.minus(1) == today
    today.minus(1) == yesterday

