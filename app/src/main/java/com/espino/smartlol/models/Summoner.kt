package com.espino.smartlol.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Summoner(
        @PrimaryKey
        var id: Long = 0,
        var accountId: Int = 0,
        var name: String = "",
        var icon: String = "",
        var region: String = "",
        var validUntil: Long = 0,

        var leagues: RealmList<SummonerLeague> = RealmList(),
        var top_champions: RealmList<SummonerTopChampions> = RealmList()
) : RealmObject() {


    override fun toString(): String {
        return "name: $name\nleagues: ${leagues[0]}\ntop_champions: ${top_champions[0]}"
    }
}
