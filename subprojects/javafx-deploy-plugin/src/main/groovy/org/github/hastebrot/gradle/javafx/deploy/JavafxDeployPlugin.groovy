package org.github.hastebrot.gradle.javafx.deploy

import org.gradle.api.Plugin
import org.gradle.api.Project

class JavafxDeployPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.task("hello") << {
            println "Hello from JavafxDeployPlugin!"
        }
    }

}
