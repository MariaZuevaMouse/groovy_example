package groovy_dsl_book.initial

class Base {
    public static void main(String[] args){
        def lorem =
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit"
        println lorem
        def matcher = lorem =~ " +"
        def removed = matcher.replaceAll(" ")
        println removed

        def builder = new groovy.xml.MarkupBuilder()
        println builder.book {
            author 'Fergal Dearle'
            title 'Groovy for DSL'
        }

        builder.book {
            author {
                first_name 'Fergal'
                surname 'Dearle'
            }
            title 'Groovy for DSL'
        }
    }
}
