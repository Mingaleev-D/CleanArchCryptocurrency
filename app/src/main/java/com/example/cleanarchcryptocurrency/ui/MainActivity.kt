package com.example.cleanarchcryptocurrency.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchcryptocurrency.ui.coin_details.CoinDetailsScreen
import com.example.cleanarchcryptocurrency.ui.coin_list.CoinListScreen
import com.example.cleanarchcryptocurrency.ui.theme.CleanArchCryptocurrencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         CleanArchCryptocurrencyTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {

               val navController = rememberNavController()
               NavHost(navController = navController, startDestination = "coin_list_screen") {
                  composable("coin_list_screen") {
                     CoinListScreen(navController = navController)
                  }
                  composable("coin_details_screen/{coinId}") {
                     CoinDetailsScreen()
                  }
               }

            }
         }
      }
   }
}

sealed class Screen(val route: String) {
   object CoinListScreen : Screen("coin_list_screen")
   object CoinDetailsScreen : Screen("coin_details_screen")
}

