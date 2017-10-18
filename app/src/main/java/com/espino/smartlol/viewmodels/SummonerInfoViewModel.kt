package com.espino.smartlol.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.repositories.SummonerRepository

/**
 * Created by espino on 18/10/17.
 */
class SummonerInfoViewModel(val summonerRepo: SummonerRepository = SummonerRepository()) : ViewModel(){
    private var summoner: LiveData<Summoner>? = null

    fun init(summonerName: String){
        if(this.summoner == null) {
            this.summoner = summonerRepo.getSummoner(summonerName)
        }
    }

    fun getSummoner(): LiveData<Summoner>? = summoner

}