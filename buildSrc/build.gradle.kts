plugins {
    `kotlin-dsl`
//    kotlin("jvm") version "1.8.20-dev-1278"
}

repositories {
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
}

dependencies {
    implementation(libs.gradle.plugin.kotlin)
    implementation(libs.gradle.plugin.diktat)
    implementation(libs.gradle.plugin.detekt)
}