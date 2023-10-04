package com.example.parcialmviles.adapter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcialmviles.model.UserRoom

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<UserRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserRoom)

    @Delete
    fun delete(user: UserRoom)
}
