package com.udacity.shoestore.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel : ViewModel() {

    var newShoe: Shoe = Shoe("", 0.0, "", "")

    private var _saveButton = MutableLiveData<Boolean>()
    public val saveButton : LiveData<Boolean>
                    get() = _saveButton

    private var _cancelButton = MutableLiveData<Boolean>()
    public val cancelButton : LiveData<Boolean>
        get() = _cancelButton

    fun saveClick() {
        _saveButton.value = true
    }

    fun restore() {
        _saveButton.value =   false
        _cancelButton.value = false
    }

    fun cancelClick() {
        _cancelButton.value = true
    }
}