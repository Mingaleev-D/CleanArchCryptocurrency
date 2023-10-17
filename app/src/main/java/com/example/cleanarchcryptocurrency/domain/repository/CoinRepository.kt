package com.example.cleanarchcryptocurrency.domain.repository

import com.example.cleanarchcryptocurrency.domain.model.CoinDetailModel
import com.example.cleanarchcryptocurrency.domain.model.CoinListModel

/**
 * @author : Mingaleev D
 * @data : 17.10.2023
 */

interface CoinRepository {

   suspend fun fetchAllCoin(): List<CoinListModel>

   suspend fun fetchByIdCoin(coinId: String): CoinDetailModel
}