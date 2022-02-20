package groovy_dsl_book.initial

class Customer{
    def name
}

given: "a Groovy object"
    Customer customer = new Customer(name:"Daniel Dewdney")
    def string1 = "Customer name is Daniel Dewdney"
    def string2 = "Customer name is ${customer.name}"
expect: "We can compare both strings as if the are equivalent"
    string1 == string2
and: "they are implemented by two different classes"
    string1 instanceof String
    string2 instanceof GString



given: "some multiline strings with single and double quotes"
    String multiLine = '''Line one
     Line two
     guage
    [ 64 ]
     "We don't need to escape quotes in a multi-line string"
     '''
    def name = "Daniel Dewdney"
    def customerSelectSQL = """
     select * from customer where name = ('${name}');
     """
expect:
    multiLine instanceof String
    customerSelectSQL instanceof GString
