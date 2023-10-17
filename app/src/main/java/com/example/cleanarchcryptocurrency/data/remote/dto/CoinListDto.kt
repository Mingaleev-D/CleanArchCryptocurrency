package com.example.cleanarchcryptocurrency.data.remote.dto

import com.example.cleanarchcryptocurrency.domain.model.CoinListModel
import com.google.gson.annotations.SerializedName

data class CoinListDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("type")
    val type: String
)

fun CoinListDto.toDomain(): CoinListModel {
   return CoinListModel(
       id = id,
       isActive = isActive,
       name = name,
       rank = rank,
       symbol = symbol
   )
}
