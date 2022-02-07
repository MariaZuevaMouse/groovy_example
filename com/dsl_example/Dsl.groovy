package com.dsl_example

class Dsl {
    static void pipeline(final Closure closure) {
        final PipelineDsl dsl = new PipelineDsl()
        closure.delegate = dsl
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }
}

class PipelineDsl {
    void agent() {

    }

    void environment(final Closure closure) {

    }

    void stages(final Closure closure) {

    }
}
