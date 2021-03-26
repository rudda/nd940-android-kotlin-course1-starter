package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.SharedShoeViewModel

class ShoeDetailFragment : Fragment() {

    private val mSharedShoeViewModel: SharedShoeViewModel by activityViewModels()
    private lateinit var mBinding: FragmentShoeDetailBinding
    private lateinit var mShoeDetailViewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentShoeDetailBinding.inflate(layoutInflater)

        mShoeDetailViewModel = ViewModelProvider(this)
            .get(ShoeDetailViewModel::class.java)

        mBinding.viewModel = mShoeDetailViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        mShoeDetailViewModel.saveButton.observe(viewLifecycleOwner, Observer { clicked ->

            if (clicked) {

                var newShoe: Shoe = Shoe("", 0.0, "", "")

                newShoe.name = mBinding.shoeNameText.text.toString()
                newShoe.company = mBinding.shoeCompanyText.text.toString()
                newShoe.size = mBinding.shoeSizeEText.text.toString().toDouble()
                newShoe.description = mBinding.shoeDescriptionText.text.toString()

                mSharedShoeViewModel.addShoe(newShoe)
                mShoeDetailViewModel.restore()
                navigateToShoeList()

            }

        })


        mShoeDetailViewModel.cancelButton.observe(viewLifecycleOwner, Observer { clicked ->
            if (clicked)  navigateToShoeList()
        })
    }

    private fun navigateToShoeList() {
        val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
        Navigation.findNavController(mBinding.root).navigate(action)
    }
}