package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import timber.log.Timber

class WelcomeFragment : Fragment() {

    private lateinit var welcomeViewModel: WelcomeViewModel
    private lateinit var mBiding : FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBiding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_welcome,
            container,
            false) as FragmentWelcomeBinding

        welcomeViewModel = ViewModelProvider(this)
                                    .get(WelcomeViewModel::class.java)

        mBiding.welcomeViewModel = welcomeViewModel

        welcomeViewModel.goToInstructions.observe(viewLifecycleOwner, Observer {
            Timber.i("hello")
            if(it) {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment()
                Navigation.findNavController(mBiding.root).navigate(action)
            }
        })

        return  mBiding.root
    }
}