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
    namespace = "com.javichaques.core.ui"
    compileSdk = BuildConstants.Sdk.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildConstants.Sdk.MIN_SDK_VERSION
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
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    api(project(":core:designsystem"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core)
    implementation(libs.coil.core)
    implementation(libs.coil.compose)

    api(platform(libs.compose.bom))
    api(libs.compose.material3)
    debugApi(libs.compose.ui.tooling.core)
    api(libs.compose.ui.tooling.preview)
    api(libs.compose.runtime.livedata)
    api(libs.androidx.paging.compose)
    api(libs.androidx.lifecycle.runtimeCompose)

    api(libs.accompanist.swiperefresh)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.timber)

    api(libs.destinations.core)
    ksp(libs.destinations.ksp)
}
