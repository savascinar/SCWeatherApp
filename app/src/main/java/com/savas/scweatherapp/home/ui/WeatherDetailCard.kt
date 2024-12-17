package com.savas.scweatherapp.home.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.savas.scweatherapp.R
import com.savas.scweatherapp.ui.common.TemperatureUI
import com.savas.scweatherapp.util.FormatHelper

@Composable
fun WeatherDetailCard(humidity: Int, uv: Double, tempFeelLike: Double) {

    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.card_elevation)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_shape_size)),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.weather_light_gray)),
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {

            WeatherDetailCardColumn(
                Modifier.weight(1f),
                stringResource(id = R.string.humidity),
                "$humidity%"
            )

            WeatherDetailCardColumn(
                Modifier.weight(1f),
                stringResource(id = R.string.uv),
                FormatHelper.formatUV(uv)
            )

            WeatherDetailCardColumn(
                Modifier.weight(1f),
                stringResource(id = R.string.feels_like),
                ","
            ) {
                TemperatureUI(
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.weather_detail_card_top_small_margin),
                            start = dimensionResource(id = R.dimen.weather_detail_card_side_margin),
                            end = dimensionResource(id = R.dimen.weather_detail_card_side_margin),
                            bottom = dimensionResource(id = R.dimen.weather_detail_card_bottom_large_margin)
                        ),
                    temp = tempFeelLike,
                    fontSize = dimensionResource(id = R.dimen.weather_detail_card_large_text).value.sp,
                    fontColor = colorResource(id = R.color.weather_detail_dark_text)
                )
            }

        }

    }
}