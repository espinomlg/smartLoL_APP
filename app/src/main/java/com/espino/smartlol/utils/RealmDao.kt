@file:JvmName("RealmDao")
package com.espino.smartlol.utils

import com.espino.smartlol.RealmLiveData.LiveRealmData
import com.espino.smartlol.daos.SummonerDao
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

fun Realm.summonerDao() = SummonerDao(this)

fun<T: RealmObject> RealmResults<T>.asLiveData() = LiveRealmData(this)
