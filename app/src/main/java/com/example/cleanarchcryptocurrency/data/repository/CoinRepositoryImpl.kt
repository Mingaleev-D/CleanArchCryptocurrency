package com.example.cleanarchcryptocurrency.data.repository

import com.example.cleanarchcryptocurrency.data.remote.APiService
import com.example.cleanarchcryptocurrency.data.remote.dto.detail.toDomain
import com.example.cleanarchcryptocurrency.data.remote.dto.toDomain
import com.example.cleanarchcryptocurrency.domain.model.CoinDetailModel
import com.example.cleanarchcryptocurrency.domain.model.CoinListModel
import com.example.cleanarchcryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 17.10.2023
 */

class CoinRepositoryImpl @Inject constructor(
    private val aPiService: APiService
) : CoinRepository {

   override suspend fun fetchAllCoin(): List<CoinListModel> {
      return aPiService.fetchAllCoin().map { it.toDomain() }
   }

   override suspend fun fetchByIdCoin(coinId: String): CoinDetailModel {
      return aPiService.fetchDetailsCoin(coinId = coinId).toDomain()
   }
}