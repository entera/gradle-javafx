package org.github.hastebrot.gradle.javafx.deploy

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GreetTask extends DefaultTask {

    @TaskAction
    def greet() {
        println "Greetings from ${this.class.simpleName}!"
    }

}
