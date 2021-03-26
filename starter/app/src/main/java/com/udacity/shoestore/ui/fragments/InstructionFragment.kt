package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    private lateinit var mInstructionViewModel: InstructionsViewModel
    private lateinit var mBinding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentInstructionBinding.inflate(layoutInflater)
        mInstructionViewModel = ViewModelProvider(this)
            .get(InstructionsViewModel::class.java)
        mBinding.instructionViewModel = mInstructionViewModel

        mInstructionViewModel.listScreen.observe(viewLifecycleOwner, Observer {
            if (it) {
                val action =
                    InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment()
                Navigation.findNavController(mBinding.root).navigate(action)
            }
        })
        return mBinding.root
    }
}