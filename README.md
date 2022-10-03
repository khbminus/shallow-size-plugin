![Gradle build](https://github.com/cscenter/Kotlin-homework-optional-compiler/actions/workflows/gradle-build.yml/badge.svg)
![Gradle test](https://github.com/cscenter/Kotlin-homework-optional-compiler/actions/workflows/test.yml/badge.svg)
![Detekt](https://github.com/cscenter/Kotlin-homework-optional-compiler/actions/workflows/detekt.yml/badge.svg)
![Diktat](https://github.com/cscenter/Kotlin-homework-optional-compiler/actions/workflows/diktat.yml/badge.svg)

### Optional homework: Kotlin compiler plugin

Make a Kotlin compiler plugin to calculate shallow size 
(the sum of sizes of all backing fields of the class in bytes) of all data classes. 
The plugin must add a new method `shallowSize` that returns the calculated size. 
We can assume that all classes in the project have different names.

Requirements: 
- convenient interaction with the plugin through CLI (enable/disable the plugin, ignore data classes by their names);
- to implement the plugin it is forbidden to use libraries for writing plugins, such as [arrow-meta](https://github.com/arrow-kt/arrow-meta), 
you must write the plugin directly vis the compiler API;
- the plugin must use FIR-frontend and IR-backend;
- the plugin must contain tests (with [the Kotlin compiler tests framework](https://github.com/JetBrains/kotlin/tree/master/compiler/test-infrastructure) 
or with [this](https://github.com/tschuchortdev/kotlin-compile-testing) library).


