package groovy_dsl_book.initial

//The spaceship operator is comprised of two angle brackets
//and an equals sign <=> and is so named because it resembles a UFO or spaceship in
//flight. Spaceship is a shorthand operator that works the same as Java's compareTo
//method. In other words, it compares two operands and returns 0 if they are equal, -1
//if the first is less than the second, and 1 if the first is greater than the second.

def a = 0
def b = 1
def c = 1

expect:
    a == (b <=> c)
and:
    (b <=> c) == b.compareTo(c)
where:
    a  | b | c
    -1 | 1 | 2
    0  | 1 | 1
    1  | 2 | 1



class Balance {
    String first
    String last
    BigDecimal balance
    String toString() { "$last, $first : $balance"}
}

//We can express the sort very succinctly using a combination of spaceship
//comparators and Elvis operators:
//        a.balance<=>b.balance ?: a.last<=>b.last ?: a.first<=>b.first


given: "we have a few customer account objects"
    def accounts = [
            new Balance(balance: 200.00,
                    first:"Fred", last:"Flintstone"),
            new Balance(balance: 100.00,
                    first:"Wilma", last:"Flintstone"),
            new Balance(balance: 100.00,
                    first:"Barney", last:"Rubble"),
            new Balance(balance: 100.00,
                    first:"Betty", last:"Rubble"),
    ]
when: "we sort these with spaceship Elvis operators"
    accounts.sort { it1, it2 ->
        it1.balance <=> it2.balance ?:
                it1.last <=> it2.last ?: it1.first <=> it2.first
    }.each { println it }
then: "the accounts are sorted by balance - last - first"
    """Flintstone, Wilma : 100.00
    Rubble, Barney : 100.00
    Rubble, Betty : 100.00
    Flintstone, Fred : 200.00"""


