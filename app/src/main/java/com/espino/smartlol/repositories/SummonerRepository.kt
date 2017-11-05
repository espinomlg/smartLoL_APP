package com.espino.smartlol.repositories

import com.espino.smartlol.daos.SummonerDao
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.utils.summonerDao
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.Realm
import java.io.IOException


class SummonerRepository(dbInstance: Realm): AbstractRepository<Summoner>() {
    override val dao: SummonerDao = dbInstance.summonerDao()

     override fun refreshData(identifier: String, region: String?): NetworkErrorResponse?{
        var errorResponse: NetworkErrorResponse? = null
        Realm.getDefaultInstance().use {
            if (!dao.hasElement(identifier, it, region!!)) {
                try {
                    val response = service.getSummoner(identifier, region, "2").execute()
                    if (response.code() == 200) {
                        val summoner: Summoner = response.body()!!
                        summoner.region = region
                        dao.save(summoner, it)
                    } else {
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