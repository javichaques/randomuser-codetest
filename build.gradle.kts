import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    alias(libs.plugins.updates)

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.junit) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.hilt) apply false
}

allprojects {
    // Spotless
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)

    configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("${layout.buildDirectory}/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint().setEditorConfigPath("$rootDir/.editorconfig")
        }
        kotlinGradle {
            target("**/*.kts")
            targetExclude("${layout.buildDirectory}/**/*.kts")

            ktlint().setEditorConfigPath("$rootDir/.editorconfig")
        }
        format("misc") {
            target("**/*.md", "**/.gitignore")
        }
        format("xml") {
            target("**/*.xml")
        }
    }
}

// Gradle Versions
fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}
