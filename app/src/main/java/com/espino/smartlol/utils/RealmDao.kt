@file:JvmName("RealmDao")
package com.espino.smartlol.utils

import com.espino.smartlol.RealmLiveData.LiveRealmDataMultiple
import com.espino.smartlol.RealmLiveData.LiveRealmDataSingle
import com.espino.smartlol.daos.SummonerDao
import com.espino.smartlol.models.Summoner
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

fun Realm.summonerDao() = SummonerDao(this)

fun<T: RealmObject> RealmResults<T>.asLiveData() = LiveRealmDataMultiple(this)
fun<T: RealmObject> Summoner.asLiveData() = LiveRealmDataSingle(this)
