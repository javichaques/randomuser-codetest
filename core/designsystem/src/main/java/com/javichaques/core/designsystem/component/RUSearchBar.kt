@file:OptIn(ExperimentalMaterial3Api::class)

package com.javichaques.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javichaques.core.designsystem.R
import com.javichaques.core.designsystem.theme.RUColor
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.theme.SfProText
import com.javichaques.core.designsystem.util.DevicePreviews

@Composable
fun RUSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    placeholder: String? = null,
    onQueryChange: (String) -> Unit = {},
    onClearButtonClick: () -> Unit = {},
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        textStyle =
            TextStyle(
                fontFamily = SfProText,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            ),
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = RUColor.Grey.Light,
                unfocusedContainerColor = RUColor.Grey.Light,
                focusedTextColor = RUColor.Primary.Black,
                unfocusedTextColor = RUColor.Primary.Black,
                focusedLeadingIconColor = RUColor.Grey.Medium,
                unfocusedLeadingIconColor = RUColor.Grey.Medium,
                focusedTrailingIconColor = RUColor.Grey.Medium,
                unfocusedTrailingIconColor = RUColor.Grey.Medium,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = RUColor.Primary.Black,
            ),
        placeholder =
            if (placeholder != null) {
                {
                    Text(
                        text = placeholder,
                        fontFamily = SfProText,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = RUColor.Grey.Medium,
                    )
                }
            } else {
                null
            },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        trailingIcon =
            if (query.isNotBlank()) {
                {
                    IconButton(
                        onClick = onClearButtonClick,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                        )
                    }
                }
            } else {
                null
            },
    )
}

@DevicePreviews
@Composable
internal fun RUSearchBarPreview() {
    RUTheme {
        RUSearchBar(
            query = "",
            placeholder = stringResource(id = R.string.mail),
            modifier = Modifier.padding(16.dp),
        )
    }
}
