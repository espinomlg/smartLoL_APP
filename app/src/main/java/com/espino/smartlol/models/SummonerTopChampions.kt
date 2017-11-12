package com.espino.smartlol.models

import io.realm.RealmObject


open class SummonerTopChampions(
        var name: String = "",
        var title: String = "",
        var image: String = "",
        var lvl: Int = 0,
        var points: Int = 0
) : RealmObject() {
    override fun toString(): String {
        return "champname: $name || title: $title"
    }
}