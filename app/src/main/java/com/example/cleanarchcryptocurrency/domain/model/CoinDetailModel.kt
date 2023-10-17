package com.example.cleanarchcryptocurrency.domain.model

import com.example.cleanarchcryptocurrency.data.remote.dto.detail.Team

data class CoinDetailModel(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: String,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<Team>,
)

//data class TeamMember(
//    val name: String
//)
