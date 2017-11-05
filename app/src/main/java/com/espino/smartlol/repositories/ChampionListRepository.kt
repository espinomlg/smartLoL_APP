package com.espino.smartlol.repositories

import com.espino.smartlol.daos.AbstractDao
import com.espino.smartlol.models.ChampionList
import com.espino.smartlol.utils.championListDao
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.Realm
import java.io.IOException


class ChampionListRepository(dbInstance: Realm) : AbstractRepository<ChampionList>(){
    override val dao: AbstractDao<ChampionList> = dbInstance.championListDao()

    override fun refreshData(identifier: String?, region: String?, language: Int): NetworkErrorResponse? {
        var errorResponse: NetworkErrorResponse? = null
        Realm.getDefaultInstance().use {
            if(!dao.hasElement(it)){
                try {
                    val response = service.getAllChampions().execute()
                    if(response.code() == 200){
                        dao.save(response.body()!!, it)
                    }else{
                        errorResponse = NetworkErrorResponse(response.code(), response.headers(), response.errorBody()?.string())
                    }
                }catch (ex: IOException){
                    errorResponse = NetworkErrorResponse(1)
                }
            }
        }
        return errorResponse
    }
}