package com.example.cleanarchcryptocurrency.ui.coin_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchcryptocurrency.data.common.Resource
import com.example.cleanarchcryptocurrency.domain.model.CoinDetailModel
import com.example.cleanarchcryptocurrency.domain.usecase.GetCoinDetailsUseCase
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
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

   companion object{
      const val COIN_ID = "coinId"
   }

   private val _coinState = mutableStateOf(CoinDetailsState())
   val coinState: androidx.compose.runtime.State<CoinDetailsState>
      get() = _coinState

   init {
      savedStateHandle.get<String>(COIN_ID)?.let {
         getDetailsCoins(it)
      }
   }

   private fun getDetailsCoins(coinId:String) {
      viewModelScope.launch {
         getCoinDetailsUseCase(coinId).onEach { result ->
            when (result) {
               is Resource.Error -> {
                  _coinState.value = CoinDetailsState(
                      error = result.error ?: "An unexpected error occurred"
                  )
               }

               Resource.Loading -> {
                  _coinState.value = CoinDetailsState(isLoading = true)
               }

               is Resource.Success -> {
                  _coinState.value = CoinDetailsState(
                      coins = result.data ,
                  )
               }
            }
         }.launchIn(viewModelScope)
      }
   }
}

data class CoinDetailsState(
    val isLoading: Boolean = false,
    val coins: CoinDetailModel? = null,
    val error: String = ""
)