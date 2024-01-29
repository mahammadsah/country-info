package com.muhammad.coutry.list.coutryinfo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.muhammad.coutry.list.coutryinfo.R
import com.muhammad.coutry.list.coutryinfo.databinding.LayoutFeedItemBinding
import com.muhammad.coutry.list.coutryinfo.fragment.CountryFragmentDirections
import com.muhammad.coutry.list.coutryinfo.fragment.FeedFragmentDirections
import com.muhammad.coutry.list.coutryinfo.model.CountryListener
import com.muhammad.coutry.list.coutryinfo.model.CountryModel
import com.muhammad.coutry.list.coutryinfo.util.downloadFromUrl
import com.muhammad.coutry.list.coutryinfo.util.placeHolderProgressDrawable

class FeedAdapter(context:Context, feedList:ArrayList<CountryModel>, private var uuid:Int):
    RecyclerView.Adapter<FeedAdapter.CountryViewHolder>() {

    private val context:Context

    private var feedList:List<CountryModel>

    private var position:Int = 0

    init{

        this.context = context

        this.feedList = feedList
    }

    class CountryViewHolder(val binding: LayoutFeedItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

        val binding = DataBindingUtil.inflate<LayoutFeedItemBinding>(
            LayoutInflater.from(context),
            R.layout.layout_feed_item, parent, false
        )
        val holder = CountryViewHolder(binding)
        binding.listener = object : CountryListener {
            override fun onClickCountry(view: View) {

                uuid = feedList[holder.bindingAdapterPosition].uuid

                val directions = FeedFragmentDirections.actionNavFeedFragmentToNavCountryFragment(uuid.toLong())

                Navigation.findNavController(view).navigate(directions)
            }
        }


        return holder
    }

    override fun getItemCount(): Int {

        return if (feedList.isNotEmpty()) feedList.size else 0
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.binding.country = feedList[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFeedData(dataList: List<CountryModel>) {

        feedList = dataList

        notifyDataSetChanged()
    }
}