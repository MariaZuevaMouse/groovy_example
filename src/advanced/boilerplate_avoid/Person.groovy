package advanced.boilerplate_avoid

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

// Declare convenience annotations on class-level
//@ToString
//@EqualsAndHashCode
//@TupleConstructor
@Canonical
class Person {

    String firstName
    String lastName
    int age

}
