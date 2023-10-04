package com.example.parcialmviles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialmviles.adapter.FavoritesAdapter
import com.example.parcialmviles.adapter.UserDao
import com.example.parcialmviles.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesActivity : AppCompatActivity() {

    private lateinit var rvFavorites: RecyclerView
    private lateinit var adapter: FavoritesAdapter
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites_activity)

        rvFavorites = findViewById(R.id.rvFavorites)
        rvFavorites.layoutManager = LinearLayoutManager(this)

        userDao = AppDatabase.getDatabase(this).userDao()

        // Obtener los usuarios favoritos de la base de datos
        CoroutineScope(Dispatchers.IO).launch {
            val favoriteUsers = userDao.getAll()
            withContext(Dispatchers.Main) {
                adapter = FavoritesAdapter(favoriteUsers)
                rvFavorites.adapter = adapter
            }
        }
    }
}
