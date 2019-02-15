package com.wipro.android.wiproandroidassignment.repository.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Class to fetch data from API
 */
class FactsNetworkDataSource {
    companion object {
        val API_URL: String = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    }

    /**
     * Method return retrofit interface
     */
    public fun getNetworkData(): FactAPI {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder();
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(FactsNetworkDataSource.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build();
        return retrofit.create(FactAPI::class.java)
    }
}