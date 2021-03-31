package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.ui.SharedShoeViewModel

class LoginFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var mViewModel: LoginViewModel
    private val sharedViewModel : SharedShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login,
            container,
            false) as FragmentLoginBinding

        mViewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        mBinding.loginViewModel = mViewModel

        mViewModel.hasLogin.observe(viewLifecycleOwner, Observer {
            if (it) {
                loginFragmentToWelcomeFragment()
            }
        })

        sharedViewModel.islogout.observe(viewLifecycleOwner, Observer {
            if(it) {
                mViewModel.logoutCompleted()
            }
        })

        return mBinding.root
    }

    private fun loginFragmentToWelcomeFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        Navigation.findNavController(mBinding.root).navigate(action)
    }
}