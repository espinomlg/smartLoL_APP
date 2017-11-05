package com.espino.smartlol.viewmodels

import com.espino.smartlol.models.ChampionList
import com.espino.smartlol.repositories.AbstractRepository
import com.espino.smartlol.repositories.ChampionListRepository
import com.espino.smartlol.utils.LiveRealmData


class ChampionListViewModel() : AbstractViewModel<ChampionList>(){
    override val repository: AbstractRepository<ChampionList> = ChampionListRepository(dbInstance)
    override var data: LiveRealmData<ChampionList>? = null
}