package com.udacity.shoestore.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {

    private var _addButton = MutableLiveData<Boolean>()
    val addButton : LiveData<Boolean>
                get() = _addButton

    fun addShoe() {
        _addButton.value = true
    }

    fun resetActionAddShoe() {
        _addButton.value = false
    }

}