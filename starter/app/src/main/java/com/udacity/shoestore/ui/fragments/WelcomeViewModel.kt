package com.udacity.shoestore.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class WelcomeViewModel : ViewModel() {
    private var _goToInstructions = MutableLiveData<Boolean>()

    val goToInstructions : LiveData<Boolean>
            get() = _goToInstructions

    init {
        _goToInstructions.value = false
    }

    fun goToInstructions() {
        Timber.i("goToInstructions")
        _goToInstructions.value = true
    }
}