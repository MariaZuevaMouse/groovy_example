package groovy_dsl_book.initial

def say (String message) {
    [ to: { person ->
        println "$message, $person!"
    }]
}

say "Hello" to "Fred"
// which is similar to calling
say("Goodbye")['to'].call('Barney')

greeting = { name -> return "Hello, " + name }
// Parens are optional for println but required for nested
// greeting call
println greeting ( "Fergal" )