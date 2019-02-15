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
    public fun getFacts(): LiveData<List<Fact>> {
        getFactsFromApi()
        return getFactsFromDb()
    }

    public fun getTitle(): LiveData<AppTitle> {
        return factDao.getTitle()
    }

    public fun getFactsFromDb(): LiveData<List<Fact>> {
        return factDao.getAllFacts()
    }

    public fun getFactsFromApi() {
        val factApi = networkDataSource.getNetworkData()
        factApi.getFacts().retry()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                clearCache()
                storeFactsInDb(data.rows)
                storeTitle(data.title)
            }, { error ->
            })
    }

    public fun storeFactsInDb(facts: List<Fact>) {
        factDao.insertAll(facts)
    }

    public fun clearCache(){
        factDao.deleteAllFacts()
        factDao.deleteTitle()
    }

    public fun storeTitle(title: String) {
        factDao.insertTitle(AppTitle(null,title))
    }
}