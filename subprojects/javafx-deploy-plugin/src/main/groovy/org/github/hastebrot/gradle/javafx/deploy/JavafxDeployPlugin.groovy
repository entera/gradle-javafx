package org.github.hastebrot.gradle.javafx.deploy

import org.github.hastebrot.gradle.javafx.deploy.task.JavafxDeployTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class JavafxDeployPlugin implements Plugin<Project> {

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
        this.project.extensions.create("javafx", JavafxDeployExtension)
    }

    private void registerTasks() {
        this.project.tasks.replace("javafxDeploy", JavafxDeployTask)
    }

}
