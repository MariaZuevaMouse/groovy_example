package com.dsl_example

import groovy.transform.NamedParam
import groovy.transform.NamedParams

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

    void stages(@DelegatesTo(value = StagesDsl, strategy = DELEGATE_ONLY) final Closure closure) {
        final StagesDsl dsl = new StagesDsl()

        closure.delegate = dsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()

        println dsl.stages
        dsl.stages.each { stage ->
            stage.run()
        }
    }

    enum Placeholder {
        ANY
    }
}

class StagesDsl {
    protected final List<Stage> stages = []

    void stage(final String name, @DelegatesTo(value = StageDsl, strategy = DELEGATE_ONLY) final Closure closure) {
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

    def void run() {
        println "==> Running stage '${name}'..."
        final StageDsl dsl = new StageDsl()

        closure.delegate = dsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}

class StageDsl {
    void steps(@DelegatesTo(value = Steps, strategy = DELEGATE_ONLY) final Closure closure) {
        final Steps steps = new Steps()

        closure.delegate = steps
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}

class Steps {
    void sh(final String script) {
        sh(script: script, returnStdout: false)
    }

    Object sh(@NamedParams([
            @NamedParam(value = "script", type = String, required = true),
            @NamedParam(value = "returnStdout", type = Boolean)

    ]) final Map param) {
        final  Process p = param.script.toString().execute()
        p.waitFor()

        println "+ ${param.script}"

        if(p.exitValue() == 0){
            if(param.returnStdout){
                return  p.text
            }
            println p.text
        }else {
            println p.err.text
        }

    }

    void echo(final String mesage) {
        println "[ECHO} ${mesage}"
    }
}




