package com.espino.smartlol.models

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject


open class Champion(
        var id: Int = 0,
        var name: String = "",
        var title: String = "",
        var image: String = "",
        var info: ChampionInfo? = null,
        var passive: ChampionPassive? = null,
        var spells: RealmList<ChampionSpell> = RealmList(),
       // var rols: RealmList<String> = RealmList(),
        var skins: RealmList<ChampionSkin> = RealmList()
        //var allyTips: RealmList<String> = RealmList(),
        //var enemyTips: RealmList<String> = RealmList()
) : RealmObject()