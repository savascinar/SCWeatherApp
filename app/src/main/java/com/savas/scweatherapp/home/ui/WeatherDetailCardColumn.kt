package com.savas.scweatherapp.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.savas.scweatherapp.R

@Composable
fun WeatherDetailCardColumn(
    modifier: Modifier,
    label: String,
    value: String,
    customValueContainer: (@Composable () -> Unit)? = null
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.weather_detail_card_top_large_margin),
                    start = dimensionResource(id = R.dimen.weather_detail_card_side_margin),
                    end = dimensionResource(id = R.dimen.weather_detail_card_side_margin),
                    bottom = dimensionResource(id = R.dimen.weather_detail_card_bottom_small_margin)
                )
                .align(Alignment.CenterHorizontally),
            text = label,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(id = R.dimen.weather_detail_card_small_text).value.sp,
            color = colorResource(id = R.color.weather_detail_light_text)
        )

        if (customValueContainer == null) {
            Text(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.weather_detail_card_top_small_margin),
                        start = dimensionResource(id = R.dimen.weather_detail_card_side_margin),
                        end = dimensionResource(id = R.dimen.weather_detail_card_side_margin),
                        bottom = dimensionResource(id = R.dimen.weather_detail_card_bottom_large_margin)
                    )
                    .align(Alignment.CenterHorizontally),
                text = value,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.weather_detail_card_large_text).value.sp,
                color = colorResource(id = R.color.weather_detail_dark_text)
            )
        } else {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                customValueContainer()
            }
        }
    }
}