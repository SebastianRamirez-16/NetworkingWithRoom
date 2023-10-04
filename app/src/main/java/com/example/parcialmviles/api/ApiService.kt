package com.example.parcialmviles.api

import com.example.parcialmviles.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/user")
    suspend fun getUsers(): List<User>
}