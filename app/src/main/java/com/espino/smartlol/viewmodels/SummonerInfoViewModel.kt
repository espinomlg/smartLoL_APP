package com.espino.smartlol.viewmodels


import com.espino.smartlol.utils.LiveRealmData
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.repositories.SummonerRepository



class SummonerInfoViewModel : AbstractViewModel<Summoner>(){
    override val repository: SummonerRepository = SummonerRepository(dbInstance)
    override var data: LiveRealmData<Summoner>? = null

    fun validateSummonerName(name: String): Boolean = name.length in 3..16//todo it cannot have word "riot" in it

}