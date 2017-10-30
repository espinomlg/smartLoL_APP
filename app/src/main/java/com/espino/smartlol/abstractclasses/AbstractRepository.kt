package com.espino.smartlol.abstractclasses

import android.arch.lifecycle.MutableLiveData
import com.espino.smartlol.RealmLiveData.LiveRealmData
import com.espino.smartlol.webservice.ISmartLolService
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.Realm
import io.realm.RealmObject
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.Ref
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import java.io.IOException

abstract class AbstractRepository<T: RealmObject>{
    protected val service: ISmartLolService = ISmartLolService.create()
    protected abstract val dao: AbstractDao<T>
    val networkError: MutableLiveData<NetworkErrorResponse> = MutableLiveData()

    abstract fun getData(identifier: String): LiveRealmData<T>

    protected abstract fun refreshData(identifier: String): NetworkErrorResponse?

    protected fun refreshDataInBackground(identifier: String){
        val ref: Ref<AbstractRepository<T>> = this.asReference()
        async(UI){
            val result = bg {
                refreshData(identifier)
            }

            val errorResponse: NetworkErrorResponse? = result.await()
            if(errorResponse != null)
                ref().networkError.value = result.await()
        }
    }

}
