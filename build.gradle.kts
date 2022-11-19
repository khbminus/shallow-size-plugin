import buildutils.configureDetekt
import buildutils.createDetektTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.research.code.submissions.clustering.buildutils.configureDiktat
import org.jetbrains.research.code.submissions.clustering.buildutils.createDiktatTask

plugins {
    `maven-publish`
    alias(libs.plugins.kosogor)
    alias(libs.plugins.buildconfig) apply false
    alias(libs.plugins.dokka)
    id(libs.plugins.kotlin.jvm.get().pluginId)
}

group = "org.example"
version = "1.0-SNAPSHOT"

allprojects {
    apply {
        plugin("java")
        plugin("kotlin")
    }

    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    configureDiktat()
    configureDetekt()
}

createDiktatTask()
createDetektTask()