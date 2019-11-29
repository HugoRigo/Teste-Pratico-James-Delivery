package com.example.hugoanjos.remote

import com.example.hugoanjos.model.Cerveja
import retrofit2.Call
import retrofit2.http.*

// Interface onde o retrofit transformará em uma requisição http

interface Api {

    // Passando o EndPont na anotação
    @GET("beers")
    fun listar(): Call<List<Cerveja>>
}