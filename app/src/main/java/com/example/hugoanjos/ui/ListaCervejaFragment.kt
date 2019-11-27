package com.example.hugoanjos.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.hugoanjos.R
import com.example.hugoanjos.viewmodel.ListaCervejaViewModel

class ListaCervejaFragment : Fragment() {

    companion object {
        fun newInstance() = ListaCervejaFragment()
    }

    private lateinit var viewModel: ListaCervejaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_cerveja_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListaCervejaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
