package com.espino.smartlol.repositories

import android.arch.lifecycle.MutableLiveData
import com.espino.smartlol.utils.LiveRealmData
import com.espino.smartlol.daos.AbstractDao
import com.espino.smartlol.webservice.ISmartLolService
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.RealmObject
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.Ref
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg


abstract class AbstractRepository<T: RealmObject>{
    protected abstract val dao: AbstractDao<T>
    protected val service: ISmartLolService = ISmartLolService.create()
    val networkError: MutableLiveData<NetworkErrorResponse> = MutableLiveData()

    fun getData(identifier: String?, region: String? = null, language: Int = 0): LiveRealmData<T>{
        refreshDataInBackground(identifier, region, language)

        return  dao.getData(identifier, region)
    }

    private fun refreshDataInBackground(identifier: String?, region: String?, language: Int){
        val ref: Ref<AbstractRepository<T>> = this.asReference()
        async(UI){
            val result = bg {
                refreshData(identifier, region, language)
            }

            val errorResponse: NetworkErrorResponse? = result.await()
            if(errorResponse != null)
                ref().networkError.value = result.await()
        }
    }

    protected abstract fun refreshData(identifier: String? = null, region: String? = null, language: Int = 0): NetworkErrorResponse?

}
