package com.wipro.android.wiproandroidassignment.viewmodel


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.wipro.android.wiproandroidassignment.repository.FactRepository
import com.wipro.android.wiproandroidassignment.repository.db.AppTitle
import com.wipro.android.wiproandroidassignment.repository.db.Fact

/**
 * ViewModel class that holds and maintains the data
 */
class FactsViewModel(factRepository: FactRepository) : ViewModel() {

    private lateinit var factList: LiveData<List<Fact>>
    private lateinit var appTitle: LiveData<AppTitle>
    init {
        factList = factRepository.getFacts()
        appTitle = factRepository.getTitle()
    }

    fun getFactList(): LiveData<List<Fact>> {
        return factList
    }

    fun getAppTitle(): LiveData<AppTitle> {
        return appTitle
    }
}