package com.example.hugoanjos.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.hugoanjos.R
import com.example.hugoanjos.model.Cerveja
import com.example.hugoanjos.ui.ListaCervejaFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cerveja.view.*


class CervejaAdapter(
    private val listener: ListaCervejaFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var values: List<Cerveja> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int = when (position) {
        else -> ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_cerveja, parent, false)
                ItemViewHolder(view)
            }
            else -> throw Error()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val item = values[position]
                holder.nome.text = item.name

                holder.filtro.visibility = View.VISIBLE
                Picasso.get().load(item.image_url).noFade()
                    .centerInside().fit()
                    .into(holder.foto, object : Callback {
                        override fun onError(e: Exception?) {
                        }

                        override fun onSuccess() {
                            holder.filtro.visibility = View.GONE
                        }
                    })

                holder.main.setOnClickListener {
                    val args = Bundle()
                    args.putString("nome", item.name)
                    args.putString("descricao", item.description)
                    args.putString("foto", item.image_url)
                    Navigation.findNavController(holder.main)
                        .navigate(R.id.action_listaCervejaFragment_to_detalheCervejaFragment, args)
                }
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val main: CardView = view.idItemCerveja_CardView
        val nome: TextView = view.idItemCerveja_TextViewTitulo
        val foto: ImageView = view.idItemCerveja_ImageViewFoto
        val filtro: ConstraintLayout = view.idItemCerveja_ConstraintLayoutFiltro

    }

    companion object {
        private const val ITEM = 0
    }
}