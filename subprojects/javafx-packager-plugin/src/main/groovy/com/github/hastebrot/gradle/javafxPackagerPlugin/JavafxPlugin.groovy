package com.github.hastebrot.gradle.javafxPackagerPlugin

import com.github.hastebrot.gradle.javafxPackagerPlugin.domain.JavafxConfig
import com.github.hastebrot.gradle.javafxPackagerPlugin.task.CreateJarTask
import com.github.hastebrot.gradle.javafxPackagerPlugin.task.DeployTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.SourceSetContainer

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
    private SourceSetContainer sourceSets

    private CreateJarTask createJarTask
    private DeployTask deployTask

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    void apply(Project project) {
        this.project = project

        // Apply JavaPlugin to ensure that the source sets container and jar task are available.
        this.project.plugins.apply(JavaPlugin)
        this.sourceSets = this.project.sourceSets

        this.registerExtensions()
        this.registerTasks()

        this.project.afterEvaluate {
            this.configureTasks()
        }
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private void registerExtensions() {
        this.project.extensions.create(JAVAFX_CONFIG, JavafxConfig)
    }

    private void registerTasks() {
        def mainSourceSet = this.sourceSets["main"]
        def packageSourceSet = this.sourceSets.create("package") {
            resources {
                srcDir "src/deploy/package"
                srcDir "src/deploy/resources"
            }
        }

        def javaJarTask = this.project.tasks.getByName(mainSourceSet.jarTaskName)

        this.createJarTask = this.project.task(type: CreateJarTask, CREATE_JAR_TASK) as CreateJarTask
        this.createJarTask.dependsOn = [javaJarTask.name]

        this.deployTask = this.project.task(type: DeployTask, DEPLOY_TASK) as DeployTask
        this.deployTask.dependsOn = [CREATE_JAR_TASK]
    }

    private void configureTasks() {
        def javafxConfig = this.project.extensions.getByName(JAVAFX_CONFIG) as JavafxConfig

        def mainSourceSet = this.sourceSets["main"]
        def packageSourceSet = this.sourceSets["package"]

        def javaJarTask = this.project.tasks.getByName(mainSourceSet.jarTaskName)

        this.project.configure(this.createJarTask) {
            this.createJarTask.mainClass = javafxConfig.mainClass

            this.createJarTask.jarFile = javaJarTask.archivePath
            this.createJarTask.classpath = mainSourceSet.compileClasspath
        }

        this.project.configure(this.deployTask) {
            this.deployTask.mainClass = javafxConfig.mainClass
            this.deployTask.distributionsRootDir = packageSourceSet.output.resourcesDir
        }
    }

}
