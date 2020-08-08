package com.example.fragmentcrud.data.Fragments.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fragmentcrud.data.Fragments.model.User
import com.example.fragmentcrud.data.Fragments.repository.UserRepository
import com.example.fragmentcrud.data.UserRoomData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {

    val getAllUsers: LiveData<List<User>>
    val repos: UserRepository

    init {
        val users = UserRoomData.getInstance(
            application
        ).userDao
        repos =
            UserRepository(
                users,
                application
            )
        getAllUsers = repos.getAll

    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repos.addUser(user)
        }
    }

    fun deleteuser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repos.deleteuser(user)
        }
    }

    fun updateuser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repos.updateuser(user)
        }
    }

    fun deleteAll(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repos.deleteAll(user)
        }
    }


}