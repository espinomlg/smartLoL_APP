package com.espino.smartlol.daos

import com.espino.smartlol.utils.LiveRealmData
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.utils.asLiveData
import io.realm.Case
import io.realm.Realm
import java.util.*


class SummonerDao(override var dbInstance: Realm) : AbstractDao<Summoner>() {

    override val FRESH_TIMEOUT = 1 //day

    override fun getData(identifier: String): LiveRealmData<Summoner> {
        return dbInstance.where(Summoner::class.java)
                .equalTo("name", identifier, Case.INSENSITIVE)
                .findAllAsync()
                .asLiveData()
    }

    override fun save(element: Summoner, dbInstance: Realm) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, FRESH_TIMEOUT)
        element.validUntil = calendar.timeInMillis

        dbInstance.executeTransactionAsync {
            it.copyToRealm(element)
        }

    }

    override fun hasElement(identifier: String, dbInstance: Realm): Boolean {
        var isValidData = false

        val summoner: Summoner? = dbInstance.where(Summoner::class.java)
                .equalTo("name", identifier, Case.INSENSITIVE)
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

