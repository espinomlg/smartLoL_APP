package com.espino.smartlol.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.espino.smartlol.RealmLiveData.LiveRealmData
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.repositories.SummonerRepository
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.Realm


class SummonerInfoViewModel : ViewModel(){
    private val dbInstance = Realm.getDefaultInstance()
    private val summonerRepo: SummonerRepository = SummonerRepository(dbInstance)
    private var summoner: LiveRealmData<Summoner>? = null
    private var networkError: MutableLiveData<NetworkErrorResponse>? = null

    fun init(summonerName: String){
        if(this.summoner == null) {
            this.summoner = summonerRepo.getData(summonerName)
        }
        if(networkError == null){
            this.networkError = summonerRepo.networkError
        }
    }

    fun getSummoner(): LiveRealmData<Summoner>? = summoner

    fun getNetworkError(): MutableLiveData<NetworkErrorResponse>? = networkError

    override fun onCleared() {
        dbInstance.close()
        super.onCleared()
    }
}