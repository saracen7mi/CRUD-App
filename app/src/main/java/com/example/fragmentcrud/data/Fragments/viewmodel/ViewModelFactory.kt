package com.example.fragmentcrud.data.Fragments.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(application: Application): ViewModelProvider.Factory {
    private val mApplication:Application

    init{
        mApplication = application

    }
  override  fun <T : ViewModel> create(modelClass:Class<T>):T {
        return UserViewModel(mApplication) as T
    }
}