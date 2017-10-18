package com.espino.smartlol.interactor

import android.app.AlertDialog
import android.content.Context
import com.espino.smartlol.interfaces.ISmartLolService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SmartLolServiceImpl{
    val baseURL = "http://192.168.1.10:5001/smartLoL/"

    fun getService(): ISmartLolService{
        val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ISmartLolService::class.java)
    }

    fun showErrorDialog(errorCode: Int, context: Context){
        val errorDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        errorDialog.setTitle("ERROR")
            .setMessage("Ha ocurrido un error con la API $errorCode")
            .show()
    }
}