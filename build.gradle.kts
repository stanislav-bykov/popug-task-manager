import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN

plugins {
    val kotlinVersion = "1.6.21"

    id("org.springframework.boot") version "2.5.6" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("org.flywaydb.flyway") version "8.5.10" apply false
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
}

tasks.jar { enabled = false }
tasks.build { enabled = false }

val schemaResources = "schema-resources"

sourceSets {
    create(schemaResources) {
        resources {
            // Universal logback config for all sub-modules
            srcDir("${project(":messaging-common").projectDir}/src/main/resources/schema")
        }
    }

    if (!project.name.endsWith("common")) {
        sourceSets {
            main {
                resources {
                    srcDirs += getByName(schemaResources).resources
                }
            }
        }
    }
}

allprojects {
    group = "org.popug.tracker"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        maven(url = "https://packages.confluent.io/maven/")
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    val project = this

    apply {
        if (!project.name.endsWith("common")) {
            plugin("org.flywaydb.flyway")
        }
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    dependencies {
        // messaging
        implementation("org.springframework.kafka:spring-kafka")
        implementation("io.confluent:kafka-json-schema-serializer:7.1.0")
        implementation("com.github.everit-org.json-schema:org.everit.json.schema:1.14.1")

        // security
        implementation("org.keycloak:keycloak-spring-boot-starter:18.0.0")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.keycloak:keycloak-admin-client:18.0.0")

        // kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // logging
        implementation("io.github.microutils:kotlin-logging:1.12.5")

        // flyway
        if (!project.name.endsWith("common")) {
            implementation("org.flywaydb:flyway-core:8.5.10")
        }

        // jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    val javaVersion = JavaVersion.VERSION_17

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
            jvmTarget = "$javaVersion"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    configure<KtlintExtension> {
        ignoreFailures.set(false)
        disabledRules.set(setOf("no-wildcard-imports"))
        reporters {
            reporter(PLAIN)
            reporter(CHECKSTYLE)
        }
    }
}
