package com.wipro.android.wiproandroidassignment.repository.api.model

import com.wipro.android.wiproandroidassignment.repository.db.Fact

/**
 * Model class for API response
 */
data class APIResponse(
    val title: String,
    var rows: List<Fact>
)

