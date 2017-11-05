package com.espino.smartlol.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.espino.smartlol.utils.LiveRealmData
import com.espino.smartlol.repositories.AbstractRepository
import com.espino.smartlol.webservice.NetworkErrorResponse
import io.realm.Realm
import io.realm.RealmObject


abstract class AbstractViewModel<T: RealmObject> : ViewModel(){
    protected val dbInstance: Realm = Realm.getDefaultInstance()
    var networkError: MutableLiveData<NetworkErrorResponse>? = null
    protected abstract val repository: AbstractRepository<T>
    abstract var data: LiveRealmData<T>?

    fun init(identifier: String? = null, region: String? = null, language: Int = 0){
        if(this.data == null)
            this.data = repository.getData(identifier, region, language)
        if(this.networkError == null)
            networkError = repository.networkError
    }

    override fun onCleared() {
        dbInstance.close()
        super.onCleared()
    }
}