package com.javichaques.core.ui.error

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javichaques.core.designsystem.R
import com.javichaques.core.designsystem.theme.RUColor
import com.javichaques.core.designsystem.theme.SfProText
import com.javichaques.core.designsystem.util.DevicePreviews
import com.javichaques.core.model.errors.RUError

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    error: RUError = RUError.UnexpectedCallError(),
    onRetry: (error: RUError) -> Unit = {},
) {
    @StringRes val title: Int

    @StringRes val body: Int

    @DrawableRes val image: Int

    when (error) {
        is RUError.IOError -> {
            title = R.string.error_network_title
            body = R.string.error_network_body
            image = R.drawable.img_error_network
        }

        else -> {
            title = R.string.error_generic_title
            body = R.string.error_generic_body
            image = R.drawable.img_error_generic
        }
    }

    ErrorContent(
        title = stringResource(id = title),
        body = stringResource(id = body),
        image = painterResource(id = image),
        modifier = modifier,
        error = error,
        onClick = onRetry,
    )
}

@Composable
internal fun ErrorContent(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    image: Painter,
    error: RUError,
    onClick: (error: RUError) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =
            modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp,
                ),
    ) {
        Image(
            painter = image,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.size(32.dp))

        Text(
            text = title,
            fontFamily = SfProText,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = RUColor.Primary.Black,
            textAlign = TextAlign.Center,
            modifier =
                Modifier
                    .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = body,
            fontFamily = SfProText,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = RUColor.Grey.Dim,
            textAlign = TextAlign.Center,
            modifier =
                Modifier
                    .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = { onClick(error) },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = RUColor.Primary.Black,
                    contentColor = RUColor.Primary.White,
                ),
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

internal class ErrorProvider : PreviewParameterProvider<RUError> {
    override val values =
        sequenceOf(
            RUError.HttpError(
                body = "",
                code = 401,
                message = "",
            ),
            RUError.IOError(Error()),
        )
}

@DevicePreviews
@Composable
internal fun ErrorScreenPreview(
    @PreviewParameter(ErrorProvider::class) error: RUError,
) {
    ErrorScreen(
        error = error,
    )
}
