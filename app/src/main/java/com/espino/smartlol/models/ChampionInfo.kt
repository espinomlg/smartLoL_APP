package com.espino.smartlol.models

import io.realm.RealmObject


open class ChampionInfo(
        var attack: Int = 0,
        var defense: Int = 0,
        var magic: Int = 0,
        var difficulty: Int = 0
) : RealmObject()