package com.espino.smartlol.models

data class SummonerLeague(val league: String, val tier: String, val rank: String, val wins: Int, val losses: Int, val points: Int, val veteran: Boolean, val freshBlood: Boolean, val hotStreak: Boolean){
    override fun toString(): String {
        return "leaguename: $league || rank: $rank"
    }
}
