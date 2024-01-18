package com.javichaques.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.javichaques.core.designsystem.R
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.util.DevicePreviews

@Composable
fun RULoader(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_loader))

    Box(modifier = modifier.fillMaxWidth()) {
        LottieAnimation(
            modifier =
                Modifier
                    .size(50.dp)
                    .align(Alignment.Center),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}

@DevicePreviews
@Composable
internal fun RULoaderPreview() {
    RUTheme {
        RULoader()
    }
}
