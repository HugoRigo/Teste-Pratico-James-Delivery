package com.example.hugoanjos.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hugoanjos.R
import com.example.hugoanjos.helpers.CervejaViewModelFactory
import com.example.hugoanjos.helpers.navigate
import com.example.hugoanjos.remote.Api
import com.example.hugoanjos.remote.RemoteDataSource
import com.example.hugoanjos.repository.CervejaRepository
import com.example.hugoanjos.ui.adapters.CervejaAdapter
import com.example.hugoanjos.viewmodel.ListaCervejaViewModel
import kotlinx.android.synthetic.main.lista_cerveja_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListaCervejaFragment : Fragment() {

    companion object {
        fun newInstance() = ListaCervejaFragment()
    }

    private var listener: OnListFragmentInteractionListener =
        object : OnListFragmentInteractionListener {
            override fun onListFragmentInteraction(nome: String, descricao: String, foto: String) {
                navigate(
                    ListaCervejaFragmentDirections.actionListaCervejaFragmentToDetalheCervejaFragment(
                        nome = nome,
                        descricao = descricao,
                        foto = foto
                    )
                )
            }
        }

    private lateinit var cervejaAdapter: CervejaAdapter
    lateinit var viewModel: ListaCervejaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.lista_cerveja_fragment, container, false)

        //Criar ViewModel
        viewModel = createViewModel(activity!!.application)

        this.cervejaAdapter = CervejaAdapter(listener)

        view.findViewById<RecyclerView>(R.id.idFragListaCerveja_RecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cervejaAdapter
        }

        /*Informa ao lifecycle que o ViewModel irá
         observar seu ciclo de vida*/
        this.lifecycle.addObserver(viewModel)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.cervejas.observe(this, Observer {
            if (it != null) {
                cervejaAdapter.values = it
                cervejaAdapter.notifyDataSetChanged()

                if (it.isEmpty()) {
                    //list.hide()
                    //feedback.show()
                } else {
                    //list.show()
                    //feedback.hide()
                }
            }
        })

        idFragListaCerveja_Swipe.setOnRefreshListener {
            viewModel.atualiza()
        }

        viewModel.isRefreshing.observe(this, Observer {
            idFragListaCerveja_Swipe.isRefreshing = it
        })

    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(nome: String, descricao: String, foto: String)
    }


    // Criar ViewModel
    fun createViewModel(application: Application): ListaCervejaViewModel {
        // Define o Repositório remoto
        val retrofit =
            Retrofit.Builder().baseUrl("https://api.punkapi.com/v2/").addConverterFactory(
                GsonConverterFactory.create()
            ).build()
        val remoteDataSource = RemoteDataSource(retrofit.create(Api::class.java))


        // Repositório - Passa para ele os 2 possíveis repositorios de dados Local e Remoto
        val repository = CervejaRepository(remoteDataSource)

        // Factory
        val factory = CervejaViewModelFactory(repository, application)

        return ViewModelProviders.of(this, factory).get(ListaCervejaViewModel::class.java)
    }

}