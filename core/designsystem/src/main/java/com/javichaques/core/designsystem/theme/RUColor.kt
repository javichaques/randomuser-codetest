package com.javichaques.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object RUColor {
    object Primary {
        val Black = Color(0xFF000000)
        val White = Color(0xFFFFFFFF)
    }

    object Grey {
        val Light = Color(0xFFE7E7E7)
        val Medium = Color(0xFFC4C4C4)
        val Dim = Color(0xFF707070)
    }
}

@Composable
fun ColorBox(
    color: Color,
    name: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Color.White),
    ) {
        OutlinedCard(
            content = {},
            shape = RoundedCornerShape(8.dp),
            colors =
                CardDefaults.outlinedCardColors(
                    containerColor = color,
                ),
            modifier =
                Modifier
                    .size(48.dp)
                    .padding(8.dp),
        )
        Text(text = name)
    }
}

@Preview
@Composable
internal fun PrimaryColorsPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ColorBox(color = RUColor.Primary.Black, "Black")
        ColorBox(color = RUColor.Primary.White, "White")
    }
}

@Preview
@Composable
internal fun TextColorsPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ColorBox(color = RUColor.Grey.Light, "Light")
        ColorBox(color = RUColor.Grey.Medium, "Medium")
        ColorBox(color = RUColor.Grey.Dim, "Dim")
    }
}
