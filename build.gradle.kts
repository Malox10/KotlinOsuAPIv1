import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
    `maven-publish`
    application
}

group = "me.malox10"
version = "1.0.1"

repositories {
    mavenCentral()
}

val ktorVersion = "1.3.0"

dependencies {
    implementation("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")
    implementation("io.ktor","ktor-client-cio", ktorVersion)
    implementation("io.ktor", "ktor-client-json", ktorVersion)
    implementation("io.ktor", "ktor-client-gson", ktorVersion)
    implementation("com.google.code.gson","gson", "2.8.6" )
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/malox10/kotlinosuapiv1")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USER")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register("gpr", MavenPublication::class) {
            from(components["java"])
        }
    }
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
        }
    }
}
