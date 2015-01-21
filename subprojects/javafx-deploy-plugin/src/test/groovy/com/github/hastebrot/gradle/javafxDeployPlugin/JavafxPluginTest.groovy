package com.github.hastebrot.gradle.javafxDeployPlugin

import com.github.hastebrot.gradle.javafxDeployPlugin.domain.JavafxConfig
import com.github.hastebrot.gradle.javafxDeployPlugin.task.DeployTask
import com.github.hastebrot.gradle.javafxDeployPlugin.task.CreateJarTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import static org.hamcrest.CoreMatchers.instanceOf
import static org.hamcrest.MatcherAssert.assertThat

class JavafxPluginTest {

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
        project.apply plugin: "com.github.hastebrot.gradle.javafxDeployPlugin"
    }

    //---------------------------------------------------------------------------------------------
    // FEATURE METHODS.
    //---------------------------------------------------------------------------------------------

    @Test
    public void should_register_tasks() {
        // expect:
        assertThat(project.tasks.javafxCreateJar, instanceOf(CreateJarTask))
        assertThat(project.tasks.javafxDeploy, instanceOf(DeployTask))
    }

    @Test
    public void should_register_extensions() {
        // expect:
        assertThat(project.extensions.javafx, instanceOf(JavafxConfig))
    }

}
