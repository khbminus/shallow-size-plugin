
rootProject.name = "hw-optional-compiler"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
    }
    plugins {
        kotlin("jvm") version "1.8.20-dev-1278"
    }
}
include("shallowsize-plugin")
