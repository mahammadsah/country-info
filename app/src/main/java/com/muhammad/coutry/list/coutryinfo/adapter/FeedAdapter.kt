package com.muhammad.coutry.list.coutryinfo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.muhammad.coutry.list.coutryinfo.databinding.LayoutFeedItemBinding
import com.muhammad.coutry.list.coutryinfo.fragment.FeedFragmentDirections
import com.muhammad.coutry.list.coutryinfo.model.CountryModel
import com.muhammad.coutry.list.coutryinfo.util.downloadFromUrl
import com.muhammad.coutry.list.coutryinfo.util.placeHolderProgressDrawable

class FeedAdapter(context:Context, feedList:ArrayList<CountryModel>): RecyclerView.Adapter<FeedAdapter.CountryViewHolder>() {

    private val context:Context

    private var feedList:List<CountryModel>

    init{

        this.context = context

        this.feedList = feedList
    }

    class CountryViewHolder(view: View, binding: LayoutFeedItemBinding) : RecyclerView.ViewHolder(view){

        val binding:LayoutFeedItemBinding

        init{

            this.binding = binding
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

        val binding = LayoutFeedItemBinding.inflate(LayoutInflater.from(context))

        binding.let{bind ->

            val holder = CountryViewHolder(bind.root, binding)

            holder.itemView.setOnClickListener {

                val action:NavDirections = FeedFragmentDirections.

                actionNavFeedFragmentToNavCountryFragment()

                Navigation.findNavController(bind.root).navigate(action)
            }
            return holder
        }
    }

    override fun getItemCount(): Int {

        return if(feedList.size > 0) feedList.size else 0
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        feedList.run {

            if(this.size> 0){

                holder.binding.feedItemCountryName.text = feedList[position].countryName

                holder.binding.feedItemCountryRegion.text = feedList[position].countryRegion

                holder.binding.feedItemCountryImage.downloadFromUrl(feedList[position].countryImageUrl!!,
                    placeHolderProgressDrawable(context))
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFeedData(dataList:List<CountryModel>){

        feedList = dataList

        notifyDataSetChanged()
    }
}