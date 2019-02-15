package com.infosys.telstra.android.telstraandroidassignment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.infosys.telstra.android.telstraandroidassignment.util.ConnectivityUtils
import com.wipro.android.wiproandroidassignment.FactsActivity
import com.wipro.android.wiproandroidassignment.R
import com.wipro.android.wiproandroidassignment.viewmodel.FactViewModelFactory
import com.wipro.android.wiproandroidassignment.viewmodel.FactsViewModel

/**
Fragment class bound to view model and listen to live data
 */
class FactsFragment : Fragment() {
    private var columnCount: Int = 1;
    private lateinit var viewModel: FactsViewModel
    private lateinit var adapter: MyFactsRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeView: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(
            R.layout.fragment_facts_list, container,
            false
        )


        recyclerView = view.findViewById(R.id.listFacts) as RecyclerView
        swipeView = view.findViewById(R.id.swipe_refresh) as SwipeRefreshLayout

        lateinit var layoutManager: RecyclerView.LayoutManager;
        when (columnCount) {
            1 -> layoutManager = LinearLayoutManager(context)
            else -> layoutManager = GridLayoutManager(context, columnCount)
        }
        recyclerView.layoutManager = layoutManager;
        adapter = MyFactsRecyclerViewAdapter(activity)
        recyclerView.adapter = adapter

        viewModel = ViewModelProviders.of(this, FactViewModelFactory(activity?.application!!))
            .get(FactsViewModel::class.java)

        fetchDataFromRepository()
        swipeView.setOnRefreshListener {
            fetchDataFromRepository()
        }
        return view
    }

    fun fetchDataFromRepository() {

        if(!ConnectivityUtils.isNetworkAvailable(activity?.applicationContext!!)){
            showToast(getString(R.string.internet_unavailable))
        }
        viewModel.getFactList().observe(this, Observer { factList ->

            Log.e("FactsFragment", "In observer :: fact size"+ factList?.size);
            adapter.swapList(factList)
            if (factList != null && factList.size != 0){
                hideLoading()
            }
            else{
                if(ConnectivityUtils.isNetworkAvailable(activity?.applicationContext!!)) {
                    showLoading()
                }else{
                    showToast(getString(R.string.cache_empty))
                    if(swipeView.isRefreshing){
                        hideLoading()
                    }
                }
            }
        })
        viewModel.getAppTitle().observe(this, Observer { appTitle ->
            (activity as FactsActivity).title = appTitle?.title;
        })
    }

    private fun showLoading() {
        swipeView.isRefreshing = true
    }

    private fun hideLoading() {
        swipeView.isRefreshing = false
    }

    private fun showToast(text:String) {
        Toast.makeText(this.context, text,Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) =
            FactsFragment().apply {}
    }
}
