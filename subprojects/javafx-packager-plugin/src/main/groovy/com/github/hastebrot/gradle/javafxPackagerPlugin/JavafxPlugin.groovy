package com.github.hastebrot.gradle.javafxPackagerPlugin

import com.github.hastebrot.gradle.javafxPackagerPlugin.domain.JavafxConfig
import com.github.hastebrot.gradle.javafxPackagerPlugin.task.CreateJarTask
import com.github.hastebrot.gradle.javafxPackagerPlugin.task.DeployTask
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

        def createJarTask = project.task(type: CreateJarTask, dependsOn: "jar", CREATE_JAR_TASK)
        //createJarTask.dependsOn = [mainSourceSet.jarTaskName]

        def deployTask = project.task(type: DeployTask, dependsOn: CREATE_JAR_TASK, DEPLOY_TASK)

        project.afterEvaluate {
            def mainSourceSet = this.project.sourceSets["main"] as SourceSet

            def javafxConfig = this.project.extensions.getByName(JAVAFX_CONFIG) as JavafxConfig
            createJarTask.mainClass = javafxConfig.mainClass

            //File libsDir = new File(project.buildDir, "libs")
            //createJarTask.jarFile = project.file(project.fileTree(libsDir).singleFile)
            //createJarTask.classpath = project.files(project.fileTree(libsDir).files)

            project.configure(createJarTask) {
                def javaJarTask = project.tasks.getByName(mainSourceSet.jarTaskName)
                jarFile = javaJarTask.archivePath
                classpath = mainSourceSet.compileClasspath

            }
        }

        this.project.afterEvaluate {
            def extensionParams = this.project.extensions.getByName(JAVAFX_CONFIG) as JavafxConfig
            deployTask.mainClass = extensionParams.mainClass

            SourceSet sourceSet = this.project.sourceSets["package"]
            deployTask.distributionsRootDir = sourceSet.output.resourcesDir
        }
    }

}
