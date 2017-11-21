import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.dsl.ExperimentalExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.1.51"
}

buildscript {
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.1")
    }
}

apply {
    plugin("org.junit.platform.gradle.plugin")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

repositories {
    jcenter()

    maven("https://dl.bintray.com/kotlin/kotlinx")
    maven("https://dl.bintray.com/kotlin/ktor")
}


dependencies {
    compile(kotlin("stdlib-jre8"))

    compile("org.processing:core:3.3.5")
    compile("io.ktor:ktor-server-netty:0.9.0")

    compile("ch.qos.logback:logback-classic:1.2.1")

    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.0")

    compile("com.corundumstudio.socketio:netty-socketio:1.7.13")
}
