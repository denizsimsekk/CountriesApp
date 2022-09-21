package com.denizsimsek.kotlincountries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.denizsimsek.kotlincountries.databinding.CountryRowBinding
import com.denizsimsek.kotlincountries.util.Country
import com.denizsimsek.kotlincountries.util.downloadFromUrl
import com.denizsimsek.kotlincountries.util.placeholderProgress
import com.denizsimsek.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.country_row.view.*

class CountryAdapter(var countryList:ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryHolder>(),CountryClickListener {
    class CountryHolder(val view: CountryRowBinding) : RecyclerView.ViewHolder(view.root)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<CountryRowBinding>(inflater,R.layout.country_row,parent,false)
        return CountryHolder(view)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        holder.view.country=countryList[position]
        holder.view.listener=this



    }

    override fun getItemCount(): Int {
        return countryList.size
    }
    fun refreshCountryList(list:List<Country>)
    {
        countryList.clear()
        countryList.addAll(list)
        notifyDataSetChanged()
    }



    override fun countryClicked(view: View) {
        var id=view.countryUuid.text.toString().toInt()

        var action=FeedFragmentDirections.actionFeedFragmentToSecondFragment(id)
        Navigation.findNavController(view).navigate(action)
    }
}
