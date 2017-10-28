package com.espino.smartlol.repositories

import android.os.AsyncTask
import com.espino.smartlol.RealmLiveData.LiveRealmData
import com.espino.smartlol.daos.SummonerDao
import com.espino.smartlol.interfaces.ISmartLolService
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.utils.summonerDao
import io.realm.Realm


class SummonerRepository(dbInstance: Realm){
    private  var service: ISmartLolService = ISmartLolService.create()
    private var summonerDao: SummonerDao = dbInstance.summonerDao()

    fun getSummoner(name: String): LiveRealmData<Summoner> {
        refreshSummoner(name)

        return summonerDao.getSummoner(name)
    }

    private fun refreshSummoner(name: String) {
        AsyncTask.execute{
            Realm.getDefaultInstance().use {
                if (!summonerDao.hasSummoner(name, it)) {
                    val summoner: Summoner? = service.getSummoner(name).execute().body()
                    if(summoner != null)
                        summonerDao.save(summoner, it)
                }
            }

        }
    }


}