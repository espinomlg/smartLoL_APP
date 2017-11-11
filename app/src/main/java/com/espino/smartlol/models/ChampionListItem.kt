package com.espino.smartlol.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class ChampionListItem(
        @PrimaryKey
        var id: Int = 0,
        var name: String = "",
        var image: String = ""
) : RealmObject(){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ChampionListItem) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}