package com.example.cleanarchcryptocurrency.ui.coin_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cleanarchcryptocurrency.domain.model.CoinListModel

/**
 * @author : Mingaleev D
 * @data : 17.10.2023
 */

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {

   val state = viewModel.coinState.value

   Box(
       modifier = Modifier.fillMaxSize()
   ) {

      LazyColumn(
          modifier = Modifier.fillMaxSize()
      ) {
         items(state.coins) { coin ->

            CoinItem(
                coin = coin,
                onItemClick = {
                   navController.navigate("coin_details_screen/${coin.id}")
                }
            )
         }

      }

      if (state.error.isNotBlank()) {
         Text(
             text = state.error,
             color = Color.Red,
             textAlign = TextAlign.Center,
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(horizontal = 20.dp)
                 .align(Center)
         )
      }

      if(state.isLoading){
         CircularProgressIndicator(modifier = Modifier.align(Center))
      }

   }
}

@Composable
fun CoinItem(
    coin: CoinListModel,
    onItemClick: (CoinListModel) -> Unit
) {

   Row(
       modifier = Modifier
           .fillMaxWidth()
           .clickable { onItemClick(coin) }
           .padding(20.dp),
       horizontalArrangement = Arrangement.SpaceBetween
   ) {

      Text(
          text = "${coin.rank} - ${coin.name} (${coin.symbol})",
          style = MaterialTheme.typography.body1,
          overflow = TextOverflow.Ellipsis,
          color = MaterialTheme.colors.onPrimary
      )

      Text(
          text = if (coin.isActive) "active" else "inactive",
          color = if (coin.isActive) Color.Green else Color.Red,
          fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
          textAlign = TextAlign.End,
          style = MaterialTheme.typography.body2,
          modifier = Modifier.align(CenterVertically)
      )

   }

}