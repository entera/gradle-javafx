# gradle-javafx [![Build Status](https://travis-ci.org/entera/gradle-javafx.svg?branch=master)](https://travis-ci.org/entera/gradle-javafx)

Gradle plugins for JavaFX. &mdash; https://github.com/entera/gradle-javafx


## Status

This project was started on January 19, 2015 and is not ready for production, yet. :smiley_cat:



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


## License

~~~
Copyright 2015 Benjamin Gudehus <hastebrot@gmail.com>

Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
European Commission - subsequent versions of the EUPL (the "Licence"); You may
not use this work except in compliance with the Licence.

You may obtain a copy of the Licence at:
http://ec.europa.eu/idabc/eupl

Unless required by applicable law or agreed to in writing, software distributed
under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
specific language governing permissions and limitations under the Licence.
~~~
