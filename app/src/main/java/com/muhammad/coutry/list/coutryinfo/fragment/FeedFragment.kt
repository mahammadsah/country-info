package com.muhammad.coutry.list.coutryinfo.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.muhammad.coutry.list.coutryinfo.R
import com.muhammad.coutry.list.coutryinfo.adapter.FeedAdapter
import com.muhammad.coutry.list.coutryinfo.databinding.FragmentFeedBinding
import com.muhammad.coutry.list.coutryinfo.view_model.FeedViewModel

class FeedFragment : Fragment() {

    private lateinit var binding:FragmentFeedBinding

    private lateinit var adapter:FeedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFeedBinding.inflate(layoutInflater)

        context?.let {

            adapter = FeedAdapter(it, arrayListOf(), 0)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feedViewModel = ViewModelProviders.of(this)[FeedViewModel::class.java]

        binding.feedFragmentRecyclerList.layoutManager = LinearLayoutManager(context)

        binding.feedFragmentRecyclerList.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {

            binding.feedFragmentRecyclerList.visibility = View.GONE

            binding.feedFragmentErrorText.visibility = View.GONE

            binding.refreshLayout.isRefreshing = false

            feedViewModel.getDataFromApi()
        }

        feedViewModel.refreshData()

        feedViewModel.feedData.observe(viewLifecycleOwner) { countryData ->

            countryData?.let {

                binding.feedFragmentRecyclerList.visibility = View.VISIBLE

                adapter.updateFeedData(countryData)
            }
        }

        feedViewModel.feedError.observe(viewLifecycleOwner) { error->

            error?.let{

                if(it){

                    binding.feedFragmentErrorText.visibility = View.VISIBLE
                }else{

                    binding.feedFragmentErrorText.visibility = View.GONE
                }
            }
        }

        feedViewModel.feedProgress.observe(viewLifecycleOwner){ progress->

            progress?.let{

                if(it){

                    binding.feedFragmentProgress .visibility = View.VISIBLE

                    binding.feedFragmentRecyclerList.visibility = View.GONE

                    binding.feedFragmentErrorText.visibility = View.GONE

                }else{

                    binding.feedFragmentProgress .visibility = View.GONE
                }
            }
        }
    }
}