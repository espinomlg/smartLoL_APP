package com.espino.smartlol.models

data class Summoner(val id: Int, val accountId: Int, val name: String, val icon: String, val leagues: Array<SummonerLeague>, val top_champions: Array<SummonerTopChampions>){
    override fun toString(): String {
        var repr = "name: $name\nleagues: ${leagues[0]}\ntop_champions: ${top_champions[0]}"

        return repr
    }
}
