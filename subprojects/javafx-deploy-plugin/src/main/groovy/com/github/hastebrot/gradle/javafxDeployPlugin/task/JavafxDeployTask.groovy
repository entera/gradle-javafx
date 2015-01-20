package com.github.hastebrot.gradle.javafxDeployPlugin.task

import com.oracle.tools.packager.Bundler
import com.oracle.tools.packager.Bundlers
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.TaskAction

import static com.oracle.tools.packager.StandardBundlerParam.MAIN_CLASS

class JavafxDeployTask extends ConventionTask {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    String mainClass

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    @TaskAction
    void taskAction() {
        println "mainClass: ${this.mainClass}"

        def outputDir = new File("output")
        def bundlers = Bundlers.createBundlersInstance()
        for (Bundler bundler in bundlers.bundlers) {
            if (bundler.name == "Windows Application Image") {
                def params = [:]
                params[MAIN_CLASS] = this.mainClass
                //if (bundler.validate(params)) {
                //    outputDir.mkdirs()
                //    bundler.execute(params, outputDir)
                //}
            }
        }
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

}
