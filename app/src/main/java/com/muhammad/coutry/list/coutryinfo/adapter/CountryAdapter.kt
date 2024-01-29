package com.muhammad.coutry.list.coutryinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muhammad.coutry.list.coutryinfo.R
import com.muhammad.coutry.list.coutryinfo.databinding.LayoutCountryItemBinding
import com.muhammad.coutry.list.coutryinfo.model.CountryModel
import com.muhammad.coutry.list.coutryinfo.util.downloadFromUrl
import com.muhammad.coutry.list.coutryinfo.util.placeHolderProgressDrawable

class CountryAdapter(private val context: Context?, private val country:List<CountryModel?>)
    : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {

        val binding = DataBindingUtil.inflate<LayoutCountryItemBinding>(LayoutInflater.from(context),
            R.layout.layout_country_item, parent, false)

        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        try{

            holder.binding.selectedCountry = country[position]
        }catch(ex:Exception){

            Toast.makeText(holder.binding.root.context, ex.message, Toast.LENGTH_LONG).show()
        }


    }

    override fun getItemCount(): Int {
        return if (country.isNotEmpty()) country.size else 0
    }

    class CountryHolder(val binding:LayoutCountryItemBinding)
        : RecyclerView.ViewHolder(binding.root)
}