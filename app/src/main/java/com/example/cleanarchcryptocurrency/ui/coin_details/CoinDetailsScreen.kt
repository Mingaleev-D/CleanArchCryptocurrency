package com.example.cleanarchcryptocurrency.ui.coin_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cleanarchcryptocurrency.ui.coin_details.components.CoinTag
import com.example.cleanarchcryptocurrency.ui.coin_details.components.TeamListItem

/**
 * @author : Mingaleev D
 * @data : 17.10.2023
 */

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailsViewModel = hiltViewModel()
) {

   val state = viewModel.coinState.value

   Box(
       modifier = Modifier.fillMaxSize()
   ) {

      state.coins?.let { coin ->
         LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)) {

            item {
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {

                  Text(
                      text = "${coin.rank} - ${coin.name} (${coin.symbol})",
                      style = MaterialTheme.typography.h2,
                      overflow = TextOverflow.Ellipsis,
                      color = MaterialTheme.colors.onPrimary,
                      modifier = Modifier.weight(8f)
                  )

                  Text(
                      text = if (coin.isActive) "active" else "inactive",
                      color = if (coin.isActive) Color.Green else Color.Red,
                      fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                      textAlign = TextAlign.End,
                      style = MaterialTheme.typography.body2,
                      modifier = Modifier
                          .align(Alignment.CenterVertically)
                          .weight(2f)
                  )

               }

               Spacer(modifier = Modifier.height(15.dp))

               Text(text = coin.description, style = MaterialTheme.typography.body2)

               Spacer(modifier = Modifier.height(15.dp))

               Text(text = "Tags", style = MaterialTheme.typography.h3)

               Spacer(modifier = Modifier.height(15.dp))

               FlowRow(
                   modifier = Modifier
                       .padding(start = 10.dp, end = 10.dp)
                       .fillMaxWidth()
               ) {

                  coin.tags.forEach { tag ->
                     CoinTag(tag = tag)
                  }
               }

               Spacer(modifier = Modifier.height(15.dp))

               Text(text = "Team members", style = MaterialTheme.typography.h3)

               Spacer(modifier = Modifier.height(15.dp))
            }

            items(coin.team) { teamMember ->
               TeamListItem(
                   teamMember = teamMember,
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(10.dp)
               )

               Divider()
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
                    .align(Alignment.Center)
            )
         }

         if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
         }

      }

   }
}