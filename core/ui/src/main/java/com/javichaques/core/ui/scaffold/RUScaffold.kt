package com.javichaques.core.ui.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.javichaques.core.designsystem.component.RULoader
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.util.DevicePreviews
import com.javichaques.core.model.errors.RUError
import com.javichaques.core.ui.error.ErrorScreen

@Composable
fun RUScaffold(
    modifier: Modifier = Modifier,
    error: RUError? = null,
    isLoading: Boolean = false,
    onRetry: (error: RUError) -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when {
            error != null -> {
                ErrorScreen(
                    error = error,
                    onRetry = { onRetry(error) },
                )
            }

            isLoading -> {
                RULoader()
            }

            else -> {
                content()
            }
        }
    }
}

internal data class ScaffoldData(
    val error: RUError? = null,
    val isLoading: Boolean = false,
)

internal class ScaffoldProvider : PreviewParameterProvider<ScaffoldData> {
    override val values =
        sequenceOf(
            ScaffoldData(error = RUError.UnexpectedCallError()),
            ScaffoldData(isLoading = true),
            ScaffoldData(),
        )
}

@DevicePreviews
@Composable
internal fun RUScaffoldPreview(
    @PreviewParameter(ScaffoldProvider::class) data: ScaffoldData,
) {
    RUTheme {
        RUScaffold(
            error = data.error,
            isLoading = data.isLoading,
        ) {
            Text(text = "Working")
        }
    }
}
