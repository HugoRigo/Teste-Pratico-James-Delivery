package com.example.hugoanjos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hugoanjos.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detalhe_cerveja_fragment.*

class DetalheCervejaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detalhe_cerveja_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {

            idFragDetalheCerveja_TextViewNome.text = bundle.getString("nome")
            idFragDetalheCerveja_TextViewDescricao.text = bundle.getString("descricao")
            idFragDetalheCerveja_ConstraintLayoutFiltro.visibility = View.VISIBLE
            Picasso.get().load(bundle.getString("foto")).noFade()
                .centerInside().fit()
                .into(idFragDetalheCerveja_ImageViewFoto, object : Callback {
                    override fun onError(e: Exception?) {
                    }

                    override fun onSuccess() {
                        idFragDetalheCerveja_ConstraintLayoutFiltro.visibility = View.GONE
                    }
                })
        }
    }
}
