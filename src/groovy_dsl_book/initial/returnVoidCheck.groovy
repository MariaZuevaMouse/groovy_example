package groovy_dsl_book.initial

void returnVoid(param) {
    println param
}

when: "calling a method with void return, passing String"
    def result = returnVoid("Fred")
then: "type of returned value is null"
    !result
    result == null
when: "calling a method with void return, passing Integer"
    result = returnVoid(123)
then: "type of returned value is null"
    !result
    result == null

//groovy src/groovy_dsl_book/initial/returnVoidCheck.groovy returnVoid