package com.sorcoz.data.room.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.sorcoz.data.room.model.Category2

@Entity(tableName = "category3_table")
data class Category3(
    @PrimaryKey
    @ColumnInfo(name = "category3_name")
    val category3_name: String,
    @ForeignKey(
        entity = Category2::class,
        parentColumns = ["category2_name"],
        childColumns = ["cat2_name"],
        onDelete = ForeignKey.CASCADE
        )
    @NonNull
    val cat2_name: String
)