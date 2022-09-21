package com.denizsimsek.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.denizsimsek.kotlincountries.R
import com.denizsimsek.kotlincountries.databinding.FragmentSecondBinding
import com.denizsimsek.kotlincountries.viewmodel.DetailseViewModel

class SecondFragment : Fragment() {

    private lateinit var viewModel:DetailseViewModel
    private var uuid = 0
    private lateinit var binding:FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_second,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            uuid=SecondFragmentArgs.fromBundle(it).countryUuid
        }
        viewModel=ViewModelProvider(this).get(DetailseViewModel::class.java)
        viewModel.getDataFromRoom(uuid)

        observeLiveData()


    }
    private fun observeLiveData()
    {
        viewModel.selectedCountry.observe(viewLifecycleOwner)
        {
            it?.let {
                binding.country=it
            }
        }
    }
}
