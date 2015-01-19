package com.github.hastebrot.gradle.javafxDeployPlugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class JavafxDeployTask extends DefaultTask {

    @TaskAction
    void task() {
        println "Deploy in ${this.class.simpleName}."
    }

}
