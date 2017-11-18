package com.espino.smartlol.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Champion(
        @PrimaryKey
        var id: Int = 0,
        var name: String = "",
        var title: String = "",
        var image: String = "",
        var info: ChampionInfo? = null,
        var passive: ChampionPassive? = null,
        var spells: RealmList<ChampionSpell> = RealmList(),
        var skins: RealmList<ChampionSkin> = RealmList(),
        var rols: RealmList<String> = RealmList(),
        var ally_tips: RealmList<String> = RealmList(),
        var enemy_tips: RealmList<String> = RealmList(),
        var validUntil: Long = 0
) : RealmObject()