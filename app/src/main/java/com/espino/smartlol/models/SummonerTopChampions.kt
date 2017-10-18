package com.espino.smartlol.models


data class SummonerTopChampions(val name: String, val title: String, val img_url: String, val lvl: Int, val points: Int){
    override fun toString(): String {
        return "champname: $name || title: $title"
    }
}