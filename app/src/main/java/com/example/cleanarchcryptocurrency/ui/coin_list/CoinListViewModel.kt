package com.example.cleanarchcryptocurrency.ui.coin_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchcryptocurrency.data.common.Resource
import com.example.cleanarchcryptocurrency.domain.model.CoinListModel
import com.example.cleanarchcryptocurrency.domain.usecase.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 17.10.2023
 */

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

   private val _coinState = mutableStateOf(CoinListState())
   val coinState: androidx.compose.runtime.State<CoinListState>
      get() = _coinState

   init {
      getCoins()
   }

   private fun getCoins() {
      viewModelScope.launch {
         getCoinUseCase().onEach { result ->
            when (result) {
               is Resource.Error -> {
                  _coinState.value = CoinListState(
                      error = result.error ?: "An unexpected error occurred"
                  )
               }

               Resource.Loading -> {
                  _coinState.value = CoinListState(isLoading = true)
               }

               is Resource.Success -> {
                  _coinState.value = CoinListState(
                      coins = result.data ?: emptyList(),
                  )
               }
            }
         }.launchIn(viewModelScope)
      }
   }
}

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinListModel> = emptyList(),
    val error: String = ""
)