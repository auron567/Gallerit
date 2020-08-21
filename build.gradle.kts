import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id(BuildPlugins.androidApplication) version BuildVersions.agp apply false
    id(BuildPlugins.kotlinAndroid) version BuildVersions.kotlin apply false
    id(BuildPlugins.safeArgs) version BuildVersions.safeArgs apply false
    id(BuildPlugins.detekt) version BuildVersions.detekt
    id(BuildPlugins.ktlintGradle) version BuildVersions.ktlintGradle
    id(BuildPlugins.versionsPlugin) version BuildVersions.versionsPlugin
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
    }
}

subprojects {
    apply {
        plugin(BuildPlugins.ktlintGradle)
        plugin(BuildPlugins.detekt)
    }
    ktlint {
        version.set(Versions.ktlint)
        verbose.set(true)
        android.set(true)
    }
    detekt {
        reports {
            html {
                enabled = true
                destination = file("build/reports/detekt.html")
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String) = "^[0-9,.v-]+(-r)?$".toRegex().matches(version).not()
