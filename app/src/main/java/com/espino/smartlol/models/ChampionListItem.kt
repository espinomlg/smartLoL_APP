package com.espino.smartlol.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChampionListItem(
        @PrimaryKey
        var id: Int = 0,
        var name: String = "",
        var image: String = ""
) : RealmObject()