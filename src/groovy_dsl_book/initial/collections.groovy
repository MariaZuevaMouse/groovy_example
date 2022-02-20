package groovy_dsl_book.initial
//Ranges
//Lists
//Maps

//ranges
given: "an inclusive and exclusive range"
    def inclusive = 1..10
    def exclusive = 'a'..<'e'
when: "we collect all the possible values of that range"
    def inclusiveValues = inclusive.collect { it }
    def exclusiveValues = exclusive.collect { it }
then: "result is an inclusive/exclusive list of those values"
    inclusiveValues == [1,2,3,4,5,6,7,8,9,10]
    exclusiveValues == ['a','b','c','d']


given: "some ranges"
    def numbers = 1..100
    def letters = 'a'..'z'
expect: "range has to and from properties"
    numbers.from == 1
    numbers.to == 100
    letters.from == 'a'
    letters.to == 'z'
and: "range is a java.util.List so we can use contains"
    numbers.contains 2
    numbers.contains 5
and: "we can use the in keyword with ranges"
    5 in numbers


given: "a range and the list equivalent"
    def numberList = [1,2,3,4,5,6,7,8,9,10]
    def numberRange = 1..10
expect: "they are equal"
    numberList == numberRange


//There are some useful list operators that we can use as shortcuts with lists, including
//plus, minus, and left shift:
given: "a list within a list"
    def multidimensional = [1,3,5,["apple","orange","pear"]]
expect: "we can add to lists together using the plus operator"
    [1,3,5] + [["apple","orange","pear"]] == multidimensional
and: "also with the left shift operator"
    [1,3,5] << ["apple","orange","pear"] == multidimensional
and: "we can Subtract elements with the minus operator"
    multidimensional - [["apple","orange","pear"]] == [1,3,5]
and: "we can flatten that multi dimensional list"
    multidimensional.flatten() == [1,3,5,"apple","orange","pear"]

//we can see the use of the reverse, collect, and grep find and sort methods and their
//effect on a List:
given: "some Lists"
    def odds = [1,3,5]
    def evens = [2,4,6]
    def animals = ["cat", "dog", "fox", "cow"]
expect: "we can reverse the order of a list"
    odds.reverse() == [5,3,1]
and: "can apply a closure to a list to transform it using collect"
    odds.collect { it + 1 } == evens
and: "we can find in the list using regex"
    animals.grep( ~/.o./ ) == ["dog", "fox", "cow"]
and: "we can sort a list"
    [5,1,3].sort() == odds
and: "we can find elements matching an expression"
    animals.find { it == "dog" } == "dog"


//We can iterate over a list in both directions by applying a closure to each item:
given:
    def list = [1,3,5]
    def number = ''
when: "we iterate forwards"
    list.each { number += it }
then: "the numbers are added to the string in order"
    number == '135'
when: "we iterate backwards"
    number = ''
    list.reverseEach { number += it }
then: "the numbers are added in reverse order"
    number == '531'


//Groovy adds two additional new methods to lists: any and every. These return
//a Boolean value if any or every member of the list, respectively, satisfies the
//given closure:
given:
    def list2 = [1,2,3,5,7,9]
expect: "any member is even because 2 is even"
    list2.any { it % 2 == 0 }
and: "every member is not even"
    ! list2.every { it % 2 == 0 }

//MAPS
//We can access an element of a Map
//using both array style and property access:
given: "we declare a simple map"
    def fruitPrices = ["apple":20,"orange":25,"pear":30]
expect: "we can subscript a map with any key value"
    fruitPrices["apple"] == 20
and: "use the key like it was a property"
    fruitPrices.apple == 20
//We can also access elements of the Map using the get method directly:
expect: "we can retrieve a value using the get method"
    fruitPrices.get("apple") == 20
and: "we can supply a default value for items that are not found"
    fruitPrices.get("grape", 5) == 5

given: "we can declare a variable that is empty but is a map"
    def empty = [:]
expect: "it is an empty map"
    empty instanceof Map
    empty.size() == 0


//When assigning values to Map elements, we can use either the array superscript
//syntax or property access syntax:
when: "assigning a value, it can be done via superscript"
    fruitPrices['apple'] = 21
then: "the expected value was set"
    fruitPrices['apple'] == 21
when: "we try the same with property access"
    fruitPrices.apple = 22
then: "that also works"
    fruitPrices['apple'] == 22
when: "assign a value to a key that does not exist"
    fruitPrices.grape = 6
then: "a new item is added to the Map"
    fruitPrices == [apple:22,orange:25,pear:30, grape:6]
//Maps support the plus operator for adding maps together:
given:
    def fruit = [apple:20, orange:25 ]
    def veg = [pea:1, carrot:15]
expect: "we can add these Maps using plus"
    fruit + veg == [apple:20, orange:25, pea:1, carrot:15]
and: "map equality is agnostic to order"
    fruit + veg == [ pea:1, carrot:15, apple:20, orange:25]


given:
    def squares = [ 1:1, 2:4, 3.0:9]
expect:
    squares[1] == 1
    squares[2] == 4
    squares[3.0] == 9

//Here we see how we can use what seem to be primitive numeric values as keys.
//        Because Groovy autoboxes these primitives into their equivalent wrapper object and
//these wrappers implement consistent hashCode methods, we can use them as keys.
//        Not only this, but we can mix the type of object that we use as a key.


//We also can use object values as keys, but in order to do so, we need to add
//parentheses around them to assist the compiler in determining our intention.
//In the following code, we add two keys to the map. The first key is a string, apple,
//but the second is the value contained in the apple local variable, which is 1:
given:
    def apple = 1
    def map = [ apple:"Red", (apple):"Green"]
then:
    map[1] == "Green"
    map["apple"] == "Red"
