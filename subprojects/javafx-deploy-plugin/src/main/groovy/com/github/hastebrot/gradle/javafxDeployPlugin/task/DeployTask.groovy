package com.github.hastebrot.gradle.javafxDeployPlugin.task

import com.oracle.tools.packager.Bundler
import com.oracle.tools.packager.Bundlers
import com.oracle.tools.packager.RelativeFileSet
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

import static com.oracle.tools.packager.StandardBundlerParam.APP_NAME
import static com.oracle.tools.packager.StandardBundlerParam.MAIN_CLASS
import static com.oracle.tools.packager.StandardBundlerParam.VERSION
import static com.oracle.tools.packager.StandardBundlerParam.CLASSPATH
import static com.oracle.tools.packager.StandardBundlerParam.APP_RESOURCES

class DeployTask extends ConventionTask {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    @OutputDirectory
    File distributionsRootDir

    String mainClass

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    @TaskAction
    void deploy() {
        println "mainClass: ${this.mainClass}"

        File libsDir = new File(project.buildDir, "libs")

        File appLayoutDir = new File(project.buildDir, "tmp/deployJavafx")
        project.delete(appLayoutDir)
        appLayoutDir.mkdirs()

        println project.fileTree(libsDir).files

        project.copy {
            from project.fileTree(libsDir).files
            into appLayoutDir
        }



        def outputDir = this.distributionsRootDir
        def bundlers = Bundlers.createBundlersInstance()
        for (Bundler bundler in bundlers.bundlers) {
            if (bundler.name == "Windows Application Image") {
                def params = [:]

                params[APP_NAME.ID] = "foo"
                params[VERSION.ID] = "0.0.0"

                params[MAIN_CLASS.ID] = this.mainClass
                params[APP_RESOURCES.ID] = new RelativeFileSet(appLayoutDir,
                    project.fileTree(appLayoutDir).files)
                params[CLASSPATH.ID] = new RelativeFileSet(appLayoutDir,
                    project.fileTree(appLayoutDir).files).includedFiles.join(' ')

                println params

                if (bundler.validate(params)) {
                    bundler.execute(params, outputDir)
                }
            }
        }
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

}
