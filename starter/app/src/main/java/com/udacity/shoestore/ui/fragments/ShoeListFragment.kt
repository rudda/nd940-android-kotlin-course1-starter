package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.LayoutShoeItemBinding
import com.udacity.shoestore.ui.SharedShoeViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private val mSharedShoeViewModel : SharedShoeViewModel by activityViewModels()
    private lateinit var mShoeListViewModel: ShoeListViewModel
    private lateinit var mBinding : FragmentShoeListBinding
    private lateinit var mLayoutItem : LayoutShoeItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_shoe_list,
            container,
            false) as FragmentShoeListBinding
        mShoeListViewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        mBinding.shoeListViewModel = mShoeListViewModel
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        mSharedShoeViewModel.shoeList.observe(viewLifecycleOwner, Observer {

            Timber.i("List<Shoe>: ${it.toString()}")
            it.forEach { shoe ->

                inflateShoeItemLayout()

                with(mLayoutItem) {
                    shoeNameText.text = shoe.name
                    shoeDescription.text = shoe.description
                    shoeSizeEText.text = shoe.size.toString()
                    companyText.text = shoe.company
                }

                mBinding.linearLayout.addView(mLayoutItem.root)

            }
        })

        mShoeListViewModel.addButton.observe(viewLifecycleOwner, Observer { hasClick ->
            if(hasClick) {
                val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
                Navigation.findNavController(mBinding.root).navigate(action)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        mShoeListViewModel.resetActionAddShoe()
    }

    fun inflateShoeItemLayout(){
        // inflate biding layout item
        val viewRoot = LayoutInflater.from(activity)
            .inflate(R.layout.layout_shoe_item, null, false)

        mLayoutItem = DataBindingUtil.bind(viewRoot)!!
    }
}