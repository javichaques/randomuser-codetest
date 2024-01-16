plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.junit)
    id("com.javichaques.android.library.flavors")
}

android {
    namespace = "com.javichaques.core.designsystem"
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
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.coil.compose)

    api(platform(libs.compose.bom))
    api(libs.compose.material3)
    debugApi(libs.compose.ui.tooling.core)
    api(libs.compose.ui.tooling.preview)

    implementation(libs.timber)
}
