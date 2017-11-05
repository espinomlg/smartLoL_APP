package com.espino.smartlol.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class ChampionList(
        @PrimaryKey
        var id: Int = 1,
        var champions: RealmList<ChampionListItem> = RealmList(),
        var validUntil: Long = 0
) : RealmObject()
