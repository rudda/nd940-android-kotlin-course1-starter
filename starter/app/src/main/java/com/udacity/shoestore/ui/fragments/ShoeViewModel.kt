package com.udacity.shoestore.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {
    var _shoeList : MutableLiveData<List<Shoe>> = MutableLiveData()
    val shoeList : LiveData<List<Shoe>>
        get() = _shoeList

}