package com.example.cleanarchcryptocurrency.domain.model



data class CoinListModel(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
