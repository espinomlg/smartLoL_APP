package com.espino.smartlol.interfaces

import android.content.Context
import org.json.JSONObject


interface SummonerInfoPresenter{
    fun getSummonerInfo(summonerName: String)

    interface View{
        fun getContext(): Context
        fun makeTransition(json: JSONObject)
    }
}