package com.github.hastebrot.gradle.javafxDeployPlugin.task

import com.oracle.tools.packager.Bundlers
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class JavafxDeployTask extends DefaultTask {

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    @TaskAction
    void task() {
        println "Deploy in ${this.class.simpleName}."

        Bundlers bundlers = Bundlers.createBundlersInstance()
        println bundlers.bundlers
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

}
