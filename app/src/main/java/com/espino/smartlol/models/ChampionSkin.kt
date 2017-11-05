package com.espino.smartlol.models

import io.realm.RealmObject


open class ChampionSkin(
        var name: String = "",
        var image: String = ""
) : RealmObject()
