package com.wipro.android.wiproandroidassignment.repository.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
/**
 * Interface providing queries from database
 */
@Dao
interface FactDao {

    @Query("SELECT * FROM facts")
    fun getAllFacts(): LiveData<List<Fact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(factList: List<Fact>)

    @Query("DELETE FROM facts")
    fun deleteAllFacts()

    @Query("SELECT * FROM appTitle")
    fun getTitle(): LiveData<AppTitle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTitle(title: AppTitle)

    @Query("DELETE FROM appTitle")
    fun deleteTitle()
}