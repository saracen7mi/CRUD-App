package com.example.fragmentcrud.data.Fragments.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.fragmentcrud.data.Fragments.model.User
import com.example.fragmentcrud.data.UserDao

class UserRepository(val userDao: UserDao, val context: Context) {


    val getAll: LiveData<List<User>> = userDao.getAll()

    suspend fun addUser(user: User) = userDao.addUser(user)

    suspend fun deleteuser(user: User) = userDao.delete(user)

    fun updateuser(user: User) = userDao.update(user)

    fun deleteAll(user: User) {
        userDao.deleteALL()
    }

}