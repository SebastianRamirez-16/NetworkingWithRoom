package com.example.parcialmviles.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserRoom(
    @PrimaryKey val id: String,
    val job: String,
    val empresa: String,
    val description: String = "",
    val image: String = ""
)
