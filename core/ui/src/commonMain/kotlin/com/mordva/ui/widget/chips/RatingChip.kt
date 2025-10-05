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
import androidx.compose.ui.unit.dp
import com.mordva.ui.theme.Gold
import com.mordva.ui.theme.Green
import com.mordva.ui.theme.Resources
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
            .clip(RoundedCornerShape(10.dp))
            .background(Gold)
    ) {
        Icon(
            painter = painterResource(Resources.Icons.Branch),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.CenterStart)
                .padding(start = 5.dp)
                .scale(scaleX = -1f, scaleY = 1f)
        )

        Text(
            text = ConvertData.convertRatingKP(rating),
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 15.dp)
        )

        Icon(
            painter = painterResource(Resources.Icons.Branch),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 5.dp)
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
            .clip(RoundedCornerShape(10.dp))
            .background(color)
    ) {
        Text(
            text = ConvertData.convertRatingKP(rating),
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}