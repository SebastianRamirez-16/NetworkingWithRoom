package com.example.parcialmviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialmviles.adapter.UserAdapter
import com.example.parcialmviles.api.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var rvItems: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvItems = findViewById(R.id.rvItems)
        rvItems.layoutManager = LinearLayoutManager(this)

        // Consumir la API
        CoroutineScope(Dispatchers.IO).launch {
            val users = RetrofitBuilder.apiService.getUsers()
            withContext(Dispatchers.Main) {
                adapter = UserAdapter(users)
                rvItems.adapter = adapter
            }
        }
    }
}
