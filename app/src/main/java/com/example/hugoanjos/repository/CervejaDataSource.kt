package com.example.hugoanjos.repository

import com.example.hugoanjos.model.Cerveja

/*Essa interface é um contrato entre nosso repositório e as
fontes de dados que nosso repositório poderá acessar.*/

interface CervejaDataSource {
    fun listar(sucesso: (List<Cerveja>) -> Unit, falha: () -> Unit)
}