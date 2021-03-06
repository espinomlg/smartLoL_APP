package com.espino.smartlol.webservice

import com.espino.smartlol.models.Champion
import com.espino.smartlol.models.ChampionList
import com.espino.smartlol.models.ChampionListItem
import com.espino.smartlol.models.Summoner
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ISmartLolService {

    @GET("summoner/{name}")
    fun getSummoner(@Path("name") name: String, @Query("platform") platform: String, @Query("language") language: Int): Call<Summoner>

    @GET("champions/")
    fun getAllChampions(): Call<ChampionList>

    @GET("champion/{id}")
    fun getChampion(@Path("id") championId: Int, @Query("language") language: Int): Call<Champion>

    /*
    @GET("recentgames/{account_id}")
    fun getNormalGames(@Path("account_id") accountId: Int): Call<List<Game>>

    @GET("recentrankeds/{account_id}")
    fun getRankedsGames(@Path("account_id") accountId: Int): Call<List<Game>>

    @GET("currentgame/{summoner_id}")
    fun getCurrentGame(@Path("summoner_id") summonerId: Int): Call<CurrentGame>
    */

    companion object {
        fun create() : ISmartLolService = Retrofit.Builder()
                .baseUrl("http://192.168.1.10:5001/smartLoL/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ISmartLolService::class.java)
    }
}