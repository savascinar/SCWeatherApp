package com.savas.scweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.savas.scweatherapp.home.ui.HomeScreen
import com.savas.scweatherapp.search.ui.SearchScreen
import com.savas.scweatherapp.ui.theme.SCWeatherAppTheme
import com.savas.scweatherapp.util.ModifierHelper.screenPadding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                                navController.navigate("search") {
                                    launchSingleTop = true
                                    popUpTo("search") { inclusive = true }
                                }

                            }
                        )
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.route
                    ) {

                        composable(Screens.Home.route) { _ ->

                            HomeScreen(
                                Modifier.screenPadding(padding))
                        }
                        composable(Screens.Search.route) { _ ->
                            SearchScreen(
                                Modifier.screenPadding(padding),
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