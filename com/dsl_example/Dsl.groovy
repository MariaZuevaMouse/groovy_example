package com.dsl_example

import java.util.concurrent.ConcurrentHashMap

import static groovy.lang.Closure.DELEGATE_FIRST
import static groovy.lang.Closure.DELEGATE_ONLY

//import static groovy.lang.Closure.DELEGATE_ONLY

class Dsl {
    static void pipeline(@DelegatesTo(value = PipelineDsl, strategy = DELEGATE_ONLY) final Closure closure) {
        final PipelineDsl dsl = new PipelineDsl()
        closure.delegate = dsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}

class PipelineDsl {
    final Placeholder any = Placeholder.ANY

    static final ConcurrentHashMap<String, String> env = [:] as ConcurrentHashMap

    void agent(final Placeholder any) {
        println "Running pipeline using any available agent"
    }

    void environment(@DelegatesTo(value = Map, strategy = DELEGATE_FIRST) final Closure closure) {
        env.with(closure)
        println env
    }

    void stages(@DelegatesTo(value = StagesDsl, strategy = DELEGATE_ONLY)final Closure closure) {
        final StagesDsl dsl = new StagesDsl()

        closure.delegate = dsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()

        println dsl.stages
    }

    enum Placeholder {
        ANY
    }
}

class StagesDsl {
    protected final List<Stage> stages = []

    void stage(final String name, final Closure closure) {
        stages << new Stage(name, closure)
    }
}

class Stage {
    final String name
    final Closure closure

    Stage(String name, Closure closure) {
        this.name = name
        this.closure = closure
    }
}





