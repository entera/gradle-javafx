package com.github.hastebrot.gradle.javafxPackagerPlugin.task

import com.sun.javafx.tools.packager.CreateJarParams
import com.sun.javafx.tools.packager.PackagerLib
import org.gradle.api.file.FileCollection
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction

class CreateJarTask extends ConventionTask {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    @InputFile
    File jarFile

    @InputFiles
    FileCollection classpath

    String mainClass
    List<String> arguments

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    @TaskAction
    void create() {
        def jarParams = new CreateJarParams();

        jarParams.addResource(null, this.jarFile)
        jarParams.applicationClass = this.mainClass
        jarParams.arguments = this.arguments
        jarParams.classpath = this.classpath.files.collect { it.name }.join(";")
        jarParams.css2bin = false
        //jarParams.embedLauncher = this.embedLauncher
        jarParams.outdir = this.jarFile.parentFile
        jarParams.outfile = this.jarFile.name

        def packager = new PackagerLib()
        packager.packageAsJar(jarParams)
    }

}
