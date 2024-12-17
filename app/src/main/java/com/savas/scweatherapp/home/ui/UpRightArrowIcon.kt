package com.savas.scweatherapp.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.savas.scweatherapp.R

@Composable
fun UpRightArrowIcon(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.arrow),
        contentDescription = stringResource(id = R.string.upright_arrow_icon),
        modifier = modifier.size(dimensionResource(id = R.dimen.weather_arrow_size))
    )
}