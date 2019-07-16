import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
}

group = "dev.aravindh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven("https://dl.bintray.com/arrow-kt/arrow-kt/" )
    maven("https://oss.jfrog.org/artifactory/oss-snapshot-local/" )
}
val arrow_version = "0.9.1-SNAPSHOT"

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("io.arrow-kt:arrow-core-data:$arrow_version")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}