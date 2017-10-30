import org.jetbrains.kotlin.gradle.dsl.Coroutines
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
    }
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jre8"))

    compile("org.openpnp:opencv:3.2.0-1")

    val lwjglVersion = "3.1.3"
    val lwjglNatives = "natives-macos"

    compile("org.lwjgl:lwjgl:$lwjglVersion")
    compile("org.lwjgl:lwjgl-glfw:$lwjglVersion")
    compile("org.lwjgl:lwjgl-jemalloc:$lwjglVersion")
    compile("org.lwjgl:lwjgl-openal:$lwjglVersion")
    compile("org.lwjgl:lwjgl-opengl:$lwjglVersion")
    compile("org.lwjgl:lwjgl-stb:$lwjglVersion")

    runtime("org.lwjgl:lwjgl:$lwjglVersion:$lwjglNatives")
    runtime("org.lwjgl:lwjgl-glfw:$lwjglVersion:$lwjglNatives")
    runtime("org.lwjgl:lwjgl-jemalloc:$lwjglVersion:$lwjglNatives")
    runtime("org.lwjgl:lwjgl-openal:$lwjglVersion:$lwjglNatives")
    runtime("org.lwjgl:lwjgl-opengl:$lwjglVersion:$lwjglNatives")
    runtime("org.lwjgl:lwjgl-stb:$lwjglVersion:$lwjglNatives")

    val junitVersion = "5.0.1"
    testCompile("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
