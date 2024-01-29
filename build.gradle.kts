// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    kotlin("kapt") version "1.9.21"
    kotlin("jvm") version "1.9.21" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val navVersion = "2.7.6"
        classpath(kotlin("gradle-plugin", version = "1.9.21"))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}