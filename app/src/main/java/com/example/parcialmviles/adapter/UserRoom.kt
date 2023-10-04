package com.example.parcialmviles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialmviles.R
import com.example.parcialmviles.model.UserRoom

class FavoritesAdapter(private val users: List<UserRoom>) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJob: TextView = itemView.findViewById(R.id.tvItem1)
        val tvEmpresa: TextView = itemView.findViewById(R.id.tvItem2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_item, parent, false)
        return FavoritesViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val user = users[position]
        holder.tvJob.text = user.job
        holder.tvEmpresa.text = user.empresa
    }
}
