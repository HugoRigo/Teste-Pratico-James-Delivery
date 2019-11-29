package com.example.hugoanjos.repository

import com.example.hugoanjos.model.Cerveja

// Responsável por fazer a ligação entre o ViewModel e o Model
class CervejaRepository(private val remoteDataSource: CervejaDataSource) : CervejaDataSource {

    // Aqui ele lista todos os dados
    override fun listar(sucesso: (List<Cerveja>) -> Unit, falha: () -> Unit) {

        // Busca no repositório Remoto
        remoteDataSource.listar({
            sucesso(it)

        }, {
            //em caso de falha cai aqui
        })
    }
}