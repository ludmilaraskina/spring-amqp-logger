dependencies {
    implementation("com.github.ludmilaraskina:spring-amqp-logger:$version")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
