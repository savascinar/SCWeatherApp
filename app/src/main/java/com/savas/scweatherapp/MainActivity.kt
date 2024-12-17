package com.savas.scweatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.savas.scweatherapp.home.ui.HomeScreen
import com.savas.scweatherapp.search.ui.SearchScreen
import com.savas.scweatherapp.ui.theme.SCWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SCWeatherAppTheme {
                val navController = rememberNavController()

                var query by remember { mutableStateOf("") }

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = {
                        SearchBar(
                            query = query,
                            onQueryChanged = { query = it },
                            onSearchTriggered = {
                                if(query.isNotEmpty()) {
                                    navController.navigate("search") {
                                        launchSingleTop = true
                                        popUpTo("search") { inclusive = true }
                                    }
                                }

                            }
                        )
                    }
                ) { _ ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.route
                    ) {

                        composable(Screens.Home.route) { _ ->
                            HomeScreen(Modifier.padding(top = dimensionResource(id = R.dimen.screen_top_padding)))
                        }
                        composable(Screens.Search.route) { _ ->
                            SearchScreen(
                                Modifier.padding(top = dimensionResource(id = R.dimen.screen_top_padding)),
                                query = query,
                                navigate = {
                                    navController.popBackStack()
                                }
                            )

                        }
                    }
                }

            }
        }
    }


}