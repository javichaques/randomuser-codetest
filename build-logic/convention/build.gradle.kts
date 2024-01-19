plugins {
    `kotlin-dsl`
}

group = "com.javichaques.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidFlavors") {
            id = "com.javichaques.android.library.flavors"
            implementationClass = "AndroidLibraryFlavorsConventionPlugin"
        }
    }
}
