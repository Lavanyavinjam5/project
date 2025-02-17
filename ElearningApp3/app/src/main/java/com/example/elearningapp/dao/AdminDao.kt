package com.example.elearningapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.elearningapp.entity.Admin

@Dao
interface AdminDao {
    @Insert
    fun insertAdmin(admin: Admin)

    // Query for login by username and password
    @Query("SELECT * FROM admin WHERE username = :username AND password = :password LIMIT 1")
    fun login(username: String, password: String): Admin?
}