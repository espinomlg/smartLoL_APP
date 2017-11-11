package com.espino.smartlol.daos

import com.espino.smartlol.models.Champion
import com.espino.smartlol.utils.LiveRealmData
import com.espino.smartlol.utils.asLiveData
import io.realm.Realm
import java.util.*


class ChampionDao(override val dbInstance: Realm) : AbstractDao<Champion>(){
    override val FRESH_TIMEOUT: Int = 5 //DAYS

    override fun getData(identifier: String?, region: String?): LiveRealmData<Champion> {
        return dbInstance.where(Champion::class.java)
                .equalTo("id", identifier?.toInt())
                .findAllAsync()
                .asLiveData()
    }

    override fun save(element: Champion, dbInstance: Realm) {
        val instant = Calendar.getInstance()
        instant.add(Calendar.DAY_OF_MONTH, FRESH_TIMEOUT)
        element.validUntil = instant.timeInMillis

        dbInstance.executeTransactionAsync {
            it.copyToRealmOrUpdate(element)
        }
    }

    override fun hasElement(dbInstance: Realm, identifier: String?, region: String?): Boolean {
        var isValidData = false

        val champion = dbInstance.where(Champion::class.java)
                .equalTo("id", identifier?.toInt())
                .findFirst()

        if(champion != null){
            val instant = Calendar.getInstance()
            val expirationTime = Calendar.getInstance()
            expirationTime.timeInMillis = champion.validUntil

            if(instant < expirationTime)
                isValidData = true
        }


        return isValidData
    }
}