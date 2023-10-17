package com.example.cleanarchcryptocurrency.data.remote

import com.example.cleanarchcryptocurrency.data.remote.dto.CoinListDto
import com.example.cleanarchcryptocurrency.data.remote.dto.detail.CoinDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author : Mingaleev D
 * @data : 17.10.2023
 */

interface APiService {

   companion object {

      const val BASE_URL = "https://api.coinpaprika.com/v1/"
   }

   @GET("coins")
   suspend fun fetchAllCoin(): List<CoinListDto>

   @GET("coins/{btc-bitcoin}")
   suspend fun fetchDetailsCoin(
       @Path("btc-bitcoin") coinId: String
   ): CoinDetailDto
}