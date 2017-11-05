package com.espino.smartlol.daos

import com.espino.smartlol.utils.LiveRealmData
import io.realm.Realm
import io.realm.RealmObject


abstract class AbstractDao<T : RealmObject>{
    protected abstract val dbInstance: Realm
    protected abstract val FRESH_TIMEOUT: Int

    abstract fun getData(identifier: String? = null, region: String? = null): LiveRealmData<T>
    abstract fun save(element: T, dbInstance: Realm)
    abstract fun hasElement(dbInstance: Realm, identifier: String?=null, region: String? = null): Boolean

}