package com.javichaques.core.ui.maps

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.javichaques.core.ui.utils.bitmapDescriptorFromVector

@Composable
fun MapMarker(
    context: Context,
    state: MarkerState,
    @DrawableRes iconResourceId: Int,
) {
    val icon =
        bitmapDescriptorFromVector(
            context,
            iconResourceId,
        )
    Marker(
        state = state,
        icon = icon,
    )
}
