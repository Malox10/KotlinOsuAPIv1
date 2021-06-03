import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
}

group = "com.github.Malox10"
version = "1.0.0"

repositories {
    mavenCentral()
}

val ktor_version = "1.3.0"

dependencies {
    implementation("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")
    implementation("io.ktor","ktor-client-cio", ktor_version)
    implementation("io.ktor", "ktor-client-json", ktor_version)
    implementation("io.ktor", "ktor-client-gson", ktor_version)
    implementation("com.google.code.gson","gson", "2.8.6" )
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}