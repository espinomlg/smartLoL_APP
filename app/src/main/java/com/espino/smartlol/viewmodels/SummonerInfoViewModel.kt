package com.espino.smartlol.viewmodels

import android.arch.lifecycle.ViewModel
import com.espino.smartlol.RealmLiveData.LiveRealmDataMultiple
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.repositories.SummonerRepository
import io.realm.Realm

/**
 * Created by espino on 18/10/17.
 */
class SummonerInfoViewModel : ViewModel(){
    private val dbInstance = Realm.getDefaultInstance()
    private val summonerRepo: SummonerRepository = SummonerRepository(dbInstance)
    private var summoner: LiveRealmDataMultiple<Summoner>? = null

    fun init(summonerName: String){
        if(this.summoner == null) {
            this.summoner = summonerRepo.getSummoner(summonerName)
        }
    }

    fun getSummoner(): LiveRealmDataMultiple<Summoner>? = summoner

    override fun onCleared() {
        dbInstance.close()
        super.onCleared()
    }
}