package com.espino.smartlol.daos

import com.espino.smartlol.RealmLiveData.LiveRealmDataMultiple
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.utils.asLiveData
import io.realm.Case
import io.realm.Realm
import java.util.*


class SummonerDao(private var dbInstance: Realm) {

    private val FRESH_TIMEOUT = 1 //day

    fun getSummoner(name: String): LiveRealmDataMultiple<Summoner> {
        val results = dbInstance.where(Summoner::class.java)
                .equalTo("name", name, Case.INSENSITIVE)
                .findAllAsync()
                .asLiveData()

        return results
    }

    fun save(summoner: Summoner, dbInstance: Realm) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, FRESH_TIMEOUT)
        summoner.validUntil = calendar.timeInMillis

        dbInstance.executeTransactionAsync {
            it.copyToRealm(summoner)
        }

    }

    fun hasSummoner(name: String, dbInstance: Realm): Boolean {
        var isValidData = false

        val summoner: Summoner? = dbInstance.where(Summoner::class.java)
                .equalTo("name", name, Case.INSENSITIVE)
                .findFirst()

        if (summoner != null) {
            val instant = Calendar.getInstance()
            val expirationTime = Calendar.getInstance()
            expirationTime.time = Date(summoner.validUntil)

            if (instant < expirationTime)
                isValidData = true

        }

        return isValidData
    }

}

