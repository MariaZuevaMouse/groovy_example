package groovy_dsl_book.initial

def a = 1  //[1,0,2,true,false]
given:
    def b = 'value1'
    def c = 'value2'
and: "a ternary expression"
    def result1 = (a ? b : c)
and: "the logical equivalent using if and condition"
    def result2
    if (a) {
        result2 = b
    } else {
        result2 = c
    }
expect: "these expressions are equivalent for various values of a"
    result1 == result2
where:
    a << [1,0,2,true,false]


//So, (a ? : b) is equivalent to (a ? a : b).
a = "val"
given:
    b = 'value1'
and: "a ternary expression"
    result1 = (a ?: b)
and: "the logical equivalent using if and condition"
    result2
    if (a) {
        result2 = a
    } else {
        result2 = b
    }
expect: "these expressions are equivalent for various values of a"
    result1 == result2
where:
    a << [1,0,2,true,false]
