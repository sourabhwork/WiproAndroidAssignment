package com.wipro.android.wiproandroidassignment.repository

import android.arch.lifecycle.LiveData
import com.wipro.android.wiproandroidassignment.repository.api.FactsNetworkDataSource
import com.wipro.android.wiproandroidassignment.repository.db.AppTitle
import com.wipro.android.wiproandroidassignment.repository.db.Fact
import com.wipro.android.wiproandroidassignment.repository.db.FactDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Repository class to manipulate data from various data sources i.e. network,database
 */
class FactRepository(val networkDataSource: FactsNetworkDataSource, val factDao: FactDao) {
    fun getFacts(): LiveData<List<Fact>> {
        getFactsFromApi()
        return getFactsFromDb()
    }

    fun getTitle(): LiveData<AppTitle> {
        return factDao.getTitle()
    }

    private fun getFactsFromDb(): LiveData<List<Fact>> {
        return factDao.getAllFacts()
    }

    private fun getFactsFromApi() {
        val factApi = networkDataSource.getNetworkData()
        factApi.getFacts()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                clearCache()
                storeFactsInDb(data.rows)
                storeTitle(data.title)
            }, { error ->
            })
    }

    private fun storeFactsInDb(facts: List<Fact>) {
        factDao.insertAll(facts)
    }

    private fun clearCache(){
        factDao.deleteAllFacts()
        factDao.deleteTitle()
    }

    private fun storeTitle(title: String) {
        factDao.insertTitle(AppTitle(null,title))
    }
}