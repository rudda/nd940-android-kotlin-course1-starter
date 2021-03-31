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
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.SharedShoeViewModel
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private val mSharedShoeViewModel: SharedShoeViewModel by activityViewModels()
    private lateinit var mBinding: FragmentShoeDetailBinding
    private lateinit var mShoeDetailViewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_shoe_detail,
            container,
            false) as FragmentShoeDetailBinding

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
                newShoe.description = mBinding.shoeDescriptionText.text.toString()

                newShoe.size =
                    if(mBinding.shoeSizeEText.text.toString().isEmpty())  0.0
                    else  mBinding.shoeSizeEText.text.toString().toString().toDouble()

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