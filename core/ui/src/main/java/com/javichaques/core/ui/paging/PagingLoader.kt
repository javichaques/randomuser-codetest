package com.javichaques.core.ui.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javichaques.core.designsystem.component.RULoader
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.util.DevicePreviews

@Composable
fun PagingLoader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(34.dp),
        contentAlignment = Alignment.Center,
    ) {
        RULoader()
    }
}

@DevicePreviews
@Composable
fun PagingLoaderPreview() {
    RUTheme {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                PagingLoader(
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
