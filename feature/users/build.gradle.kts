plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.junit)
    alias(libs.plugins.hilt)
    id("com.javichaques.android.library.flavors")
}

android {
    namespace = "com.javichaques.feature.users"
    compileSdk = BuildConstants.Sdk.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildConstants.Sdk.MIN_SDK_VERSION

        ksp {
            arg("compose-destinations.mode", "navgraphs")
            arg("compose-destinations.moduleName", "users")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(libs.coil.core)
    implementation(libs.coil.compose)

    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.destinations.core)
    ksp(libs.destinations.ksp)

    implementation(platform(libs.coroutines.bom))
    implementation(libs.hilt.navigation.compose)
    implementation(libs.coroutines.android)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.timber)

    implementation(platform(libs.arrow.bom))
    implementation(libs.arrow.core)
    implementation(libs.arrow.retrofit)
}
