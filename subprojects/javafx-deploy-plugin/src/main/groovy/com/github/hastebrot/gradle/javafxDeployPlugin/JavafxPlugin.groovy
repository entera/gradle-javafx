package com.github.hastebrot.gradle.javafxDeployPlugin

import com.github.hastebrot.gradle.javafxDeployPlugin.domain.JavafxConfig
import com.github.hastebrot.gradle.javafxDeployPlugin.task.DeployTask
import com.github.hastebrot.gradle.javafxDeployPlugin.task.CreateJarTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.SourceSet

class JavafxPlugin implements Plugin<Project> {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    static final String JAVAFX_CONFIG = "javafx"

    static final String CREATE_JAR_TASK = "javafxCreateJar"
    static final String DEPLOY_TASK = "javafxDeploy"

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private Project project

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    void apply(Project project) {
        this.project = project
        this.registerExtensions()
        this.registerTasks()
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------


    private void registerExtensions() {
        this.project.extensions.create(JAVAFX_CONFIG, JavafxConfig)
    }

    private void registerTasks() {
        this.project.plugins.apply(JavaPlugin)

        this.project.sourceSets {
            "package" {
                resources {
                    srcDir "src/deploy/package"
                    srcDir "src/deploy/resources"
                }
            }
        }

        def createJarTask = project.tasks.replace(CREATE_JAR_TASK, CreateJarTask)

        project.afterEvaluate {
            def mainSourceSet = this.project.sourceSets["main"] as SourceSet
            createJarTask.dependsOn = [mainSourceSet.jarTaskName]

            def javafxConfig = this.project.extensions.getByName(JAVAFX_CONFIG) as JavafxConfig
            createJarTask.mainClass = javafxConfig.mainClass

            //File libsDir = new File(project.buildDir, "libs")
            //createJarTask.jarFile = project.file(project.fileTree(libsDir).singleFile)
            //createJarTask.classpath = project.files(project.fileTree(libsDir).files)

            def javaJarTask = project.tasks.getByName(mainSourceSet.jarTaskName)
            createJarTask.jarFile = javaJarTask.archivePath
            createJarTask.classpath = mainSourceSet.compileClasspath
        }

        def deployTask = this.project.tasks.replace(DEPLOY_TASK, DeployTask)

        this.project.afterEvaluate {
            def extensionParams = this.project.extensions.getByName(JAVAFX_CONFIG) as JavafxConfig
            deployTask.mainClass = extensionParams.mainClass

            SourceSet sourceSet = this.project.sourceSets["package"]
            deployTask.distributionsRootDir = sourceSet.output.resourcesDir
        }
    }

}
