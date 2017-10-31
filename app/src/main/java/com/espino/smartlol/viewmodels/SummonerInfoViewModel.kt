package com.espino.smartlol.viewmodels


import com.espino.smartlol.utils.LiveRealmData
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.repositories.SummonerRepository



class SummonerInfoViewModel : AbstractViewModel<Summoner>(){
    override val repository: SummonerRepository = SummonerRepository(dbInstance)
    override var data: LiveRealmData<Summoner>? = null
}