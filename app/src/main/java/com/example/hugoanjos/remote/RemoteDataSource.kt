package com.example.hugoanjos.remote

import com.example.hugoanjos.model.Cerveja
import com.example.hugoanjos.repository.CervejaDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// Essa clase recebe como parâmetro a interface de Api - contendo todos os Endpoints
class RemoteDataSource(val api: Api) : CervejaDataSource {

    // Lê o Endpoint retornando todos os resultados
    override fun listar(sucesso: (List<Cerveja>) -> Unit, falha: () -> Unit) {
        val call = api.listar()
        call.enqueue(object : Callback<List<Cerveja>> {
            override fun onResponse(
                call: Call<List<Cerveja>>,
                response: Response<List<Cerveja>>
            ) {
                if (response.isSuccessful) {

                    val cervejas = mutableListOf<Cerveja>()
                    response.body()?.forEach {
                        cervejas.add(it)
                    }
                    sucesso(cervejas)
                } else {
                    falha()

                }
            }
            override fun onFailure(
                call: Call<List<Cerveja>>,
                t: Throwable?
            ) {

                falha()
            }
        })
    }
}