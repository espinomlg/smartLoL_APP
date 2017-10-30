package com.espino.smartlol.abstractclasses

import com.espino.smartlol.RealmLiveData.LiveRealmData
import io.realm.Realm
import io.realm.RealmObject


abstract class AbstractDao<T : RealmObject>{
    protected abstract val dbInstance: Realm
    protected abstract val FRESH_TIMEOUT: Int

    abstract fun getData(identifier: String): LiveRealmData<T>
    abstract fun save(element: T, dbInstance: Realm)
    abstract fun hasElement(identifier: String, dbInstance: Realm): Boolean
}