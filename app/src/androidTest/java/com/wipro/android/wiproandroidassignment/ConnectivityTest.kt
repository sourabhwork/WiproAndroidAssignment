package com.wipro.android.wiproandroidassignment

import android.support.test.InstrumentationRegistry
import com.infosys.telstra.android.telstraandroidassignment.util.ConnectivityUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ConnectivityTest {

    @Test
    fun internetConnectivityTest() {
        val isConnected = ConnectivityUtils.isNetworkAvailable(InstrumentationRegistry.getContext())
        Assert.assertEquals(true, isConnected)
    }
}