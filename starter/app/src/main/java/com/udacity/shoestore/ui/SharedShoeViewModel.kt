package com.udacity.shoestore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class SharedShoeViewModel : ViewModel() {

    private var _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
                    get() = _shoeList

    init {
        _shoeList.value = ArrayList<Shoe>()
    }

    fun addShoe(newShoe:Shoe) {
        Timber.i("addShoe")
        _shoeList.value?.add(newShoe)
    }

}