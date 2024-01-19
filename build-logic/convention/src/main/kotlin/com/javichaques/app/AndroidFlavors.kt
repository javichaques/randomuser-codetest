package com.javichaques.app

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project

@Suppress("EnumEntryName")
enum class FlavorDimension {
    environment,
}

@Suppress("EnumEntryName")
enum class AndroidFlavor(val dimension: FlavorDimension) {
    development(
        FlavorDimension.environment,
    ),

    production(
        FlavorDimension.environment,
    ),
}

fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: AndroidFlavor) -> Unit = {},
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.environment.name
        productFlavors {
            AndroidFlavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    flavorConfigurationBlock(this, it)
                }
            }
        }
    }
}
