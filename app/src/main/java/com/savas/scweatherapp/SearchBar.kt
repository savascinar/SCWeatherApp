package com.savas.scweatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.savas.scweatherapp.util.ModifierHelper.outerPadding

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .outerPadding()
            .background(
                colorResource(id = R.color.weather_light_gray),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.search_bar_bg_shape_size))
            )
            .padding(
                horizontal = dimensionResource(id = R.dimen.search_bar_inner_horizontal_padding),
                vertical = dimensionResource(id = R.dimen.search_bar_inner_vertical_padding)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = query,
            onValueChange = { onQueryChanged(it) },
            modifier = Modifier
                .weight(1f)
                .background(colorResource(id = R.color.weather_light_gray)),
            placeholder = { Text(stringResource(id = R.string.search_location)) },
            singleLine = true,
            colors = TextFieldDefaults.colors().copy(
                unfocusedContainerColor = colorResource(id = R.color.weather_light_gray),
                focusedContainerColor = colorResource(id = R.color.weather_light_gray),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedPlaceholderColor = colorResource(id = R.color.weather_detail_light_text),
                focusedPlaceholderColor = colorResource(id = R.color.weather_detail_light_text)
            )
        )

        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = stringResource(id = R.string.search_symbol),
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.search_icon_padding))
                .clickable {
                    onSearchTriggered()
                }
        )
    }

}

