package com.savas.scweatherapp.util

import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.savas.scweatherapp.R

object ModifierHelper {

    @Composable
    fun Modifier.screenPadding(paddingValues: PaddingValues): Modifier {
        val systemBarsPadding = getSystemBarsTopPadding()
        Log.i("eklfmelkf","systemBarsPadding " + systemBarsPadding.toString())
        val adjustedTopPadding =
            paddingValues.calculateTopPadding() - dimensionResource(id = R.dimen.search_bar_padding) - systemBarsPadding
        return this.padding(top = adjustedTopPadding)
    }

    @Composable
    fun Modifier.outerPadding(): Modifier {
        return this
            .padding(dimensionResource(id = R.dimen.search_bar_padding))
            .padding(WindowInsets.systemBars.asPaddingValues())
    }

    // Function to handle system bar padding for all API levels
    @Composable
    private fun getSystemBarsTopPadding(): Dp {
        val context = LocalContext.current
        val density = LocalDensity.current.density

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For Android 11 (API 30) and above
            WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
        } else {
            // For older versions
            val activityRootView = (context as? ComponentActivity)?.window?.decorView?.rootView
            val topInset = activityRootView?.let { rootView ->
                val insets = ViewCompat.getRootWindowInsets(rootView)
                insets?.getInsets(WindowInsetsCompat.Type.systemBars())?.top ?: 0
            } ?: 0
            (topInset / density).dp // Convert pixel value to dp
        }
    }
}