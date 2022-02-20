package groovy_dsl_book.initial

protected String output() {
    System.out.toString().trim()
}

GroovyShell shell = new GroovyShell()

def helper = shell.parse(new File('helper.groovy'))

