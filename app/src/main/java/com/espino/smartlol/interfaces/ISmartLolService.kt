package com.espino.smartlol.interfaces

import com.espino.smartlol.models.Summoner
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ISmartLolService {

    @GET("summoner/{name}")
    fun getSummoner(@Path("name") name: String): Call<Summoner>

    /*
    @GET("recentgames/{account_id}")
    fun getNormalGames(@Path("account_id") accountId: Int): Call<List<Game>>

    @GET("recentrankeds/{account_id}")
    fun getRankedsGames(@Path("account_id") accountId: Int): Call<List<Game>>

    @GET("currentgame/{summoner_id}")
    fun getCurrentGame(@Path("summoner_id") summonerId: Int): Call<CurrentGame>

    @GET("champions/")
    fun getAllChampions(): Call<List<Champion>>

    @GET("champion/{id}")
    fun getChampion(@Path("id") championId: Int): Call<Champion>*/
}