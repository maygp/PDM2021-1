package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListaDesejosAdapter(private val lista: ArrayList<Desejo>) : RecyclerView.Adapter<ListaDesejosAdapter.DesejoViewHolder>() {

    var listener: ItemClickListenerRecycleView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesejoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.desejo_items, parent, false)
        return DesejoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DesejoViewHolder, position: Int) {
        val desejo = lista[position]
        holder.tvNota.text = desejo.nota.toString()
        holder.tvDescricao.text = desejo.descricao
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class DesejoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNota: TextView
        var tvDescricao: TextView

        init {

            this.tvDescricao = itemView.findViewById(R.id.tvDescricao)
            this.tvNota = itemView.findViewById(R.id.tvNota)

            itemView.setOnClickListener {
                this@ListaDesejosAdapter.listener?.onItemClick(this.adapterPosition)
            }

            itemView.setOnLongClickListener  {
                this@ListaDesejosAdapter.listener?.onItemClick(this.adapterPosition)
                true
            }
        }
    }

}

