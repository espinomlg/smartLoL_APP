package com.espino.smartlol.models

import io.realm.RealmObject

//todo this class and ChampionSpell are the same
open class ChampionPassive(
        var name: String = "",
        var description: String = "",
        var image: String = ""
) : RealmObject()