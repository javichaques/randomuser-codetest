package com.javichaques.core.designsystem.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.javichaques.core.designsystem.theme.RUColor
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.util.DevicePreviews

@Composable
fun RULoader(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        trackColor = RUColor.Primary.Black,
    )
}

@DevicePreviews
@Composable
internal fun RULoaderPreview() {
    RUTheme {
        RULoader()
    }
}
