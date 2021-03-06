package com.espino.smartlol.utils

import android.arch.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmObject
import io.realm.RealmResults

class LiveRealmData<T : RealmObject>(private var result: RealmResults<T>) : LiveData<RealmResults<T>>(){

    private val listener: RealmChangeListener<RealmResults<T>> = RealmChangeListener {
        value = it
    }

    override fun onActive() {
        result.addChangeListener(listener)
    }

    override fun onInactive() {
        result.removeChangeListener(listener)
    }
}