plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.junit)
    alias(libs.plugins.hilt)
    id("com.javichaques.android.library.flavors")
}

android {
    namespace = "com.javichaques.core.network"
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
        buildConfig = true
    }

    productFlavors {
        val development by getting {
            buildConfigField("String", "API_URL", "\"${BuildConstants.Development.Api.URL}\"")
        }

        val production by getting {
            buildConfigField("String", "API_URL", "\"${BuildConstants.Production.Api.URL}\"")
        }
    }
}

dependencies {
//    implementation(project(":core:common"))
//    implementation(project(":core:model"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(platform(libs.coroutines.bom))
    implementation(libs.coroutines.android)
    implementation(libs.serialization)

    implementation(platform(libs.okHttp.bom))
    implementation(libs.okHttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.serialization)

    implementation(platform(libs.arrow.bom))
    implementation(libs.arrow.core)
    implementation(libs.arrow.retrofit)

    implementation(libs.timber)
}
