package com.denizsimsek.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.denizsimsek.kotlincountries.CountryAdapter
import com.denizsimsek.kotlincountries.R
import com.denizsimsek.kotlincountries.util.Country
import com.denizsimsek.kotlincountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private var countryList=ArrayList<Country>()
    private var adapter=CountryAdapter(countryList)
    private lateinit var viewModel:FeedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter

        viewModel=ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        swipeRefresh.setOnRefreshListener {
            recyclerView.visibility=View.GONE
            errorText.visibility=View.GONE
            progressBarid.visibility=View.VISIBLE
            viewModel.getDataFromApi()
            swipeRefresh.isRefreshing=false
        }

        observeLiveData()


    }
    private fun observeLiveData()
    {
        viewModel.countries.observe(viewLifecycleOwner)
        {countries->
            countries?.let {
                recyclerView.visibility=View.VISIBLE
                progressBarid.visibility=View.GONE
                errorText.visibility=View.GONE
                adapter.refreshCountryList(countries)
            }

        }
        viewModel.countryLoading.observe(viewLifecycleOwner)
        { loading->
            if(loading)
            {
                recyclerView.visibility=View.GONE
                progressBarid.visibility=View.VISIBLE
                errorText.visibility=View.GONE

            }

        }
        viewModel.countryError.observe(viewLifecycleOwner)
        {error->

            if(error)
            {
                recyclerView.visibility=View.GONE
                progressBarid.visibility=View.GONE
                errorText.visibility=View.VISIBLE

            }

        }
    }




}
