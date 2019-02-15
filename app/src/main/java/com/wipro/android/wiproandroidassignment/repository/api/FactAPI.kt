package com.wipro.android.wiproandroidassignment.repository.api

import com.wipro.android.wiproandroidassignment.repository.api.model.APIResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Retrofit interface
 */
interface FactAPI {
    @GET("facts.json")
    fun getFacts(): Observable<APIResponse>
}
