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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20-dev-1278")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.20.0")
    implementation("org.cqfn.diktat:diktat-gradle-plugin:1.1.0")
}