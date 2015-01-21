# gradle-javafx: JavaFX Plugins for Gradle

~~~
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

- http://docs.oracle.com/javafx/2/deployment/packager.htm
- http://docs.oracle.com/javase/8/docs/technotes/guides/deploy/part_packaging.html
