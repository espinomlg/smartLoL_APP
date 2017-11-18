@file:JvmName("Utils")
package com.espino.smartlol.utils

import android.content.Context
import android.content.SharedPreferences
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.espino.smartlol.BR
import com.espino.smartlol.GlideApp
import com.espino.smartlol.R
import com.espino.smartlol.daos.ChampionDao
import com.espino.smartlol.daos.ChampionListDao
import com.espino.smartlol.daos.SummonerDao
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import java.util.*


fun Realm.summonerDao() = SummonerDao(this)
fun Realm.championListDao() = ChampionListDao(this)
fun Realm.championDao() = ChampionDao(this)

fun<T: RealmObject> RealmResults<T>.asLiveData() = LiveRealmData(this)

fun Fragment.showNetworkErrorDialog(errorResponse: NetworkErrorResponse){
    var title = ""
    var message = ""
    when(errorResponse.statusCode){
        400->{
            title = "BAD REQUEST"
            message = "LA URL O LOS QUERY PARAMS ESTÁN MAL"
        }
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
                .setPositiveButton(context.resources.getString(android.R.string.ok), null)
                .create()

        dialog.show()
    }
}

fun Fragment.showActionlessDialog(title: String, message: String){
    val dialog : AlertDialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .create()

    dialog.show()
}

fun Fragment.getLanguage() : Int{
    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    return preferences.getInt("language", getLanguageId())
}

private fun getLanguageId() : Int{
    return when (Locale.getDefault().language) {
        "en" -> 1
        "es" -> 2
        "it" -> 3
        "fr" -> 4
        "de" -> 5
        else -> 1
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?){
    GlideApp.with(view.context)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(view)
}

@BindingAdapter("entries", "layout")
fun <T> loadListData(viewGroup: ViewGroup, entries: List<T>?, layoutId: Int){

    if(entries != null){
        viewGroup.removeAllViews()
        val infalter = viewGroup.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        for(entry in entries){
            val binding: ViewDataBinding = DataBindingUtil.inflate(infalter, layoutId, viewGroup, true)
            binding.setVariable(BR.data, entry)
        }
    }
}
