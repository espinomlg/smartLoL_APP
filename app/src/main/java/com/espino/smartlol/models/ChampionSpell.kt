package com.espino.smartlol.models

import io.realm.RealmObject


open class ChampionSpell(
        var name: String = "",
        var description: String = "",
        var image: String = ""
) : RealmObject()