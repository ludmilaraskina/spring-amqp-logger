import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.60"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("com.github.ben-manes.versions") version "0.27.0"
    id("io.gitlab.arturbosch.detekt") version "1.1.1"
}

ext["kotlin.version"] = "1.3.60"

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.2.1.RELEASE")
    }
}

group = "com.github.ludmilaraskina"

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    compileOnly("org.springframework.boot:spring-boot-starter-amqp")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.github.microutils:kotlin-logging:1.7.8")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.3")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.1.1")
}

repositories {
    mavenLocal()
    mavenCentral()
}

detekt {
    ignoreFailures = true
}
