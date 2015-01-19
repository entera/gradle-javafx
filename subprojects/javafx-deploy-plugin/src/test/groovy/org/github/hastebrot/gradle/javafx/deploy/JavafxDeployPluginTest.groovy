package org.github.hastebrot.gradle.javafx.deploy

import org.github.hastebrot.gradle.javafx.deploy.task.JavafxDeployTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import static org.hamcrest.CoreMatchers.instanceOf
import static org.hamcrest.MatcherAssert.assertThat

class JavafxDeployPluginTest {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    Project project

    //---------------------------------------------------------------------------------------------
    // FIXTURE METHODS.
    //---------------------------------------------------------------------------------------------

    @Before
    public void setup() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: "javafx.deploy.plugin"
    }

    //---------------------------------------------------------------------------------------------
    // FEATURE METHODS.
    //---------------------------------------------------------------------------------------------

    @Test
    public void should_register_tasks() {
        // expect:
        assertThat(project.tasks.javafxDeploy, instanceOf(JavafxDeployTask))
    }

    @Test
    public void should_register_extensions() {
        // expect:
        assertThat(project.extensions.javafx, instanceOf(JavafxDeployExtension))
    }

}
