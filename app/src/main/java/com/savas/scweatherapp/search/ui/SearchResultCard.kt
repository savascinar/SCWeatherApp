package com.savas.scweatherapp.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.savas.scweatherapp.R
import com.savas.scweatherapp.ui.common.TemperatureUI

@Composable
fun SearchResultCard(name: String, temp: Double, iconUrl: String, onClick: () -> Unit) {

    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.card_elevation)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_shape_size)),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.weather_light_gray)),
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.search_result_card_padding),
                end = dimensionResource(id = R.dimen.search_result_card_padding),
                bottom = dimensionResource(id = R.dimen.search_result_card_padding)
            )
            .fillMaxWidth()
            .clickable { onClick() }
    ) {


        Box(modifier = Modifier.fillMaxWidth()) {


            Column(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.search_location_container_start_padding),
                        top = dimensionResource(id = R.dimen.search_location_container_top_padding)
                    )
                    .align(Alignment.CenterStart),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.search_location_text_start_padding),
                        end = dimensionResource(id = R.dimen.search_location_text_end_padding),
                        top = dimensionResource(id = R.dimen.search_location_text_top_padding),
                        bottom = dimensionResource(id = R.dimen.search_location_text_bottom_padding)
                    ),
                    text = name,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.search_location_text_size).value.sp
                )

                TemperatureUI(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.search_temp_padding),
                        bottom = dimensionResource(id = R.dimen.search_temp_padding)
                    ),
                    temp = temp,
                    fontSize = dimensionResource(id = R.dimen.search_temp_text_size).value.sp
                )
            }


            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = dimensionResource(id = R.dimen.search_location_icon_padding))
                    .size(dimensionResource(id = R.dimen.search_location_icon_size)),
                model = iconUrl,
                contentDescription = null,
            )

        }
    }


}