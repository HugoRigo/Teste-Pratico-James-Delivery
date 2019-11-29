package com.example.hugoanjos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.hugoanjos.model.Cerveja
import com.example.hugoanjos.repository.CervejaDataSource

class ListaCervejaViewModel(val repository: CervejaDataSource, application: Application) :
    ViewModel(), LifecycleObserver {


    private val _cervejas = MutableLiveData<List<Cerveja>>()
    val cervejas: LiveData<List<Cerveja>>
        get() = _cervejas

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun atualiza() {
        _isRefreshing.postValue(true)

        repository.listar({ items ->
            _cervejas.postValue(items)
            _isRefreshing.postValue(false)
            if (items.isEmpty()) {

            }
        }, {
        })

    }
}