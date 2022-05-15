import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.6.21"

    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    kotlin("plugin.spring") version kotlinVersion
    kotlin("jvm") version kotlinVersion
}

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }

group = "org.popug.tracker"
version = "0.0.1-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {

    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.jvm")
    }

    dependencies {
        // security
        implementation("org.keycloak:keycloak-spring-boot-starter:18.0.0")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.keycloak:keycloak-admin-client:18.0.0")

        // kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // logging
        implementation("io.github.microutils:kotlin-logging:1.12.5")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    val javaVersion = JavaVersion.VERSION_17

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "$javaVersion"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
