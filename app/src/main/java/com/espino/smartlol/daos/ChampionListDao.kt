package com.espino.smartlol.daos

import com.espino.smartlol.models.ChampionList
import com.espino.smartlol.utils.LiveRealmData
import com.espino.smartlol.utils.asLiveData
import io.realm.Realm
import java.util.*


class ChampionListDao(override val dbInstance: Realm) : AbstractDao<ChampionList>(){
    override val FRESH_TIMEOUT: Int = 15 //days

    override fun getData(identifier: String?, region: String?): LiveRealmData<ChampionList> {
        return dbInstance.where(ChampionList::class.java)
                .findAllAsync()
                .asLiveData()
    }

    override fun save(element: ChampionList, dbInstance: Realm) {
        val instant = Calendar.getInstance()
        instant.add(Calendar.DAY_OF_MONTH, FRESH_TIMEOUT)
        element.validUntil = instant.timeInMillis

        dbInstance.executeTransactionAsync {
            it.copyToRealmOrUpdate(element)
        }
    }

    override fun hasElement(dbInstance: Realm, identifier: String?, region: String?): Boolean {
        var isValidData = false

        val championList: ChampionList? = dbInstance.where(ChampionList::class.java)
                .findFirst()

        if(championList != null){
            val instant = Calendar.getInstance()
            val expirationTime = Calendar.getInstance()
            expirationTime.time = Date(championList.validUntil)

            if(instant < expirationTime)
                isValidData = true
        }

        return isValidData
    }
}