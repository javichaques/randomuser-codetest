package com.javichaques.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val lightColorScheme =
    lightColorScheme(
        primary = RUColor.Primary.White,
        secondary = RUColor.Primary.Black,
        tertiary = RUColor.Grey.Medium,
    )

private val darkColorScheme =
    darkColorScheme(
        primary = RUColor.Primary.Black,
        secondary = RUColor.Primary.White,
        tertiary = RUColor.Grey.Light,
    )

@Composable
fun RUTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            darkTheme -> darkColorScheme
            else -> lightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
