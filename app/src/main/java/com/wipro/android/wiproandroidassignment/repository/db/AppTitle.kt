package com.wipro.android.wiproandroidassignment.repository.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
/**
 * table class to manipulate app title
 */
@Entity(tableName = "appTitle")
data class AppTitle(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "title")
    val title: String?
)