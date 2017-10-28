package com.espino.smartlol

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class SmartLolApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("smartlol")
                .schemaVersion(1)
                .build()

        Realm.setDefaultConfiguration(config)
    }
}
