package com.espino.smartlol.abstractclasses

import com.espino.smartlol.RealmLiveData.LiveRealmData
import io.realm.Realm
import io.realm.RealmObject


abstract class AbstractDao<T : RealmObject>(private var dbinstance: Realm){
    abstract fun getData(identifier: String): LiveRealmData<T>
    abstract fun save(element: T, dbinstance: Realm)
    abstract fun hasElement(identifier: String, dbInstance: Realm): Boolean
}