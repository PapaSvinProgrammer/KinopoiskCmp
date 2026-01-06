package com.mordva.ui.widget.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.theme.Gold
import com.mordva.ui.theme.Green
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.Typography
import com.mordva.ui.util.ConvertData
import org.jetbrains.compose.resources.painterResource

@Composable
fun RatingChip(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Typography.bodySmall.fontSize,
    rating: Float,
    top: Int? = null
) {
    if (top != null) {
        EliteBox(
            modifier = modifier,
            fontSize = fontSize,
            rating = rating
        )
        return
    }

    if (rating.toInt() >= 7) {
        DefaultBox(
            modifier = modifier,
            rating = rating,
            fontSize = fontSize,
            color = Green
        )
    }
    else if (rating.toInt() >= 5) {
        DefaultBox(
            modifier = modifier,
            rating = rating,
            fontSize = fontSize,
            color = Color.Gray
        )
    }
    else if (rating.toInt() > 0) {
        DefaultBox(
            modifier = modifier.background(Color.Red),
            rating = rating,
            color = Color.Red,
            fontSize = fontSize
        )
    }
}

@Composable
private fun EliteBox(
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    rating: Float = 9.0f
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(DsSpacer.M10))
            .background(Gold)
    ) {
        Icon(
            painter = painterResource(Icons.Branch),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(DsSpacer.M15)
                .align(Alignment.CenterStart)
                .padding(start = DsSpacer.M5)
                .scale(scaleX = -1f, scaleY = 1f)
        )

        Text(
            text = ConvertData.convertRatingKP(rating),
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = Color.Black,
            lineHeight = DsTextSize.M10,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = DsSpacer.M16, vertical = DsSpacer.M3)
        )

        Icon(
            painter = painterResource(Icons.Branch),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(DsSpacer.M15)
                .align(Alignment.CenterEnd)
                .padding(end = DsSpacer.M5)
        )
    }
}

@Composable
private fun DefaultBox(
    modifier: Modifier,
    fontSize: TextUnit,
    rating: Float,
    color: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(DsSpacer.M10))
            .background(color)
    ) {
        Text(
            text = ConvertData.convertRatingKP(rating),
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = Color.White,
            lineHeight = DsTextSize.M10,
            modifier = Modifier.padding(
                horizontal = DsSpacer.M8,
                vertical = DsSpacer.M3
            )
        )
    }
}