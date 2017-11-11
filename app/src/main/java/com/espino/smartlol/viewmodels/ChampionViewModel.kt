package com.espino.smartlol.viewmodels

import com.espino.smartlol.models.Champion
import com.espino.smartlol.repositories.AbstractRepository
import com.espino.smartlol.repositories.ChampionRepository


class ChampionViewModel : AbstractViewModel<Champion>(){
    override val repository: AbstractRepository<Champion> = ChampionRepository(dbInstance)
}