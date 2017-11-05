package com.espino.smartlol.models

import io.realm.RealmObject


open class ChampionPassive(
        var name: String = "",
        var description: String = "",
        var image: String = ""
) : RealmObject()