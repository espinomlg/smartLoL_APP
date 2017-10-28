package com.espino.smartlol.RealmLiveData

import android.arch.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmObject


class LiveRealmDataSingle<T : RealmObject>(private var result: T?) : LiveData<T>(){

    private val listener: RealmChangeListener<T> = RealmChangeListener {
        value = it
    }

    override fun onActive() {
        result?.addChangeListener(listener)
    }

    override fun onInactive() {
        result?.removeChangeListener(listener)
    }
}