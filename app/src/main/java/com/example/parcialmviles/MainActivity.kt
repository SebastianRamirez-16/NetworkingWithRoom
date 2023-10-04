package com.example.parcialmviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialmviles.adapter.UserAdapter
import com.example.parcialmviles.adapter.UserDao
import com.example.parcialmviles.api.RetrofitBuilder
import com.example.parcialmviles.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var rvItems: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userDao = AppDatabase.getDatabase(this).userDao()

        rvItems = findViewById(R.id.rvItems)
        rvItems.layoutManager = LinearLayoutManager(this)

        // Consumir la API
        CoroutineScope(Dispatchers.IO).launch {
            val users = RetrofitBuilder.apiService.getUsers()
            withContext(Dispatchers.Main) {
                adapter = UserAdapter(users, userDao)
                rvItems.adapter = adapter
            }
        }

        val btnFavorites: ImageButton = findViewById(R.id.btnFavorites)
        btnFavorites.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }
}
