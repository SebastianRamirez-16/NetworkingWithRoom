package com.example.parcialmviles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialmviles.R
import com.example.parcialmviles.model.User
import com.example.parcialmviles.model.UserRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserAdapter(private val users: List<User>, private val userDao: UserDao) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJob: TextView = itemView.findViewById(R.id.tvItem1)
        val tvEmpresa: TextView = itemView.findViewById(R.id.tvItem2)
        val btnAdd: ImageButton = itemView.findViewById(R.id.ibAddFavorite)
        init {
            btnAdd.setOnClickListener {
                val user = users[adapterPosition]
                // Crear un objeto UserRoom con solo id, job y empresa
                val userRoom = UserRoom(user.id, user.job, user.empresa, "", "")
                CoroutineScope(Dispatchers.IO).launch {
                    userDao.insert(userRoom)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.tvJob.text = user.job
        holder.tvEmpresa.text = user.empresa
        // Aquí puedes agregar la lógica para el botón "btnAdd" si lo necesitas
    }
}