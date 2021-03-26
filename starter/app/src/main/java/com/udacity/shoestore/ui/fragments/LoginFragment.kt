package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var mViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLoginBinding.inflate(layoutInflater)

        mViewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        mBinding.loginViewModel = mViewModel

        mViewModel.hasLogin.observe(viewLifecycleOwner, Observer {
            if (it) {
                loginFragmentToWelcomeFragment()
            }
        })
        return mBinding.root
    }

    private fun loginFragmentToWelcomeFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        Navigation.findNavController(mBinding.root).navigate(action)
    }
}