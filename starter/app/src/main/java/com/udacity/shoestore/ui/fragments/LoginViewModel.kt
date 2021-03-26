package com.udacity.shoestore.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private var _hasLogin = MutableLiveData<Boolean>()
    val hasLogin: LiveData<Boolean>
        get() {
            return _hasLogin
        }

    init {
        _hasLogin.value = false
    }

    fun login() {
        _hasLogin.value = true
    }

    fun logout() {
        _hasLogin.value = false
    }

}