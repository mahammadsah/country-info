package com.muhammad.coutry.list.coutryinfo.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammad.coutry.list.coutryinfo.R
import com.muhammad.coutry.list.coutryinfo.adapter.CountryAdapter
import com.muhammad.coutry.list.coutryinfo.databinding.FragmentCountryBinding
import com.muhammad.coutry.list.coutryinfo.service.CountryDatabase
import com.muhammad.coutry.list.coutryinfo.view_model.CountryViewModel

class CountryFragment : Fragment() {
    private lateinit var dataBinding:FragmentCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dataBinding = FragmentCountryBinding.inflate(LayoutInflater.from(context))

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countryModel:CountryViewModel = ViewModelProviders.of(this)[CountryViewModel::class.java]

        arguments?.let {

            countryModel.getFromDatabase(CountryFragmentArgs.fromBundle(it).countryUuid.toInt())
        }

        countryModel.countryLiveData.observe(viewLifecycleOwner) {

            val data = listOf(countryModel.countryLiveData.value)

            val adapter = CountryAdapter(context, data)

            dataBinding.countryFragmentRecyclerList.layoutManager = LinearLayoutManager(context)

            dataBinding.countryFragmentRecyclerList.adapter = adapter
        }
    }
}