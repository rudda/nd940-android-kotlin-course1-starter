package com.udacity.shoestore.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionsViewModel : ViewModel() {
    private var _listScreen = MutableLiveData<Boolean>()
    val listScreen : LiveData<Boolean>
                get() = _listScreen

    fun gotoListScreen() {
        _listScreen.value = true
    }
}