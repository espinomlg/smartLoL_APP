@file:JvmName("Utils")
package com.espino.smartlol.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import com.espino.smartlol.R
import com.espino.smartlol.daos.SummonerDao
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

fun Realm.summonerDao() = SummonerDao(this)

fun<T: RealmObject> RealmResults<T>.asLiveData() = LiveRealmData(this)

fun Fragment.showNetworkErrorDialog(errorResponse: NetworkErrorResponse){
    var title = ""
    var message = ""
    when(errorResponse.statusCode){
        403->{
            title = context.resources.getString(R.string.networK_error_response_403_title)
            message = context.resources.getString(R.string.networK_error_response_403_message)
        }
        404->{
            title = context.resources.getString(R.string.networK_error_response_404_title)
            message = context.resources.getString(R.string.networK_error_response_404_message)
        }
        1->{
            title = context.resources.getString(R.string.networK_error_response_1_title)
            message = context.resources.getString(R.string.networK_error_response_1_message)
        }
    }

    if(title != "" && message != ""){
        val dialog: AlertDialog = AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(context.resources.getString(R.string.btn_ok), null)
                .create()

        dialog.show()
    }

}
