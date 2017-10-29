package com.espino.smartlol.repositories

import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import com.espino.smartlol.RealmLiveData.LiveRealmData
import com.espino.smartlol.daos.SummonerDao
import com.espino.smartlol.webservice.ISmartLolService
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.utils.summonerDao
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.Realm
import java.io.IOException


class SummonerRepository(dbInstance: Realm) {
    private var service: ISmartLolService = ISmartLolService.create()
    private var summonerDao: SummonerDao = dbInstance.summonerDao()
    private var networkError: MutableLiveData<NetworkErrorResponse> = MutableLiveData()

    fun getSummoner(name: String): LiveRealmData<Summoner> {
        refreshSummoner(name)

        return summonerDao.getSummoner(name)
    }

    fun getNetworkError(): MutableLiveData<NetworkErrorResponse> = networkError


    private fun refreshSummoner(name: String) {

        object : AsyncTask<Void, Void, NetworkErrorResponse?>() {
            override fun doInBackground(vararg p0: Void?): NetworkErrorResponse? {
                var errorResponse: NetworkErrorResponse? = null
                Realm.getDefaultInstance().use {
                    if (!summonerDao.hasSummoner(name, it)) {
                        try {
                            val response = service.getSummoner(name, "euw1", "2").execute()
                            if (response.code() == 200) {
                                summonerDao.save(response.body()!!, it)
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

            override fun onPostExecute(result: NetworkErrorResponse?) {
                super.onPostExecute(result)
                if (result != null)
                    networkError.value = result
            }
        }.execute()

    }
}