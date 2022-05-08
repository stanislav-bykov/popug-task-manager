import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.6.21"

    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version kotlinVersion
    kotlin("jvm") version kotlinVersion
}

group = "org.popug.tracker"
version = "0.0.1-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {

    apply{
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.jvm")
    }

    dependencies {
        // security
        implementation("org.keycloak:keycloak-spring-boot-starter:18.0.0")
        implementation("org.springframework.boot:spring-boot-starter-security")

        //kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
//        testImplementation("org.testcontainers:junit-jupiter")
//        testImplementation("org.testcontainers:r2dbc")
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
