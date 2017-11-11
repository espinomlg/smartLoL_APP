package com.espino.smartlol.repositories

import com.espino.smartlol.daos.AbstractDao
import com.espino.smartlol.models.Champion
import com.espino.smartlol.utils.championDao
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.Realm
import java.io.IOException


class ChampionRepository(dbInstance: Realm) : AbstractRepository<Champion>(){
    override val dao: AbstractDao<Champion> = dbInstance.championDao()

    override fun refreshData(identifier: String?, region: String?, language: Int): NetworkErrorResponse? {
        var errorResponse: NetworkErrorResponse? = null

        Realm.getDefaultInstance().use {
            if(!dao.hasElement(it, identifier = identifier!!)){
                try {
                    val response = service.getChampion(identifier.toInt(), language).execute()
                    if(response.code() == 200){
                        val champion = response.body()!!
                        dao.save(champion, it)
                    }else{
                        errorResponse = NetworkErrorResponse(response.code(), response.headers(), response.errorBody()?.string())
                    }
                } catch (ex: IOException) {
                    errorResponse = NetworkErrorResponse(1)
                }
            }
        }

        return errorResponse
    }
}