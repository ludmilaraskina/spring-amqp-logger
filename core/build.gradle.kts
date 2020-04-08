plugins {
    `maven-publish`
}

tasks {
    "jar"(Jar::class) {
        archiveFileName.set("spring-amqp-logger.jar")
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(kotlin("reflect"))
    compileOnly("org.springframework.boot:spring-boot-starter-amqp")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.github.microutils:kotlin-logging:1.7.8")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.3")
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            artifactId = "spring-amqp-logger"
            from(components["java"])
        }
    }
}
