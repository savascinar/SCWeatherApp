package com.savas.scweatherapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.savas.scweatherapp.R
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun TemperatureUI(
    modifier: Modifier, temp: Double, fontSize: TextUnit, fontColor: Color = colorResource(
        id = R.color.black
    )
) {

    val roundedTemp = temp.roundToInt()

    Row(modifier = modifier) {

        Text(
            text = String.format(Locale.US, "%d", roundedTemp),
            modifier = Modifier,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = fontColor
        )

        val symbolSize = maxOf(
            with(LocalDensity.current) { (fontSize.value / 8.0).dp },
            dimensionResource(id = R.dimen.minimum_celsius_icons_size)
        )

        Image(
            painter = painterResource(id = R.drawable.celsius),
            contentDescription = stringResource(id = R.string.celsius_symbol),
            modifier = Modifier.size(symbolSize),
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(fontColor)
        )

    }
}