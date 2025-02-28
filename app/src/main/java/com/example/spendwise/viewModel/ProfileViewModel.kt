package com.example.spendwise.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spendwise.Data.model.User

class ProfileViewModel(): ViewModel() {
    var user: User? = null
        private set

    fun setUserData(user: User) {
        this.user = user
        println(user)
    }
}


class ProfileViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}