# gradle-javafx [![Build Status](https://travis-ci.org/hastebrot/gradle-javafx.svg?branch=master)](https://travis-ci.org/hastebrot/gradle-javafx)

Gradle plugins for JavaFX.

## Usage

~~~groovy
buildscript {
    dependencies {
        classpath project(":javafx-packager-plugin")
    }
}

apply plugin: "javafx-packager-plugin"

javafx {
    name "foo"
    version "0.0.0"
    mainClass "org.foo.Main"
}
~~~

~~~
gradle --daemon jar javafxCreateJar javafxDeploy
~~~

## Documentation

- http://gradle.org/docs/current/dsl/org.gradle.api.Project.html


- http://docs.oracle.com/javafx/2/deployment/packager.htm
- http://docs.oracle.com/javase/8/docs/technotes/guides/deploy/part_packaging.html
- http://docs.oracle.com/javase/8/docs/technotes/guides/deploy/packager.html
