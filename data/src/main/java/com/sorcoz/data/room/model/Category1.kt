package com.sorcoz.data.room.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category1_table")
data class Category1(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "category1_name")
    val category1_name: String
)