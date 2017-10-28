package com.espino.smartlol.models

import io.realm.RealmObject


open class SummonerLeague(
        var league: String = "",
        var tier: String = "",
        var rank: String = "",
        var wins: Int = 0,
        var losses: Int = 0,
        var points: Int = 0,
        var veteran: Boolean = false,
        var freshBlood: Boolean = false,
        var hotStreak: Boolean = false
) : RealmObject() {

    override fun toString(): String {
        return "leaguename: $league || rank: $rank"
    }

}
