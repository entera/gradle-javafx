package org.github.hastebrot.gradle.javafx.deploy

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.hamcrest.CoreMatchers.instanceOf
import static org.hamcrest.MatcherAssert.assertThat

class JavafxDeployPluginTest {

    @Test
    public void should_add_greet_task() {
        // given:
        Project project = ProjectBuilder.builder().build()

        // when:
        project.apply plugin: "javafx.deploy.plugin"

        // then:
        assertThat(project.tasks.greet, instanceOf(GreetTask))
    }

}
