package com.savas.scweatherapp.home.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.savas.scweatherapp.R
import com.savas.scweatherapp.ui.common.TemperatureUI

@Composable
fun WeatherInfo(modifier: Modifier, name: String, iconUrl: String, temp: Double) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth(0.4f),
        model = iconUrl,
        contentDescription = null,
    )

    Row(
        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.weather_info_bottom_margin)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            name,
            modifier = Modifier,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontSize = dimensionResource(id = R.dimen.weather_info_text_size).value.sp,
            fontWeight = FontWeight.Bold
        )

        UpRightArrowIcon(Modifier.padding(start = dimensionResource(id = R.dimen.weather_arrow_side_margin)))
    }

    TemperatureUI(
        Modifier.padding(bottom = dimensionResource(id = R.dimen.weather_info_temp_bottom_margin)),
        temp,
        dimensionResource(id = R.dimen.weather_info_temp_text_size).value.sp
    )
}