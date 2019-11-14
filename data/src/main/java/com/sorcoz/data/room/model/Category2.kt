package com.sorcoz.data.room.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.sorcoz.data.room.model.Category1

@Entity(tableName = "category2_table")
data class Category2(
    @PrimaryKey
    @ColumnInfo(name = "category2_name")
    val category2_name: String,
    @ForeignKey(
        entity = Category1::class,
        parentColumns = ["category1_name"],
        childColumns =  ["cat1_name"],
        onDelete = ForeignKey.CASCADE
        )
    @NonNull
    val cat1_name: String
)