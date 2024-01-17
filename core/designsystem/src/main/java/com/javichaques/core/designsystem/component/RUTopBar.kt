@file:OptIn(ExperimentalMaterial3Api::class)

package com.javichaques.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import com.javichaques.core.designsystem.R
import com.javichaques.core.designsystem.theme.Oswald
import com.javichaques.core.designsystem.theme.RUColor
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.util.DevicePreviews

enum class NavigationType(
    @DrawableRes val icon: Int? = null,
) {
    None,
    Back(R.drawable.ic_back),
}

enum class BackgroundType {
    White,
    Transparent,
}

@Composable
fun RUTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {},
    navigationType: NavigationType = NavigationType.Back,
    backgroundType: BackgroundType = BackgroundType.White,
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors =
        when (backgroundType) {
            BackgroundType.White ->
                TopAppBarDefaults.topAppBarColors(
                    containerColor = RUColor.Primary.White,
                    titleContentColor = RUColor.Primary.Black,
                    navigationIconContentColor = RUColor.Primary.Black,
                    actionIconContentColor = RUColor.Primary.Black,
                )

            BackgroundType.Transparent ->
                TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = RUColor.Primary.White,
                    navigationIconContentColor = RUColor.Primary.White,
                    actionIconContentColor = RUColor.Primary.White,
                )
        },
) {
    TopAppBar(
        title = {
            Text(
                text = title.uppercase(),
                fontFamily = Oswald,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
        },
        colors = colors,
        modifier = modifier,
        navigationIcon = {
            navigationType.icon?.let {
                IconButton(
                    onClick = onNavigationClick,
                    colors =
                        IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent,
                        ),
                ) {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null,
                    )
                }
            }
        },
        actions = actions,
    )
}

internal class TopBarProvider : PreviewParameterProvider<BackgroundType> {
    override val values = BackgroundType.entries.asSequence()
}

@DevicePreviews
@Composable
fun TrexxTopAppBarPreview(
    @PreviewParameter(TopBarProvider::class) data: BackgroundType,
) {
    RUTheme {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(RUColor.Grey.Dim),
        ) {
            RUTopAppBar(
                title = stringResource(id = R.string.contacts),
                backgroundType = data,
            )
        }
    }
}
