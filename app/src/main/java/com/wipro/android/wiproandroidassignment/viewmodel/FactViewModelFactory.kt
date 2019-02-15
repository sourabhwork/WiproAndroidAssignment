package com.wipro.android.wiproandroidassignment.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.wipro.android.wiproandroidassignment.repository.FactRepository
import com.wipro.android.wiproandroidassignment.repository.api.FactsNetworkDataSource
import com.wipro.android.wiproandroidassignment.repository.db.AppDatabase

/**
 * Factory class that initialize ViewModel constructor parameters
 */
class FactViewModelFactory(private val context: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var repository = FactRepository(FactsNetworkDataSource(), AppDatabase.getInstance(context)!!.factDao());
        return FactsViewModel(repository) as T
    }
}