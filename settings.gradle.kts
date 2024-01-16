@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Random User"

include(":app")
include(":core:common")
include(":core:data")
include(":core:designsystem")
include(":core:domain")
include(":core:network")
include(":core:model")
include(":core:testing")
include(":core:ui")
