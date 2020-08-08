package com.example.fragmentcrud.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fragmentcrud.data.Fragments.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun delete(user: User)
    @Query("DELETE FROM user_table")
    fun deleteALL()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
   fun getAll(): LiveData<List<User>>

    @Update
    fun update(user: User)

}