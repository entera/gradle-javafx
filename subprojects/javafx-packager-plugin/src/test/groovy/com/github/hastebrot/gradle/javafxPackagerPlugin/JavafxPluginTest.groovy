package com.github.hastebrot.gradle.javafxPackagerPlugin

import com.github.hastebrot.gradle.javafxPackagerPlugin.domain.JavafxConfig
import com.github.hastebrot.gradle.javafxPackagerPlugin.task.DeployTask
import com.github.hastebrot.gradle.javafxPackagerPlugin.task.CreateJarTask
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
        def builder = ProjectBuilder.builder()
        //builder.withProjectDir()
        project = builder.build()
        project.apply(plugin: "javafx-packager-plugin")
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
