package com.example.starwarsplanets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.starwarsplanets.navigation.NavGraph
import com.example.starwarsplanets.shared.ui.theme.StarWarsPlanetsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsPlanetsTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}