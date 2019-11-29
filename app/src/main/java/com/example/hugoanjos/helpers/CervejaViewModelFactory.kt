package com.example.hugoanjos.helpers

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hugoanjos.repository.CervejaDataSource
import com.example.hugoanjos.viewmodel.ListaCervejaViewModel

// Se usa a Factory para gerenciar a lógica de criação do ViewModel.

class CervejaViewModelFactory constructor(private val repository: CervejaDataSource, private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(ListaCervejaViewModel::class.java) ->
                        ListaCervejaViewModel(repository, application)
                    else ->
                        throw IllegalArgumentException("Classe desconhecida.")
                }
            } as T
}