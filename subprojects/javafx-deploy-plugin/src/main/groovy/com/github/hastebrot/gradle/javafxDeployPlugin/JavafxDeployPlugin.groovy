package com.github.hastebrot.gradle.javafxDeployPlugin

import com.github.hastebrot.gradle.javafxDeployPlugin.task.JavafxDeployTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

class JavafxDeployPlugin implements Plugin<Project> {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    static final String JAVAFX_EXTENSION = "javafx"
    static final String JAVAFX_DEPLOY_TASK = "javafx.deploy"

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private Project project

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    void apply(Project project) {
        this.project = project
        this.project.plugins.apply(JavaPlugin)

        this.registerExtensions()
        this.registerTasks()
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private void registerExtensions() {
        this.project.extensions.create(JAVAFX_EXTENSION, JavafxDeployExtension)
    }

    private void registerTasks() {
        def task = this.project.tasks.replace(JAVAFX_DEPLOY_TASK, JavafxDeployTask)

        this.project.afterEvaluate {
            def extensionParams = this.project[JAVAFX_EXTENSION] as JavafxDeployExtension
            task.mainClass = extensionParams.mainClass
        }
    }

}
