package com.wipro.android.wiproandroidassignment

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.wipro.android.wiproandroidassignment.repository.FactRepository
import com.wipro.android.wiproandroidassignment.repository.api.FactsNetworkDataSource
import com.wipro.android.wiproandroidassignment.repository.db.AppDatabase
import com.wipro.android.wiproandroidassignment.repository.db.Fact
import com.wipro.android.wiproandroidassignment.repository.db.FactDao
import com.wipro.android.wiproandroidassignment.util.LiveDataTestUtil.getValue
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class RoomDatabaseTest {


    companion object {

        private var DUMMY_FACT_LIST = ArrayList<Fact>()
        private var DUMMY_TITLE: String = "Dummy_title"

    }

    private lateinit var  mRepository: FactRepository
    private lateinit var  factDao: FactDao
    private var mDb: AppDatabase? = null
    private lateinit var factNetworkDataSource: FactsNetworkDataSource


    init {
        DUMMY_FACT_LIST.add(Fact(1,"1st fact title", "1st fact description", "http://first_url/"))
        DUMMY_FACT_LIST.add(Fact(2, "2nd fact title", "2nd fact description", "http://second_url/"))
        DUMMY_FACT_LIST.add(Fact(3, "3rd fact title", "3rd fact description", "http://third_url/"))
    }

    @Before
    fun setup() {
        mDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java).build()
        factDao = mDb!!.factDao()
        factNetworkDataSource = FactsNetworkDataSource()
        mRepository = FactRepository(factNetworkDataSource, factDao);
    }

    @After
    fun tearDown() {
        mDb!!.close()
    }

     /**
     * Test to store and retrieve correct app title
     */
    @Test
    fun titleManipulationTest() {

        mRepository.storeTitle(DUMMY_TITLE!!) // store title
        assertEquals(getValue(mRepository.getTitle()).title, DUMMY_TITLE)

    }

    /**
     * Test to store and get exact no of dummy entries in ROOM DB
     */
    @Test
    fun factListManipulationTest() {

        mRepository.storeFactsInDb(DUMMY_FACT_LIST) // store fact list
        assertEquals(3, getValue(mRepository.getFactsFromDb()).size)


    }

}