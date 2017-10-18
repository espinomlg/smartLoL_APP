package com.espino.smartlol.repositories

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.espino.smartlol.interactor.SmartLolServiceImpl
import com.espino.smartlol.interfaces.ISmartLolService
import com.espino.smartlol.models.Summoner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by espino on 18/10/17.
 */
class SummonerRepository{
    private  var service: ISmartLolService = SmartLolServiceImpl.getService()

    fun getSummoner(name: String): MutableLiveData<Summoner>{
        val summoner: MutableLiveData<Summoner> = MutableLiveData()

        service.getSummoner(name).enqueue(object : Callback<Summoner>{
            override fun onFailure(call: Call<Summoner>?, t: Throwable?) {
                Log.e("RETROFIT_ERROR", t?.localizedMessage)
            }

            override fun onResponse(call: Call<Summoner>?, response: Response<Summoner>?) {
                if(response?.code() == 200){
                    summoner.value = response.body()
                    Log.e("RESPONSE", response.body().toString())
                }
                else
                    Log.e("callback_error", response?.code().toString())
            }
        }
        )

        return summoner
    }
}